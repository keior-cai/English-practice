package com.english.listening.mapper;

import com.english.listening.pojo.ClassHomeWork;
import com.english.listening.pojo.HomeWorkHistory;
import com.english.listening.pojo.StudentHomeWork;
import com.english.listening.vo.StudentListeningSubjectSubmit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 22:00
 */
public interface HomeworkMapper {
    /**
     * 根据作业id查作业
     * @param homeworkId
     * @return
     */
    @Select("select * from class_homework where homework_id=#{homeworkId}")
    ClassHomeWork selectClassHomeWorkByHomeWorkkId(@Param("homeworkId") Integer homeworkId);

    /**
     * 查询所有作业
     * @return
     */
    @Select("select * from class_homework")
    List<ClassHomeWork> selectAllClassHomeWork();

    /**
     * 根据课程ID查作业
     * @param classId
     * @return
     */
    @Select("select * from class_homework where class_id=#{classId}")
    List<ClassHomeWork> selectClassHomeWorkByClassId(@Param("classId") Integer classId);

    /**
     * 根据学生id找自己的作业
     * @param studentID
     * @return
     */
    @Select("select * from student_homework where student_id=#{studentId}")
    StudentHomeWork selectClassHomeWorkByStudentId(@Param("studentId") Integer studentID);

    /**
     * 根据学生id找学生相关听力的历史作业
     * @param studentId
     * @return
     */
    @Select("select * from home_work_history where student_id =#{studentId}")
    List<HomeWorkHistory> selectAllListeningHomeWorkHistoryByStudentId(@Param("studentId")Integer studentId);

    /**
     * 根据学生查询学生朗读的历史作业
     * @param studentId
     * @return
     */
    @Select("select * from class_homework,student_homework where class_homework.homework_id=student_homework.class_home_work_id " +
            "and class_homework.homework_type=0 and student_homework.student_id=#{studentId}")
    List<StudentHomeWork> selectAllOralLanguageHomeWorkHistoryByStudentId(@Param("studentId")Integer studentId);

    @Select("select * from  class_homework where class_id =#{classId}")
    List<ClassHomeWork> selecAllHomeWorkByClassId(@Param("classId")Integer classId);

    /**
     * 根据学生id查询学生所有听力成绩
     *
     * @param studentId
     * @return
     */
    @Select("select student_homework.student_home_work_score from class_homework,student_homework where" +
            " class_homework.homework_id=student_homework.class_home_work_id " +
            " and class_homework.homework_type=1 and student_homework.student_id=#{studentId}")
    List<Integer> selectAllListeningHomeWorkScoreByStudentId(@Param("studentId")Integer studentId);


    @Insert("insert into student_homework(class_home_work_id,student_id,status,student_home_work_score) " +
            "values(#{classHomeWorkId},#{studentId},#{status},#{studentHomeWorkScore})")
    int insertStudentHomework(StudentHomeWork studentHomeWork);

    @Insert("insert into student_homework(class_home_work_id,student_id,status,student_home_work_score,long_text,home_work_path) " +
            "values(#{classHomeWorkId},#{studentId},#{status},#{studentHomeWorkScore},#{longText},#{homeWorkPath})")
    int insertStudentHomework2(StudentHomeWork studentHomeWork);


    @Insert("insert into home_work_history(student_id,listening_id,answer,user_answer) " +
            "values(#{studentId},#{listeningId},#{answer},#{userAnswer}) ")
    int  inserthomeWorkHistory(StudentListeningSubjectSubmit studentListeningSubjectSubmit);

    @Update("update student_homework set `delete` = 1 where id =#{id}")
    int deleteOne(@Param("id") Integer id);
}
