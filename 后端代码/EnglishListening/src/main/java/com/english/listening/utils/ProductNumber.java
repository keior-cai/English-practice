package com.english.listening.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: Gentle
 * @date 2018/11/10  20:59
 */
public class ProductNumber {
    private static volatile int student_number=1;


    public static String createNumber(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println( df.format(new Date()).substring(2,4));
        String number =df.format(new Date()).substring(2,4)+"40";
        String str = String.format("%04d", number);
        student_number++;
        return null;
    }

    public static void main(String[] args) {
        createNumber();

    }
}
