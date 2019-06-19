package com.english.listening.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/12/31 14:12
 */
@Data
public class AdminInfo {

    private Integer adminId;
    /**
     * 管理员编号
     */
    private String adminNumber;
    /**
     * 管理员密码
     */
    private String adminPassword;
    /**
     * 管理员名字
     */
    private String adminName;

    private Date createTime;
    private Date updateTime;



}
