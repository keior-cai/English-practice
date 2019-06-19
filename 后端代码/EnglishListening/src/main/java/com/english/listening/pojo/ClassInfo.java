package com.english.listening.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 17:43
 */
@Data
public class ClassInfo {
    /**
     * 课程id
     */
    private Integer classId;
    /**
     * 课程代码
     */
    private String  classNumber;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;




}
