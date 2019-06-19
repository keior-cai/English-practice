package com.english.listening.mapper;

import com.english.listening.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TeacherInfoMapper {
    @Select("select * from teacher_info where teacher_number = #{number}")
    TeacherInfo selectByNumber(@Param("number") String number);

    @Select("select * from teacher_info where `delete` = 0")
    List<TeacherInfo> selectAll();

    @Update("update teacher_info set teacher_password = #{pwd} where teacher_number = #{number} and `delete` = 0")
    int updatePwd(@Param("number") String number, @Param("pwd") String pwd);

    @Select("select * from teacher_info where teacher_idcard = #{idCard} and `delete` = 0")
    TeacherInfo selectByIdCard(@Param("idCare") String idCare);

    List<TeacherInfo> selectAllByNumberList(@Param("numberList") List<String> numberList);

    @Update("update teacher_info set sex = #{sex} where teacher_number = #{number}")
    int updateSex(@Param("number") String number, @Param("sex") Integer sex);

    @Update("update teacher_info set teacher_number = #{teacherNumber},teacher_password = #{teacherPassword}," +
            "teacher_idcard = #{teacherIdcard},teacher_name = #{teacherName},sex = #{sex} ,age = #{age}" +
            "where teacher_id = #{teacherId}")
    int updateTeacher(TeacherInfo teacherInfo);

    @Insert("insert into teacher_info(teacher_number,teacher_password,teacher_idcard,teacher_name) " +
            " values(#{teacherNumber},#{teacherPassword},#{teacherIdcard},#{teacherName})")
    int insertTeacherInfo(TeacherInfo teacherInfo);

    @Update("update teacher_info set `delete` = 1 where teacher_id =#{id}")
    int deleteOne(@Param("id") Integer id);
}
