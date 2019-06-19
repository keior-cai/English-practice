package com.english.listening.result;

import lombok.Data;

@Data
public class AudioBean {
    /**
     * 错误信息
     */
    private String err_msg;
    /**
     * 标识
     */
    private String sn;
    /**
     * 错误码
     */
    private Integer err_no;
    /**
     * 结果
     */
    private String result;
}
