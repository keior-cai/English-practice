package com.english.listening.mapper;


import com.english.listening.pojo.OralLanguage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description:
 * @Author: Gentle
 * @date 2019/01/01 13:37
 */
public interface OralLanguageMapper {

    @Select("select * from oral_language where oral_language_id=#{oralLanguageId} and `delete` = 0")
    OralLanguage getOralLanguageByOralLanguageId(@Param("oralLanguageId") Integer oralLanguageId);

    @Select("select * from oral_language where `delete` = 0")
    List<OralLanguage> selectAll();

    @Select("select * from oral_language where oral_language_text = #{text} and `delete` = 0")
    OralLanguage selectOralLanguageByText(@Param("text") String text);

    @Insert("insert into oral_language(oral_language_text) values(#{text})")
    int addOraLanguage(@Param("text") String text);

    @Select("select * from oral_language where oral_language_id = #{id} and `delete` = 0")
    OralLanguage selectById(@Param("id") Integer id);


    @Insert("insert into oral_language(oral_language_text) values(#{oralLanguageText}) ")
    int insertOralLanguage(@Param("oralLanguageText") String oralLanguageText);

    @Update("update oral_language set oral_language_text=#{oralLanguageText} where oral_language_id=#{oralLanguageId}")
    int updateOralLanguage(OralLanguage oralLanguage);

    @Update("update oral_language set `delete` = 1 where oral_language_id =#{id}")
    int deleteOne(@Param("id") Integer id);
}

