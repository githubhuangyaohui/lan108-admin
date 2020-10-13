package com.persistenthuang.lan108admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    //登录拦截器
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        Subject subject = null;
        try {
            // 放行 options 请求，否则无法让前端带上自定义的 header 信息，
            // 导致 sessionID 改变，shiro 验证失败
            if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
                httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
                return true;
            }

            subject = SecurityUtils.getSubject();
            // 使用 shiro 验证
        } catch (Exception e) {

        }
        return subject.isAuthenticated() || subject.isRemembered();
    }
}

