package com.example.mapper;

import com.example.entity.Reviewer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface ReviewerMapper {

    /**
      * 新增
    */
    int insert(Reviewer reviewer);

    /**
      * 删除
    */
    @Delete("delete from reviewer where user_id = #{id}")
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Reviewer reviewer);

    /**
      * 根据ID查询
    */
    @Select("select * from admin where user_id = #{id}")
    Reviewer  selectById(Integer id);


    /**
      * 查询所有
    */
    List<Reviewer > selectAll(Reviewer reviewer);


    /**
     * 根据用户名查询
     */
    @Select("select * from reviewer where username = #{username}")
    Reviewer selectByUsername(String username);

}