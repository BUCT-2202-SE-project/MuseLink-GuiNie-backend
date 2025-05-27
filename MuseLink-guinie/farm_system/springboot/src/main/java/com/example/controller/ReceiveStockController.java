package com.example.controller;

import com.example.common.Result;
import com.example.entity.ReceiveStock;
import com.example.service.ReceiveStockService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端操作接口
 **/
@RestController
@RequestMapping("/receiveStock")
public class ReceiveStockController {

    @Resource
    private ReceiveStockService receiveStockService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody ReceiveStock receiveStock) {
        receiveStockService.add(receiveStock);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        receiveStockService.deleteById(id);
        return Result.success();
    }


    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ReceiveStock receiveStock = receiveStockService.selectById(id);
        return Result.success(receiveStock);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(ReceiveStock receiveStock) {
        List<ReceiveStock> list = receiveStockService.selectAll(receiveStock);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(ReceiveStock receiveStock,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {

        int k=3;
        PageInfo<ReceiveStock> page = receiveStockService.selectPage(receiveStock, pageNum, pageSize);
        return Result.success(page);
    }

}