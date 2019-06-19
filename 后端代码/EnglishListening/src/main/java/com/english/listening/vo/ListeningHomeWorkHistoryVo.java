package com.english.listening.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Gentle
 * @date 2019/01/01 13:45
 */
@Data
public class ListeningHomeWorkHistoryVo {

    private Integer id;

    /**
     * 总听力做题数
     */
    private Integer total;
    /**
     * 总得分
     */
    private Integer scope;
    /**
     * 回答的题目数
     */
    List<StudentListeningSubjectHistoryVo> listeningSubjectHistoryVos;

}
