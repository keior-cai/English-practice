package com.english.listening.controller.admin;


import com.english.listening.exception.CheckException;
import com.english.listening.mapper.TeacherInfoMapper;
import com.english.listening.pojo.StudentInfo;
import com.english.listening.pojo.TeacherInfo;
import com.english.listening.result.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/")
@CrossOrigin("*")
public class TeacherManager {

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @GetMapping(value = "selectAllTeacher")
    public ResultBean<List<TeacherInfo>> allStudentInfo() {
        List<TeacherInfo> teacherInfoList =  teacherInfoMapper.selectAll();
        return new ResultBean<>(teacherInfoList,teacherInfoList.size());
    }

    @PostMapping("/updateTeacherInfo")
    public ResultBean updateTeacherInfo(TeacherInfo teacherInfo){
        if (teacherInfoMapper.updateTeacher(teacherInfo) > 0){
            return new ResultBean(0,"修改成功");
        }
        return new ResultBean(0,"修改失败");
    }

    @PostMapping("/addTeacher")
    public ResultBean addTeacher(TeacherInfo teacherInfo){
        if (teacherInfoMapper.selectByNumber(teacherInfo.getTeacherNumber()) != null){
            throw new CheckException("老师已存在");
        }
        if (teacherInfoMapper.insertTeacherInfo(teacherInfo) > 0){
            return new ResultBean(0,"添加成功");
        }
        return new ResultBean(0,"添加失败");
    }

    @PostMapping("/deleteTeacher")
    public ResultBean delete(@RequestParam("id")Integer id){
        if (teacherInfoMapper.deleteOne(id) > 0){
            return new ResultBean(0,"删除成功");
        }
        return new ResultBean(0,"删除失败");
    }

}
