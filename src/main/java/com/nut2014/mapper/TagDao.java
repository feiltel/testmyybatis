package com.nut2014.mapper;

import com.nut2014.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * 注解版实现
 */
@Mapper
@CacheNamespace //开启二级缓存
@Qualifier("db1SqlSessionTemplate")//开启数据源1
public interface TagDao {
    /***************增***********************************************************************/

    @Insert("insert into tag(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int add(Tag tag);

    /***************删***********************************************************************/
    @Delete("delete from tag where id=#{id}")
    int delete(int id);

    /***************改***********************************************************************/
    @Update("update tag set userName=#{userName} where id=#{id}")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int update(Tag tag);

    /***************查***********************************************************************/
    @Select("select * from tag where id =#{id}")
    Tag get(@Param("id") int id);


    @Select("select * from tag")
    List<Tag> getAll();
}
