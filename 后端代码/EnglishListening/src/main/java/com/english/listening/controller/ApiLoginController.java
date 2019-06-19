package com.english.listening.controller;


import com.english.listening.common.RedisService;
import com.english.listening.exception.CheckException;
import com.english.listening.mapper.LoginMapper;
import com.english.listening.pojo.AdminInfo;
import com.english.listening.pojo.StudentInfo;
import com.english.listening.pojo.TeacherInfo;
import com.english.listening.result.ResultBean;
import com.english.listening.utils.JsonUtil;
import com.english.listening.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class ApiLoginController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private LoginMapper loginMapper;

    @PostMapping(value = "/student/login")
    public ResultBean<String > login(@RequestParam(value = "number") String number, @RequestParam(value = "pwd") String pwd){
        if (StringUtils.isEmpty(number) || StringUtils.isEmpty(pwd)) {
            throw  new CheckException("用户名或密码不能为空");
        }
        StudentInfo studentInfo = loginMapper.checkStudent(number,pwd);
        if (studentInfo == null){
            throw  new CheckException("用户名或密码错误");
        }
        String token = UuidUtil.get32UUID();
        redisService.set(token, JsonUtil.ObjectTojson(studentInfo),60*60*6);
        return new ResultBean<>(token);
    }


    @PostMapping(value = "/teacher/login")
    public ResultBean<String >  teacherLogin(@RequestParam(value = "number") String number, @RequestParam(value = "pwd") String pwd){
        if (StringUtils.isEmpty(number) || StringUtils.isEmpty(pwd)) {
            throw  new CheckException("用户名或密码不能为空");
        }
        TeacherInfo teacherInfo = loginMapper.checkTeacher(number,pwd);
        if (teacherInfo == null){
            throw  new CheckException("用户名或密码错误");
        }
        String token = UuidUtil.get32UUID();
        redisService.set(token, JsonUtil.ObjectTojson(teacherInfo),60*60*6);
        return new ResultBean<>(token);
    }


    @PostMapping(value = "/admin/login")
    public ResultBean<String >  adminLogin(@RequestParam(value = "number") String number, @RequestParam(value = "pwd") String pwd){
        if (StringUtils.isEmpty(number) || StringUtils.isEmpty(pwd)) {
            throw  new CheckException("用户名或密码不能为空");
        }
        AdminInfo adminInfo = loginMapper.checkAdmin(number,pwd);
        if (adminInfo == null){
            throw  new CheckException("用户名或密码错误");
        }
        String token = UuidUtil.get32UUID();
        redisService.set(token, JsonUtil.ObjectTojson(adminInfo),60*60*6);
        return new ResultBean<>(token);
    }
}
