package com.english.listening.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: Gentle
 * @date 2019/01/01 00:23
 */
@Data
public class ListeningHomeWorkVo {

    /**
     * 作业id
     */
    private Integer homeworkId;
    /**
     * 作业类型
     */
    private Integer homeworkType;

    /**
     * 题目数量
     */
    private Integer num;
}
