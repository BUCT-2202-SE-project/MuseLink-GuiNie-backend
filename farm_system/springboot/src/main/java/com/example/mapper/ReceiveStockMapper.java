package com.example.mapper;

import com.example.entity.ReceiveStock;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作receiveStock相关数据接口
*/
public interface ReceiveStockMapper {

    /**
      * 新增
    */
    int insert(ReceiveStock receiveStock);

    /**
      * 删除
    */
    @Delete("delete from receive_Stock where id = #{id}")
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(ReceiveStock receiveStock);

    /**
      * 根据ID查询
    */
    @Select("select * from receive_Stock where id = #{id}")
    ReceiveStock selectById(Integer id);

    /**
      * 查询所有
    */
    List<ReceiveStock> selectAll(ReceiveStock receiveStock);

}