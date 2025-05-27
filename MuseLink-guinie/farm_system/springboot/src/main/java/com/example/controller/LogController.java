package com.example.controller;

import com.example.common.Result;
import com.example.entity.Log;
import com.example.service.LogService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员前端操作接口
 **/
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService LogService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Log log) {
        LogService.add(log);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        LogService.deleteById(id);
        return Result.success();
    }


    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Log log = LogService.selectById(id);
        return Result.success(log);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Integer operatorId) {
        List<Log> list = LogService.selectAll(operatorId);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam Integer operatorId,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
            int k=2;
        PageInfo<Log> page = LogService.selectPage(operatorId, pageNum, pageSize);
        return Result.success(page);
    }

}