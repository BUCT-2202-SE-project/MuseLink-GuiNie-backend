package com.example.controller;

import com.example.common.LogAOP;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Reviewer;
import com.example.service.AdminService;
import com.example.service.ReviewerService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员前端操作接口
 **/
@RestController
@RequestMapping("/reviewer")
public class ReviewerController {

    @Resource
    private ReviewerService reviewerService;

    /**
     * 新增
     */
    @PostMapping("/add")
    @LogAOP(title = "增", content = "新增审核员用户信息")
    public Result add(@RequestBody Reviewer reviewer) {
        reviewerService.add(reviewer);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @LogAOP(title = "删", content = "删除审核员用户信息")
    public Result deleteById(@PathVariable Integer id) {
        reviewerService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @LogAOP(title = "改", content = "修改审核员用户信息")
    public Result updateById(@RequestBody Reviewer reviewer) {
        reviewerService.updateById(reviewer);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Reviewer reviewer = reviewerService.selectById(id);
        return Result.success(reviewer);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Reviewer reviewer) {
        List<Reviewer> list = reviewerService.selectAll(reviewer);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Reviewer reviewer,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Reviewer> page = reviewerService.selectPage(reviewer, pageNum, pageSize);
        return Result.success(page);
    }

}