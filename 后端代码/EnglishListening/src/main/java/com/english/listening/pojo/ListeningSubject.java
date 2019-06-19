package com.english.listening.pojo;

import lombok.Data;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 14:14
 */
@Data
public class ListeningSubject {
    private Integer subjectId;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String subjectTitle;
}
