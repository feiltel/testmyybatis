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

    @Insert("insert into user(userName,passWord,realName) values(#{userName},#{passWord},#{realName})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int addUser(User user);

    /***************删***********************************************************************/
    @Delete("delete from user where id=#{id}")
    int deleteUser(int id);

    /***************改***********************************************************************/
    @Update("update user set userName=#{userName} where id=#{id}")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void updateUser(User user);

    /***************查***********************************************************************/
    @Select("select * from user where id =#{id}")
    User getUser(@Param("id") int id);


    @Select("select * from user")
    List<User> getAllUser();
}
