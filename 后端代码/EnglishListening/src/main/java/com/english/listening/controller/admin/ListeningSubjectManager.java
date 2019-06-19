package com.english.listening.controller.admin;


import com.english.listening.constants.FileConstants;
import com.english.listening.exception.CheckException;
import com.english.listening.mapper.ListeningSubjectMapper;
import com.english.listening.pojo.ListeningSubject;
import com.english.listening.pojo.StudentInfo;
import com.english.listening.result.ResultBean;
import com.english.listening.utils.FileUpload;
import com.english.listening.utils.JsonUtil;
import com.english.listening.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/")
@CrossOrigin("*")
public class ListeningSubjectManager {

    @Autowired
    private ListeningSubjectMapper listeningSubjectMapper;

    @GetMapping(value = "getAllListeningSubject")
    public ResultBean<List<ListeningSubject>> getAllListeningSubject() {
        List<ListeningSubject> listeningSubjectList =  listeningSubjectMapper.selectAllListeningSubject();
        return new ResultBean<>(listeningSubjectList,listeningSubjectList.size());
    }
    @PostMapping("/addListeningSubject")
    public ResultBean addListeningSubject( @RequestParam("list") String list){

        if (StringUtils.isEmpty(list)){
            throw new CheckException("传入不能为空");
        }

        List<ListeningSubject> listeningSubjectList =  JsonUtil.jsonTolist(list,ListeningSubject.class);

        if (listeningSubjectMapper.addAllListeningSubject(listeningSubjectList) > 0){
            return new ResultBean(0,"添加成功");
        }
        return new ResultBean(0,"添加失败");
    }


    @PostMapping("/uploadListeningSubject")
    public ResultBean< List<String >> addListeningSubject(@RequestParam(value="file",required = false) MultipartFile[] file){

        if (file==null||file.length==0){
            throw new CheckException("没有文件上传");
        }
        List<String > list1 = new ArrayList<>();
        for (MultipartFile file1:file) {
            String s = FileUpload.fileUp(file1, FileConstants.DEFAULT_ROOT_PATH, UuidUtil.get32UUID());
            list1.add("http://39.108.125.57:8080/image/"+s.substring(s.lastIndexOf("/")+1));
        }

        return new ResultBean<>(list1);
    }

    @PostMapping("/updateListeningSubject")
    public ResultBean updateListeningSubject(ListeningSubject list){
        if (listeningSubjectMapper.updateListenlingSubject(list) > 0){
            return new ResultBean(0,"修改成功");
        }
        return new ResultBean(0,"修改失败");
    }

    @PostMapping("/deleteListeningSubject")
    public ResultBean delete(@RequestParam("id")Integer id){
        if (listeningSubjectMapper.deleteOne(id) > 0){
            return new ResultBean(0,"删除成功");
        }
        return new ResultBean(0,"删除失败");
    }

    public static void main(String[] args) {

        String s="adsdad/dddd";
        System.out.println(s.substring(s.indexOf("/")+1));

    }

}
