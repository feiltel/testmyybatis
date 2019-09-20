#MyBatis 集成步骤
#####controller---Service--UserMapper--UserMapper.xml-Sql
1. 配置式：有xml文件 com.nut2014.mapper.UserDao
2. 注解式：无xml文件 com.nut2014.mapper.UserMapper

##一、分页配置
####1. pox.xml
```
<!--导入pagehelper相关依赖111-->
       <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>5.1.2</version>
        </dependency>
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-autoconfigure</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.3</version>
        </dependency>   
```
####2. application.properties 
```
#pagehelper    
pagehelper.helperDialect=mysql    
pagehelper.reasonable=true  
pagehelper.supportMethodsArguments=true  
pagehelper.params=count=countSql
```
####3. 创建配置类
```
@Configuration
public class PageHelperConfig {
    @Bean
    public PageHelper getPageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
```
####4. 使用   
```
@RequestMapping("/getPageUser")
    @Transactional(readOnly = true)
    public List<User> getPageUser(int pageNum) {
        //pageNum:表示第几页  pageSize:表示一页展示的数据
        PageHelper.startPage(pageNum, 3);
        List<User> list = userService.getAllUser();
        //将查询到的数据封装到PageInfo对象
        PageInfo<User> pageInfo = new PageInfo(list, 3);
        System.out.println(pageInfo.getTotal()+">>>>>>");
        //分割数据成功
        return list;
    }
```
##二、开启二级缓存,通过接口操作数据会自动刷新缓存
####1. mapper.java  
```
@CacheNamespace //开启二级缓存
```
####2. application.properties 
```
mybatis.configuration.cache-enabled=true
```
##三、多数据源配置
####1.  application.properties 
```
spring.datasource.hikari.db1.jdbc-url=jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&serverTimezone=UTC
spring.datasource.hikari.db1.username=root
spring.datasource.hikari.db1.password=tf26535..
spring.datasource.hikari.db1.driver-class-name=com.mysql.cj.jdbc.Driver
```
####2. 创建数据源配置类 
```
@Configuration
@MapperScan(basePackages = "com.nut2014.mapper", sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class DataSource1Config {

    /**
     * 生成数据源.  @Primary 注解声明为默认数据源
     */
    @Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.db1")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建 SqlSessionFactory
     */
    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "db1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```
####3. 使用
在mapper文件加上注解
```
@Qualifier("db1SqlSessionTemplate")
 public interface UserDao {
 
 ```
 
 ##四、数据库支持emoji  
 ####1. my.ini 增加配置    验证
 ```
 [client]
 default-character-set = utf8mb4
 [mysql]
 default-character-set = utf8mb4
 
 [mysqld]
 character-set-client-handshake = FALSE
 character-set-server = utf8mb4
 collation-server = utf8mb4_unicode_ci
 init_connect='SET NAMES utf8mb4'
  ```  
  ####2. 数据库，表编码统一使用utf8mb4  
   ```
 /*修改数据库编码*/
 ALTER DATABASE test CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
 /*修改表编码*/
 ALTER TABLE cover CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
  ```  
  ##五,集成JWT 实现token验证登录  
  ####1.引入依赖
   ```
  <dependency>
        <groupId>com.auth0</groupId>
        <artifactId>java-jwt</artifactId>
        <version>3.4.0</version>
  </dependency>
   ```
   ####2.加入自定义注解
   ```
 @Target({ElementType.METHOD, ElementType.TYPE})
 @Retention(RetentionPolicy.RUNTIME)
 public @interface PassToken {
     boolean required() default true;
 }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UserLoginToken {
        boolean required() default true;
    }
  ```  
  ####3.加入生成token的Service
  ```
  @Service
  public class TokenService {
      public String getToken(User user) {
          String token="";
          token= JWT.create().withAudience(user.getId()+"")
                  .sign(Algorithm.HMAC256(user.getPassWord()));
          return token;
      }
  }
```  
  ####4.加入拦截器
  ```
  public class AuthenticationInterceptor implements HandlerInterceptor {
      @Autowired
      UserService userService;
  
      @Override
      public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
          String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
          // 如果不是映射到方法直接通过
          if (!(object instanceof HandlerMethod)) {
              return true;
          }
          HandlerMethod handlerMethod = (HandlerMethod) object;
          Method method = handlerMethod.getMethod();
          //检查是否有passtoken注释，有则跳过认证
          if (method.isAnnotationPresent(PassToken.class)) {
              PassToken passToken = method.getAnnotation(PassToken.class);
              if (passToken.required()) {
                  return true;
              }
          }
          //检查有没有需要用户权限的注解
          if (method.isAnnotationPresent(UserLoginToken.class)) {
              UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
              if (userLoginToken.required()) {
                  // 执行认证
                  if (token == null) {
                      throw new RuntimeException("无token，请重新登录");
                  }
                  // 获取 token 中的 user id
                  String userId;
                  try {
                      userId = JWT.decode(token).getAudience().get(0);
                  } catch (JWTDecodeException j) {
                      throw new RuntimeException("401");
                  }
                  User user = userService.get(Integer.parseInt(userId));
                  if (user == null) {
                      throw new RuntimeException("用户不存在，请重新登录");
                  }
                  // 验证 token
                  JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassWord())).build();
                  try {
                      jwtVerifier.verify(token);
                  } catch (JWTVerificationException e) {
                      throw new RuntimeException("401");
                  }
                  return true;
              }
          }
          return true;
      }
  
      @Override
      public void postHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o, ModelAndView modelAndView) throws Exception {
  
      }
  
      @Override
      public void afterCompletion(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse,
                                  Object o, Exception e) throws Exception {
      }
  
  }
   ```
   ####5.配置拦截器
   ```
   @Configuration
   public class InterceptorConfig extends WebMvcConfigurerAdapter {
       @Override
       public void addInterceptors(InterceptorRegistry registry) {
           registry.addInterceptor(authenticationInterceptor())
                   .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
       }
       @Bean
       public AuthenticationInterceptor authenticationInterceptor() {
           return new AuthenticationInterceptor();
       }
   }
   ``` 
   ####6.验证
   1. 登录时返回token ，之后每次请求头都带上token 参数
   2. 在想要验证的方法加入   @UserLoginToken 注解 