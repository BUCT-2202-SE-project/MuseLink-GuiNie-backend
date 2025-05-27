package com.example.mapper;

import com.example.entity.Admin;
import com.example.entity.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作log相关数据接口
*/
public interface LogMapper {

    /**
      * 新增
    */
    int insert(Log admin);

    /**
      * 删除
    */
    @Delete("delete from log where log_id = #{id}")
    int deleteById(Integer id);


    /**
      * 根据ID查询
    */
    @Select("select * from log where log_id = #{id}")
    Log selectById(Integer id);

    /**
      * 查询所有
    */
    List<Log> selectAll(Integer operatorId);

    @Select("select * from log where username = #{username}")
    Log selectByUsername(String username);

}