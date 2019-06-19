package com.english.listening.utils;

import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/11/18 16:32
 */
public class IdcardUtils {

    /**
     * 身份证验证
     * @param idCard
     * @return
     */
    public  static boolean isIDCard(String idCard) {
        String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

}
