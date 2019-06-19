package com.english.listening.vo;

import com.english.listening.pojo.OralLanguage;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: Gentle
 * @date 2019/01/01 00:26
 */
@Data
public class OralLanguageIdVo {

    /**
     * 作业id
     */
    private Integer homeworkId;
    /**
     * 作业类型
     */
    private Integer homeworkType;

    /**
     * 作业数量
     */
    private Integer num;
    /**
     * 朗读作业作业集合
     */
    OralLanguage list;

}
