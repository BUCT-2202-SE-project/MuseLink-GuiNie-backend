package com.example.service;

import com.example.entity.Returns;
import com.example.entity.Returns;
import com.example.mapper.ReturnsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务处理
 **/
@Service
public class ReturnService {

    @Resource
    private ReturnsMapper ReturnsMapper;


    /**
     * 新增
     */
    @Transactional

    /**
     * 根据ID查询
     */
    public Returns selectById(Integer id) {
        return ReturnsMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Returns> selectAll(Returns returns) {
        return ReturnsMapper.selectAll(returns);
    }

    /**
     * 分页查询
     */
    public PageInfo<Returns> selectPage(Returns returns, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Returns> list = ReturnsMapper.selectAll(returns);
        int k=3;
        return PageInfo.of(list);
    }

}