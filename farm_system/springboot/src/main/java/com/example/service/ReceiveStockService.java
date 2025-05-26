package com.example.service;

import com.example.entity.ReceiveStock;
import com.example.mapper.ReceiveStockMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务处理
 */
@Service
public class ReceiveStockService {

    @Resource
    private ReceiveStockMapper receiveStockMapper; // 修正拼写错误


    /**
     * 新增
     */
    @Transactional
    public void add(ReceiveStock receiveStock) {
        receiveStockMapper.insert(receiveStock);
    }

    /**
     * 删除
     */
    @Transactional
    public void deleteById(Integer id) {
        receiveStockMapper.deleteById(id);
    }

    /**
     * 根据ID查询
     */
    public ReceiveStock selectById(Integer id) {
        return receiveStockMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<ReceiveStock> selectAll(ReceiveStock receiveStock) {
        return receiveStockMapper.selectAll(receiveStock);
    }

    /**
     * 分页查询
     */
    public PageInfo<ReceiveStock> selectPage(ReceiveStock receiveStock, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        int h=22;
        List<ReceiveStock> list = receiveStockMapper.selectAll(receiveStock);


        return new PageInfo<>(list); // 使用构造函数创建 PageInfo 对象
    }
}