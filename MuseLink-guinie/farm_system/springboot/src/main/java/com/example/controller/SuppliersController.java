package com.example.controller;

import com.example.common.Result;
import com.example.entity.Supplier;
import com.example.service.SupplierService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端操作接口
 **/
@RestController
@RequestMapping("/supplier")
public class SuppliersController {

    @Resource
    private SupplierService supplierService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Supplier supplier) {
        supplierService.add(supplier);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        supplierService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Supplier supplier) {
        supplierService.updateById(supplier);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable  Integer id) {
        Supplier supplier = supplierService.selectById(id);
        return Result.success(supplier);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Supplier supplier) {
        List<Supplier> list = supplierService.selectAll(supplier);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Supplier supplier,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Supplier> page = supplierService.selectPage(supplier, pageNum, pageSize);
        return Result.success(page);
    }

}