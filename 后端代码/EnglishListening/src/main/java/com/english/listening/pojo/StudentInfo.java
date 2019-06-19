package com.english.listening.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 14:10
 */
@Data
public class StudentInfo {
    private Integer studentId;
    private Integer sex;
    private String studentNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentPassword;
    private String studentIdcard;
    private String studentName;
    private Date createTime;
    private Date updateTime;
}
