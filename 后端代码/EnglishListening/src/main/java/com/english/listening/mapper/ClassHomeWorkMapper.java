package com.english.listening.mapper;

import com.english.listening.pojo.ClassHomeWork;
import com.english.listening.vo.OralLanguageHomeWorkVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ClassHomeWorkMapper {

    int addClassHomeWork(ClassHomeWork classHomeWork);

    List<OralLanguageHomeWorkVO> selectOralLanguageHomeWorkByClass(@Param("classId") Integer classId, @Param("homeWorkType") Integer homeWorkType);

    @Update("update class_homework set `class_homework`.`delete` = 1 where homework_id = #{id}")
    int deleteOne(@Param("id") Integer id);
}
