package com.english.listening.mapper;

import com.english.listening.pojo.ListeningSubject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 22:38
 */
public interface ListeningSubjectMapper {

    @Select("select * from listening_subject where `delete` = 0")
    List<ListeningSubject> selectAllListeningSubject();

    /**
     * 随机出自定义数量道题目
     * @param number
     * @return
     */
    @Select("SELECT * FROM listening_subject WHERE `delete` = 0 AND subject_id >= ((SELECT MAX(subject_id) FROM listening_subject where `delete` = 0)-(SELECT MIN(subject_id) FROM listening_subject where `delete` = 0)) * RAND() + (SELECT MIN(subject_id) FROM listening_subject where `delete` = 0) limit #{number}" )
    List<ListeningSubject> selectRandomListeningSubject(@Param("number")Integer number);

    @Select("select * from listening_subject where `delete` = 0 and subject_id =#{subjectId}")
    ListeningSubject selectListeningSubjectById(@Param("subjectId") Integer id);


    int addAllListeningSubject(@Param("listeningSubject") List<ListeningSubject> listeningSubject);

    @Update("update listening_subject set option_A = #{optionA},option_B = #{optionB}," +
            "option_C = #{optionC},option_D = #{optionD},answer = #{answer},subject_title = #{subjectTitle} " +
            "where subject_id = #{subjectId}")
    int updateListenlingSubject(ListeningSubject listeningSubject);

    @Update("update listening_subject set `delete` = 1 where subject_id =#{id}")
    int deleteOne(@Param("id") Integer id);
}
