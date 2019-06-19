package com.english.listening.mapper;

import com.english.listening.pojo.StudentClassInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentClassInfoMapper {
    @Select("select * from student_class_info where teacher_id = #{teacherId} and `delete` = 0")
    List<StudentClassInfo> selectAllByTeacherId(@Param("teacherId") Integer teacherId);

    @Select("select * from student_class_info where student_id = #{studentId} and `delete` = 0")
    List<StudentClassInfo> selectAllByStudentId(@Param("studentId") Integer studentId);

    @Select("select * from student_class_info where class_id = #{classId} and `delete` = 0")
    List<StudentClassInfo> selectAllByClassId(@Param("classId") Integer classId);

    @Select("select * from student_class_info where class_id = #{classId} and teacher_id = #{teacherId} and `delete` = 0")
    List<StudentClassInfo> selectAllByClassIdAndTeacherId(@Param("classId") Integer classId, @Param("teacherId") Integer teacherId);

    @Select("select * from student_class_info where id = #{id} and `delete` = 0")
    StudentClassInfo selectStudentClassInfoById(Integer id);

    @Select("select * from student_class_info where `delete` = 0")
    List<StudentClassInfo> selectAll();

    @Update("update student_class_info set `delete` = 1 where id =#{id}")
    int deleteOne(@Param("id") Integer id);

}
