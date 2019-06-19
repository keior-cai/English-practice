package com.english.listening.aspject;



import com.english.listening.common.RedisService;
import com.english.listening.exception.UnloginException;
import com.english.listening.pojo.StudentInfo;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 拦截未登录的用户
 * @Author: Gentle
 * @date 2018/8/18 23:53
 */
@Order(5)
@Slf4j
@Component
@Aspect
public class LoginAuthorization {
    @Autowired
    RedisService redisService;


    @Pointcut("execution(public * com.english.listening.controller.student.*.*(..))")
    public void authorization() {
    }

    @Before("authorization()")
    public void handlerControllerMethod() throws Exception {

        HttpServletRequest httpServletRequest = RequestAndResponseUtils.getRequest();
        String uuId = httpServletRequest.getHeader("token");
        if (uuId == null || uuId.isEmpty()) {
            throw  new UnloginException("没有登录");
        }
        String s = redisService.get(uuId);
        StudentInfo studentInfo = JsonUtil.jsonToObject(s, StudentInfo.class);
        if (studentInfo == null){
            throw  new UnloginException("非法登录");
        }
        System.out.println(studentInfo);
        RequestAndResponseUtils.getRequest().setAttribute("studentInfo",studentInfo);
    }




}
