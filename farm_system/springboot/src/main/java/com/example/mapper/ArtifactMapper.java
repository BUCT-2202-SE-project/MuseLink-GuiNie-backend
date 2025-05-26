package com.example.mapper;

import com.example.entity.Artifact;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作category相关数据接口
*/
public interface ArtifactMapper {

    /**
      * 新增
    */
    int insert(Artifact artifact);

    /**
      * 删除
    */
    @Delete("delete from artifact where artifact_id = #{id}")
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Artifact artifact);

    /**
      * 根据ID查询
    */
    @Select("select * from artifact where artifact_id = #{id}")
    Artifact selectById(Integer id);

    /**
      * 查询所有
    */
    List<Artifact> selectAll(Artifact artifact);

}