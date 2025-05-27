package com.example.mapper;

import com.example.entity.Returns;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作returns相关数据接口
*/
public interface ReturnsMapper {

    /**
      * 新增
    */
    int insert(Returns returns);

    /**
      * 删除
    */
    @Delete("delete from returns where id = #{id}")
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Returns returns);

    /**
      * 根据ID查询
    */
    @Select("select * from returns where id = #{id}")
    Returns selectById(Integer id);

    /**
      * 查询所有
    */
    List<Returns> selectAll(Returns returns);

}