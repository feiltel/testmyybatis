#MyBatis 集成步骤
#####controller---Service--UserMapper--UserMapper.xml-Sql
1. 配置式：有xml文件 com.nut2014.mapper.UserDao
2. 注解式：无xml文件 com.nut2014.mapper.UserMapper

##一、分页配置
###1. pox.xml
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
###2. application.properties 
```
#pagehelper    
pagehelper.helperDialect=mysql    
pagehelper.reasonable=true  
pagehelper.supportMethodsArguments=true  
pagehelper.params=count=countSql
```
###3. 创建配置类
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
###4. 使用   
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
1. mapper.java  
```
@CacheNamespace //开启二级缓存
```
2. application.properties 
```
mybatis.configuration.cache-enabled=true
```
##三、多数据源配置
1.  application.properties 
```
`spring.datasource.hikari.db1.jdbc-url=jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&serverTimezone=UTC
spring.datasource.hikari.db1.username=root
spring.datasource.hikari.db1.password=tf26535..
spring.datasource.hikari.db1.driver-class-name=com.mysql.cj.jdbc.Driver`
```
2. 创建数据源配置类 
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
3. 使用
在mapper文件加上注解
```
@Qualifier("db1SqlSessionTemplate")
 public interface UserDao {
 
 ```