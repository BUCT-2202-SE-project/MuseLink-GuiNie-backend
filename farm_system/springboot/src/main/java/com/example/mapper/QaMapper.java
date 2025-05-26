package com.example.mapper;

import com.example.entity.Qa;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作goods相关数据接口
*/
@Mapper
public interface QaMapper {

    /**
      * 新增
    */
    int insert(Qa qa);

    /**
      * 删除
    */
    @Delete("delete from qa where qa_id = #{id}")
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Qa qa);

    /**
      * 根据ID查询
    */
    @Select("select * from qa where qa_id = #{id}")
    Qa selectById(Integer id);

    /**
      * 查询所有
    */
    List<Qa> selectAll(Qa qa);

}