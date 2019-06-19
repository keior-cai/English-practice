package com.english.listening.mapper;

import com.english.listening.pojo.AdminInfo;
import com.english.listening.pojo.StudentInfo;
import com.english.listening.pojo.TeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 14:08
 */
public interface LoginMapper {
    @Select("select * from student_info where student_number =#{number} and student_password = #{pwd}")
    StudentInfo checkStudent(@Param("number") String number, @Param("pwd") String pwd);

    @Select("select * from teacher_info where teacher_number =#{number} and teacher_password = #{pwd}")
    TeacherInfo checkTeacher(@Param("number") String number, @Param("pwd") String pwd);

    @Select("select * from admin_info where admin_number =#{number} and admin_password = #{pwd}")
    AdminInfo checkAdmin(@Param("number") String number, @Param("pwd") String pwd);

}
