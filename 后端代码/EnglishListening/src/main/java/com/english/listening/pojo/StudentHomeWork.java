package com.english.listening.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 17:58
 */
@Data
public class StudentHomeWork {
    private Integer id;
    /**
     * 作业id
     */
    private Integer classHomeWorkId;
    /**
     * 学生id
     */
    private Integer studentId;
    /**
     *  翻译后的文本（口语的）
     */
    private String  longText;
    /**
     * 作业路径（口语的才写）
     */
    private String  homeWorkPath;
    /**
     * 学生作业完成状态
     */
    private Integer  status;
    /**
     * 学生作业成绩（只有听力的才可以写）
     */
    private Integer studentHomeWorkScore;
    private Date createTime;
    private Date updateTime;


}
