package com.english.listening.mapper;

import com.english.listening.pojo.ClassInfo;
import com.english.listening.pojo.StudentClassInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 22:28
 */
public interface ClassInfoMapper {

    @Select("select * from student_class_info where student_id =#{studentId} AND `student_class_info`.`delete` = 0")
    StudentClassInfo selectStudentClassInfoByStudentId(@Param("studentId") Integer studentId);

    @Select("select * from class_info WHERE `class_info`.`delete` = 0")
    List<ClassInfo> selectAllClassInfo();

    @Select("select * from student_class_info where teacher_id =#{teacherId} AND `student_class_info`.`delete` = 0")
    StudentClassInfo selectStudentClassInfoByTeacherId(@Param("teacherId") Integer teacherId);

    List<ClassInfo> selectByClassIdList(@Param("idList") List<Integer> idList);

    @Insert("insert into class_info(class_number) values(#{classNumber})")
    int addClassInfo(ClassInfo classInfo);

    @Update("update class_info set class_number = #{classNumber} where class_id = #{classId}")
    int updateClassInfo(ClassInfo classInfo);

    @Insert("insert into student_class_info(student_id,teacher_id,class_id) values(#{studentId},#{teacherId},#{classId})")
    int insertTeacherClassAndStudent(StudentClassInfo studentClassInfo);

    @Update("update class_info set `delete` = 1 where class_id = #{id}")
    int deleteOne(@Param("id") Integer id);
}
