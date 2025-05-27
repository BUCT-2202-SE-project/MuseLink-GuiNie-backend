package com.example.controller;

import com.example.common.LogAOP;
import com.example.common.Result;
import com.example.entity.Artifact;
import com.example.service.ArtifactService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端操作接口
 **/
@RestController
@RequestMapping("/Artifact")
public class ArtifactController {

    @Resource
    private ArtifactService artifactService;

    /**
     * 新增
     */
    @PostMapping("/add")
    @LogAOP(title = "增", content = "新增用户信息")
    public Result add(@RequestBody Artifact artifact) {
        artifactService.add(artifact);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @LogAOP(title = "删", content = "删除用户信息")
    public Result deleteById(@PathVariable Integer id,
                             @RequestParam(required = false) String operator) {
        artifactService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @LogAOP(title = "改", content = "修改用户信息")
    public Result updateById(@RequestBody Artifact artifact) {
        artifactService.updateById(artifact);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Artifact artifact = artifactService.selectById(id);
        return Result.success(artifact);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Artifact artifact) {
        List<Artifact> list = artifactService.selectAll(artifact);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Artifact artifact,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Artifact> page = artifactService.selectPage(artifact, pageNum, pageSize);
        return Result.success(page);
    }

}