package com.english.listening.controller.admin;

import com.english.listening.exception.CheckException;
import com.english.listening.mapper.StudentInfoMapper;
import com.english.listening.pojo.StudentInfo;
import com.english.listening.result.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/")
@CrossOrigin("*")
public class StudentManager {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @GetMapping(value = "selectAllStudent")
    public ResultBean<List<StudentInfo>> allStudentInfo() {
        List<StudentInfo> studentInfoList = studentInfoMapper.selectAll();
        return new ResultBean<>(studentInfoList,studentInfoList.size());
    }

    @PostMapping(value = "updateStudentInfo")
    public ResultBean<String> updateStudentInfo(StudentInfo studentInfo) {
        if (studentInfoMapper.updateStudent(studentInfo) >0){
            return new ResultBean<>(0,"修改成功");
        }
        return new ResultBean<>(0,"修改失败");
    }

    @PostMapping(value = "addStudent")
    public ResultBean<String> addStudent(StudentInfo studentInfo) {
        if (studentInfoMapper.selectByNumber(studentInfo.getStudentNumber()) != null){
            throw new CheckException("用学生已存在");
        }
        if (studentInfoMapper.addStudent(studentInfo) > 0){
            return new ResultBean<>(0,"添加成功");
        }
        return new ResultBean<>(0,"添加失败");
    }

    @PostMapping("/deleteStudent")
    public ResultBean delete(@RequestParam("id")Integer id){
        if (studentInfoMapper.deleteOne(id) > 0){
            return new ResultBean(0,"删除成功");
        }
        return new ResultBean(0,"删除失败");
    }
}
