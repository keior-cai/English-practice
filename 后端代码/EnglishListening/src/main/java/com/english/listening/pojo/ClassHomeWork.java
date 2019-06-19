package com.english.listening.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 14:17
 */
@Data
public class ClassHomeWork {
    /**
     * 作业id
     */
    private Integer homeworkId;
    /**
     * 作业类型
     */
    private Integer homeworkType;
    /**
     * 朗读库 的标识
     */
    private Integer oralLanguageId;
    /**
     * 课程id
     */
    private Integer classId;
//    /**
//     * 听力作业id
//     */
//    private Integer listeningId;
    private Date createTime;
    private Date updateTime;
    /**
     * 作业数量
     */
    private Integer num;
}
