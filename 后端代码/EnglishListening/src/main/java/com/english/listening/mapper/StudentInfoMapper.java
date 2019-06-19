package com.english.listening.mapper;
import com.english.listening.pojo.StudentInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/**
 * @ClassName StudentInfoMapper
 * @Description
 * @Author CCJ
 * @Date 2018/12/31 21:57
 **/
public interface StudentInfoMapper {
    @Select("select * from student_info where `delete` = 0")
    List<StudentInfo> selectAll();

    @Select("select * from student_info where student_number = #{number}")
    StudentInfo selectByNumber(@Param("number") String number);

    @Update("update student_info set student_password = #{pwd} where student_number = #{number}")
    int updateStudentPwd(@Param("number") String number, @Param("pwd") String pwd);

    List<StudentInfo> selectAllByIdList(@Param("numberList") List<String> numberList);

    @Select("select * from student_info where student_idcard = #{idCard} and `delete` = 0")
    StudentInfo selectByIdCard(@Param("idCard") String idCard);

    @Update("update student_info set sex = #{sex} where student_number = #{number} and `delete` = 0")
    int updateStudentSex(@Param("number") String number, @Param("sex") Integer sex);

    @Update("update student_info set sex = #{sex},student_number = #{studentNumber}," +
            "student_password = #{studentPassword}," +
            "student_idcard = #{studentIdcard},student_name = #{studentName} " +
            "where student_id = #{studentId}")
    int updateStudent(StudentInfo studentInfo);

    @Insert("insert into student_info(student_number,student_password,student_idcard,student_name,sex) " +
            "values(#{studentNumber},#{studentPassword},#{studentIdcard},#{studentName},#{sex})")
    int addStudent(StudentInfo studentInfo);

    @Update("update student_info set `delete` = 1 where student_id =#{id}")
    int deleteOne(@Param("id") Integer id);

}
