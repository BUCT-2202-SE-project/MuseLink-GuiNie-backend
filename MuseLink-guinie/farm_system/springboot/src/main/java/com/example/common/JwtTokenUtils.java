package com.example.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.Admin;
import com.example.service.AdminService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



import java.util.Date;

@Component
public class JwtTokenUtils {
    private static AdminService staticAdminService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Resource
    private AdminService adminService;

    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
    }


    /**
     * 生成token
     */
    public static String genToken(String userId, String password) {
        return JWT.create()
                .withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                .sign(Algorithm.HMAC256(password));
    }

    /**
     * 获取当前登录的用户信息
     */
    public static Admin getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            System.out.println("获取到的 token: " + token);

            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败，token: {}", token);
                return null;
            }
            // 解析token，获取用户的id
            System.out.println("获取到的 token: " + JWT.decode(token).getAudience());
            String adminId = JWT.decode(token).getAudience().get(0);
            System.out.println("获取到的 adminId: " + adminId);
            return staticAdminService.findByUsername(adminId);
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败，token={}", token, e);
            return null;
        } // 补全缺失的 catch 块闭合大括号
    }
}


