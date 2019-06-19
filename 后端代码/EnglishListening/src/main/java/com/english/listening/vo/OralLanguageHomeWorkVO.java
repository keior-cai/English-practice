package com.english.listening.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @ClassName OralLanguageHomeWorkVO
 * @Description
 * @Author CCJ
 * @Date 2019/1/10 17:09
 **/
@Data
public class OralLanguageHomeWorkVO {

    private Integer sex;

    private String sno;

    private String name;

    private String longText;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String oralLanguageText;

    private Integer status = 0;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer studentHomeWorkScore;

    private String homeWorkPath;


}
