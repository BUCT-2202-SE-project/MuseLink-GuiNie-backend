package com.example.entity;

import java.time.LocalDateTime;

/**
 * 审核员实体类（对应数据库 reviewer 表）
 */
public class Reviewer extends Account {
    /** 用户ID */
    private Integer userId;
    /** 用户名 */
    private String username;
    /** 密码（需加密存储） */
    private String password;
    /** 邮箱 */
    private String email;
    ///** 注册时间 */
    private String registerTime; // 使用 Date 类型对应数据库 datetime

    /** 注册时间，默认 CURRENT_TIMESTAMP */
    //private LocalDateTime registerTime;

    /** 用户角色（固定为“审核员”） */
    private String role;
    /** 权限状态（正常/受限） */
    private String permissionStatus;

    // Getter 和 Setter
    @Override
    public Integer getUserId() {
        return userId;
    }
    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String getRegisterTime() {
        return registerTime;
    }
    @Override
    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
    @Override
    public String getRole() {
        return role;
    }
    @Override
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String getPermissionStatus() {
        return permissionStatus;
    }
    @Override
    public void setPermissionStatus(String permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

}