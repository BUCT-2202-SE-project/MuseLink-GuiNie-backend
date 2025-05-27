package com.example.controller;

import com.example.common.LogAOP;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.service.AdminService;
import com.example.service.ReviewerService;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
public class WebController {

    @Resource
    private AdminService adminService;
    @Resource
    private UserService ReviewerService;


    /**
     * 默认请求接口
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    //@LogAOP(title = "登录", content = "用户登录")
    public Result login(@RequestBody Account account) {
        Account ac = null;
        if ("管理员".equals(account.getRole())) {
            ac = adminService.login(account);
        } else {
            ac = ReviewerService.login(account);
        }
        return Result.success(ac);
    }



}
