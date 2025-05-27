package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.JwtTokenUtils;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.entity.Reviewer;
import com.example.entity.Reviewer;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.example.mapper.ReviewerMapper;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.entity.Account;

import java.util.List;

/**
 * 管理员业务处理
 **/
@Service
public class ReviewerService {

    @Resource
    private ReviewerMapper reviewerMapper;

    /**
     * 新增
     */
    public void add(Reviewer reviewer) {
        // 检查用户名是否已存在
        Reviewer dbReviewer = reviewerMapper.selectByUsername(reviewer.getUsername());
        if (ObjectUtil.isNotNull(dbReviewer)) {
            throw new CustomException("用户名已存在");
        }
        // 密码默认值（根据需求调整）
        if (ObjectUtil.isEmpty(reviewer.getPassword())) {
            reviewer.setPassword("123"); // 建议加密后存储
        }
        // 固定角色为“审核员”（表默认值已保证，但代码中可显式设置）
        reviewer.setRole("reviewer");
        // 权限状态默认值由数据库处理，若需显式设置：reviewer.setPermissionStatus("正常");
        reviewerMapper.insert(reviewer);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        reviewerMapper.deleteById(id);
    }

    /**
     * 修改
     */
    public void updateById(Reviewer reviewer) {
        reviewerMapper.updateById(reviewer);
    }

    /**
     * 根据ID查询
     */
    public Reviewer  selectById(Integer id) {
        return reviewerMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Reviewer > selectAll(Reviewer reviewer) {
        return reviewerMapper.selectAll(reviewer);
    }

    /**
     * 分页查询
     */
    public PageInfo<Reviewer> selectPage(Reviewer reviewer, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Reviewer> list = reviewerMapper.selectAll(reviewer);
        return PageInfo.of(list);
    }

    /**
     * 登录
     */
    public Account login(Account account) {
        Account dbReviewer = reviewerMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbReviewer)) {
            throw new CustomException("用户不存在");
        }
        if (!account.getPassword().equals(dbReviewer.getPassword())) {
            throw new CustomException("账号或密码错误");
        }

        //生成Token
        String token = JwtTokenUtils.genToken(dbReviewer.getUsername(), account.getPassword());
        dbReviewer.setToken(token);
        return dbReviewer;
    }

    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        Reviewer dbReviewer = reviewerMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbReviewer)) {
            throw new CustomException("用户不存在");
        }
        if (!account.getPassword().equals(dbReviewer.getPassword())) {
            throw new CustomException("原密码错误");
        }
        dbReviewer.setPassword(account.getNewPassword());
        reviewerMapper.updateById(dbReviewer);
    }

    public Reviewer findByUsername(String username) {
        return reviewerMapper.selectByUsername(username);
    }
}