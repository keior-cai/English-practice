package com.english.listening.vo;

import lombok.Data;

import java.security.PrivateKey;

/**
 * @Description:
 * @Author: Gentle
 * @date 2019/01/02 12:24
 */
@Data
public class OralLanguageIdHistoryVo {


    /**
     * 作业id
     */
    private Integer homeworkId;
    /**
     * 作业类型
     */
    private Integer homeworkType;
    /**
     * 朗读id
     */
    private Integer oralLanguageId;
    /**
     * 作业原文
     */
    private String oralLanguageText;
    /**
     * 用户文本
     */
    private String longText;
    /**
     * MP3路径
     */
    private String homeWorkPath;

}
