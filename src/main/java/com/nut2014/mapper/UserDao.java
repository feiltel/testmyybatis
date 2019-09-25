package com.nut2014.mapper;

import com.nut2014.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * 注解版实现
 */
@Mapper
@CacheNamespace //开启二级缓存
//@Qualifier("db1SqlSessionTemplate")//开启数据源1
public interface UserDao {
    /***************增***********************************************************************/

    @Insert("insert into user(userName,passWord,realName,bgImg) values(#{userName},#{passWord},#{realName},#{bgImg})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int add(User user);

    /***************删***********************************************************************/
    @Delete("delete from user where id=#{id}")
    int delete(int id);

    /***************改***********************************************************************/
    @Update("update user set userName=#{userName} where id=#{id}")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int update(User user);

    /***************查***********************************************************************/
    @Select("select * from user where id =#{id}")
    User get(@Param("id") int id);


    @Select("select * from user")
    List<User> getAll();
}
