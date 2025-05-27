package com.example.service;

import com.example.entity.Qa;
import com.example.mapper.QaMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务处理
 **/
@Service
public class QaService {

    @Resource
    private QaMapper qaMapper;

    /**
     * 新增
     */
    public void add(Qa qa) {

        qaMapper.insert(qa);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        qaMapper.deleteById(id);
    }

    /**
     * 修改
     */
    public void updateById(Qa qa) {
        qaMapper.updateById(qa);
    }

    /**
     * 根据ID查询
     */
    public Qa selectById(Integer id) {
        return qaMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Qa> selectAll(Qa qa) {
        return qaMapper.selectAll(qa);
    }

    /**
     * 分页查询
     */
    public PageInfo<Qa> selectPage(Qa qa, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Qa> list = qaMapper.selectAll(qa);
        return PageInfo.of(list);
    }

}