package com.english.listening.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 14:12
 */
@Data
public class TeacherInfo {

    private Integer teacherId;

    private Integer sex;
    /**
     * 老师编号
     */
    private String teacherNumber;
    /**
     * 老师密码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherPassword;
    /**
     * 老师身份证
     */
    private String teacherIdcard;
    /**
     * 老师名字
     */
    private String teacherName;
    /**
     * 老师年龄
     */
    private Integer age;
    private Date createTime;
    private Date updateTime;

}
