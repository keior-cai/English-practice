package com.english.listening.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 17:40
 */
@Data
public class HomeWorkHistory {

    private Integer id;
    /**
     * 听力的标识
     */
    private Integer listeningId;
    /**
     * 学生标识
     */
    private String studentId;
    /**
     * 正确答案
     */
    private String answer;
    /**
     * 用户的回答
     */
    private String userAnswer;
    private Date createTime;


}
