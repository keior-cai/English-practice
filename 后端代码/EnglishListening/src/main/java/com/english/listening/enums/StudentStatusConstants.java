package com.english.listening.enums;

import lombok.Getter;
/**
 * 学生在校状态
 * @author CCJ
 * @date 2018/11/21 19:03
 **/
@Getter
public enum  StudentStatusConstants {
    STUDENT_STATUS_IN(0,"在读"),
    STUDENT_STATUS_OUT(1,"离校"),
    STUDENT_STATUS_LESS(2,"离校"),
    STUDENT_STATUS_LESS_STUDY(3,"退学")
    ;
    private Integer code;
    private String message;
    StudentStatusConstants(Integer code,String message){
        this.code = code;
        this.message = message;
    }

}
