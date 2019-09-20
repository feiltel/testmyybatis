package com.nut2014.mapper;

import com.nut2014.entity.Cover;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 注解版实现
 */
@Mapper
@CacheNamespace //开启二级缓存
//@Qualifier("db1SqlSessionTemplate")//开启数据源1
public interface CoverDao {


    /***************增***********************************************************************/

    @Insert("insert into cover(user_id,coverImgPath,coverDes,likeNumber,tag_id) " +
            "values" +
            "(#{user_id},#{coverImgPath},#{coverDes},#{likeNumber},#{tag_id})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int add(Cover cover);

    /***************删***********************************************************************/
    @Delete("delete from cover where id=#{id}")
    int delete(int id);

    /***************改***********************************************************************/
    @Update("update cover set user_id=#{user_id},coverImgPath=#{coverImgPath},coverDes=#{coverDes},likeNumber=#{likeNumber},tag_id=#{tag_id}" +
            " where id=#{id}")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int update(Cover cover);

    /***************查***********************************************************************/
    @Select("select * from cover where id =#{id}")
    Cover get(@Param("id") int id);


    @Select("select * from cover")
    List<Cover> getAll();


    @Select("select cover.likeNumber,cover.coverDes,cover.coverImgPath,cover.user_id,user.avatarPath,user.userName,tag.name tagName" +
            " from cover LEFT JOIN user ON cover.user_id = user.id  LEFT JOIN tag ON cover.tag_id = tag.id  order by cover.id desc")
    List<Cover> getCoverInfo();

    @Select("select cover.likeNumber,cover.coverDes,cover.coverImgPath,cover.user_id,user.avatarPath,user.userName,tag.name tagName" +
            " from cover LEFT JOIN user ON cover.user_id = user.id  LEFT JOIN tag ON cover.tag_id = tag.id   where cover.user_id =#{user_id} order by cover.id desc ")
    List<Cover> getUserCoverInfo(@Param("user_id") int user_id);

    /*左边标识代表bean里面的属性，右边标识和sql语句对应*/
    @Results({
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "coverImgPath", column = "coverImgPath"),
            @Result(property = "coverDes", column = "coverDes"),
            @Result(property = "likeNumber", column = "likeNumber"),
            @Result(property = "avatarPath", column = "avatarPath"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "tagName", column = "tagName")
    })
    @Select("select cover.likeNumber,cover.coverDes,cover.coverImgPath,cover.user_id,user.avatarPath,user.userName,tag.name tagName" +
            " from cover LEFT JOIN user ON cover.user_id = user.id  LEFT JOIN tag ON cover.tag_id = tag.id  order by cover.id desc")
    List<Cover> getCovers();

}
