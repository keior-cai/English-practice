package com.english.listening.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName OralLanguage
 * @Description
 * @Author CCJ
 * @Date 2019/1/1 16:15
 **/
@Data
public class OralLanguage {
    private Integer oralLanguageId;

    private String oralLanguageText;

    private Date createTime;

    private Date updateTime;
}
