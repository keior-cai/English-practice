package com.english.listening.controller.admin;

import com.english.listening.mapper.ClassInfoMapper;
import com.english.listening.pojo.ClassInfo;
import com.english.listening.pojo.StudentClassInfo;
import com.english.listening.result.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/admin/")
public class ClassInfoManager {

    @Autowired
    private ClassInfoMapper classInfoMapper;


    @PostMapping(value = "addClassInfo")
    public ResultBean<String> addClassInfo(ClassInfo classInfo){
        if (classInfoMapper.addClassInfo(classInfo) > 0){
            return new ResultBean<>(0,"添加成功");
        }
        return new ResultBean<>(0,"添加失败");
    }
    @GetMapping(value = "selectAllClassInfo")
    public ResultBean<List<ClassInfo>> selectAllClassInfo(){
        List<ClassInfo> classInfoList =  classInfoMapper.selectAllClassInfo();
        return new ResultBean<>(classInfoList,classInfoList.size());
    }

    @PostMapping(value = "updateClassInfo")
    public ResultBean<String> updateClassInfo(ClassInfo classInfo){
        if (classInfoMapper.updateClassInfo(classInfo) > 0){
            return new ResultBean<>(0,"修改成功");
        }
        return new ResultBean<>(0,"修改失败");
    }

    @PostMapping(value = "addTeacherClassAndStudent")
    @Transactional
    public ResultBean<String > addTeacherClassAndStudent(StudentClassInfo studentClassInfo){

        classInfoMapper.insertTeacherClassAndStudent(studentClassInfo);

        return new ResultBean<>(0,"添加成功");
    }

    @PostMapping("/deleteClass")
    public ResultBean delete(@RequestParam("id")Integer id){
        if (classInfoMapper.deleteOne(id) > 0){
            return new ResultBean(0,"删除成功");
        }
        return new ResultBean(0,"删除失败");
    }

}
