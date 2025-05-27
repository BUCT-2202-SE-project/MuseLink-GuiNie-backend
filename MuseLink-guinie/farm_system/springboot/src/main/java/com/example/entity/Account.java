package com.example.entity;

import jakarta.persistence.Transient;

/**
 * 角色用户父类
 */
public class Account {
    private Integer userId;
    /** 用户名 */
    private String username;
//    /** 名称 */
//    private String name;
    /** 密码 */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 注册时间
     */
    private String registerTime;
    /** 角色标识 */
    private String role;
    /**
     *权限状态
     */
    private String permissionStatus;
    /** 新密码 */
    private String newPassword;
   /* *//** 头像 *//*
    private String avatar;*/
    /** token*/
    @Transient
    private String token;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /*public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }*/

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(String permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
