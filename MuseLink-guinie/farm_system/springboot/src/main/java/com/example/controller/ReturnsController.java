package com.example.controller;

import com.example.common.Result;
import com.example.entity.Returns;
import com.example.entity.Returns;
import com.example.service.ReturnService;
import com.example.service.ReturnService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端操作接口
 **/
@RestController
@RequestMapping("/returns")
public class ReturnsController {

    @Resource
    private ReturnService returnService;


    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Returns returns = returnService.selectById(id);
        return Result.success(returns);
    }

    /**
     * 查询所有
 /*    *//*
    @GetMapping("/selectAll")
    public Result selectAll(Returns returns) {
        List<Returns> list = returnsService.selectAll(returns);
        return Result.success(list);
    }
*/
    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Returns returns,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        int h=3;
        PageInfo<Returns> page = returnService.selectPage(returns, pageNum, pageSize);
        return Result.success(page);
    }

}