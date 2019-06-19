package com.english.listening.aspject;


import com.english.listening.common.RedisService;
import com.english.listening.exception.UnloginException;
import com.english.listening.pojo.AdminInfo;
import com.english.listening.utils.JsonUtil;
import com.english.listening.utils.RequestAndResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/08 16:35
 */
@Order(5)
@Slf4j
@Component
@Aspect
public class AdminLoginAuthorization {
    @Autowired
    RedisService redisService;


    @Pointcut("execution(public * com.english.listening.controller.admin.*.*(..))")
    public void authorization1() {
    }
    @Before("authorization1()")
    public void handlerControllerMethod1() throws Exception {

        HttpServletRequest httpServletRequest = RequestAndResponseUtils.getRequest();
        String uuId = httpServletRequest.getHeader("token");
        if (uuId == null || uuId.isEmpty()) {
            throw  new UnloginException("没有登录");
        }
        String s = redisService.get(uuId);
        AdminInfo adminInfo = JsonUtil.jsonToObject(s, AdminInfo.class);
        if (adminInfo == null){
            throw  new UnloginException("非法登录");
        }
        RequestAndResponseUtils.getRequest().setAttribute("teacherInfo",adminInfo);
    }
//


}
