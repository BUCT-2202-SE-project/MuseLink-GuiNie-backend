package com.example.entity;

/**
 * 管理员
*/
public class Admin extends Account {

    /** ID */
    private Integer userId;
    /** 用户名 */
    private String username;
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
    /** 姓名 *//*
    private String name;*/
    /** 头像 *//*
    private String avatar;*/
    /** 角色标识 */
    private String role;
    /**
     *权限状态
     */
    private String permissionStatus;


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

   /* @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }*/

    /*@Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }*/

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
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
    public String getPermissionStatus() {
        return permissionStatus;
    }

    @Override
    public void setPermissionStatus(String permissionStatus) {
        this.permissionStatus = permissionStatus;
    }
}