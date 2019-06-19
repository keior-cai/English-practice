package com.english.listening.controller.admin;

import com.english.listening.exception.CheckException;
import com.english.listening.mapper.OralLanguageMapper;
import com.english.listening.pojo.ListeningSubject;
import com.english.listening.pojo.OralLanguage;
import com.english.listening.result.ResultBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/")
@CrossOrigin("*")
public class OralLanguageManager {

    @Autowired
    OralLanguageMapper oralLanguageMapper;


    @GetMapping(value = "selectAllOralLanguage")
    public ResultBean<List<OralLanguage>> selectAllOralLanguage() {

        List<OralLanguage> list = oralLanguageMapper.selectAll();

        return new ResultBean<>(list);
    }


    @PostMapping(value = "addOralLanguage")
    @Transactional
    public ResultBean<String > addOralLanguage(@Param("oralLanguageText") String oralLanguageText) {

        if (StringUtils.isEmpty(oralLanguageText)){
            throw new CheckException("不能为空");
        }
        oralLanguageMapper.insertOralLanguage(oralLanguageText);

        return new ResultBean<>(0,"添加成功");
    }

    @PostMapping(value = "updateOralLanguage")
    public ResultBean<String > updateOralLanguage(OralLanguage oralLanguage) {

        if (StringUtils.isEmpty(oralLanguage.getOralLanguageText())){
            throw new CheckException("不能为空");
        }
        if (oralLanguage.getOralLanguageId()==null){
            throw new CheckException("id不能为空");
        }

        oralLanguageMapper.updateOralLanguage(oralLanguage);

        return new ResultBean<>(0,"修改成功");
    }


    @PostMapping("/deleteOralLanguage")
    public ResultBean delete(@RequestParam("id")Integer id){
        if (oralLanguageMapper.deleteOne(id) > 0){
            return new ResultBean(0,"删除成功");
        }
        return new ResultBean(0,"删除失败");
    }


}
