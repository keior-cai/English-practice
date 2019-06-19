package com.english.listening.controller.student;

import com.english.listening.mapper.StudentInfoMapper;
import com.english.listening.pojo.StudentInfo;
import com.english.listening.result.ResultBean;
import com.english.listening.utils.RequestAndResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 22:51
 */
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/student/")
public class StudentInfoController {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @GetMapping(value = "getStudentInfo")
    public ResultBean<StudentInfo> getStudentInfo() {
        StudentInfo studentInfo = (StudentInfo) RequestAndResponseUtils.getRequest().getAttribute("studentInfo");
        return new ResultBean<>(studentInfoMapper.selectByNumber(studentInfo.getStudentNumber()));

    }


}
