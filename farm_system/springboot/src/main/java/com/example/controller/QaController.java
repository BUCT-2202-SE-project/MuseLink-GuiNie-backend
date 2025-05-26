package com.example.controller;

import com.example.common.Result;
import com.example.entity.Qa;
import com.example.service.QaService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端操作接口
 **/
@RestController
@RequestMapping("/qa")
public class QaController {

    @Resource
    private QaService qaService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Qa qa) {
        qaService.add(qa);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id,
                             @RequestParam(required = false) String operator) {
        qaService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Qa qa) {
        qaService.updateById(qa);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Qa qa = qaService.selectById(id);
        return Result.success(qa);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Qa qa) {
        List<Qa> list = qaService.selectAll(qa);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Qa qa,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Qa> page = qaService.selectPage(qa, pageNum, pageSize);
        return Result.success(page);
    }

}