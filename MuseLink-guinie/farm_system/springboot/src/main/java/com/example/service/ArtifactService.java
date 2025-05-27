package com.example.service;

import com.example.entity.Artifact;
import com.example.mapper.ArtifactMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务处理
 **/
@Service
public class ArtifactService {

    @Resource
    private ArtifactMapper artifactMapper;

    /**
     * 新增
     */
    public void add(Artifact artifact) {
        artifactMapper.insert(artifact);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        artifactMapper.deleteById(id);
    }

    /**
     * 修改
     */
    public void updateById(Artifact artifact) {
        artifactMapper.updateById(artifact);
    }

    /**
     * 根据ID查询
     */
    public Artifact selectById(Integer id) {
        return artifactMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Artifact> selectAll(Artifact artifact) {
        return artifactMapper.selectAll(artifact);
    }

    /**
     * 分页查询
     */
    public PageInfo<Artifact> selectPage(Artifact artifact, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Artifact> list = artifactMapper.selectAll(artifact);
        return PageInfo.of(list);
    }

}