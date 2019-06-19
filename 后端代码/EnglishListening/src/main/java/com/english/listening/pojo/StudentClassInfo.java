package com.english.listening.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 17:45
 */
@Data
public class StudentClassInfo {
    private Integer id;
    private Integer studentId;
    private Integer teacherId;
    private Integer classId;
    private Date createTime;
    private Date updateTime;
}
