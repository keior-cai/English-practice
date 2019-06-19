package com.english.listening.utils;

import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @date 2018/11/13
 * @author Gentle
 */
public class XssHandler {


    public static void main(String[] args) throws Exception {
//        StudentInfoForm studentInfoForm = new StudentInfoForm();
//        studentInfoForm.setSiIdcard("使用javascript脚本：" + "<scrpit>123</script>");
//        studentInfoForm.setSiName("使用非法sql注入类字符：" + "#   %  <>");
//        System.out.println(studentInfoForm.getSiIdcard());
//        Object o = checkString(studentInfoForm);
//        System.out.println(o);
//        String[] a = new String[1];
//        a[0]="<script>";
//        checkString(a);
//        System.out.println(a[0]);
    }

    /**
     * 校验数组，并且进行过滤
     * @param strings
     * @return
     */

    public static String[] checkString(String[] strings) {

        if (strings == null || strings.length <= 0) {
            throw new NullPointerException("传入数组不能为空");
        }
        for (int i = 0; i < strings.length; i++) {
            strings[i]=xssEncode(strings[i]);
        }
        return strings;
    }

    /**
     *  校验对象中String类型的字段。并且进行过滤
     * @param o
     * @return
     * @throws Exception
     */

    public static Object checkString(Object o) throws Exception {

        if (o instanceof Collection) {
            throw new RuntimeException("传入的类型有误，不能为集合类型");
        }

        Class<?> aClass = o.getClass();

        Field[] field = aClass.getDeclaredFields();
        List<String> list = new ArrayList();
        //找出所有String类型的字段
        for (Field field1 : field) {
            if (field1.getType().equals(String.class)) {
                list.add(field1.getName());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            //获取get方法
            Method declaredMethod = aClass.getDeclaredMethod(getGetMethod(list.get(i)));
            //用get方法拿到属性值
            Object o1 = declaredMethod.invoke(o);
            //获得set方法。注入一般是字符串类型。所以写死String了
            Method declaredMethod1 = aClass.getDeclaredMethod(getSetMethod(list.get(i)), String.class);
            //用set方法将拿到的属性值进行过滤后放回
            declaredMethod1.invoke(o, new String[]{xssEncode(String.valueOf(o1))});
        }

        return o;
    }

    /**
     *  获得set方法。
     * @param key
     * @return
     * @throws Exception
     */
    private static String getSetMethod(String key) throws Exception {

        return "set" + Character.toUpperCase(key.charAt(0)) + key.substring(1);
    }

    private static String getGetMethod(String key) throws Exception {

        return "get" + Character.toUpperCase(key.charAt(0)) + key.substring(1);
    }


    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     * @param s
     * @return
     */
    private static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
                case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;
                case '&':
                    sb.append('＆');// 全角
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '#':
                    sb.append('＃');// 全角井号
                    break;
                case '%':    // < 字符的 URL 编码形式表示的 ASCII 字符（十六进制格式） 是: %3c
                    processUrlEncoder(sb, s, i);
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public static void processUrlEncoder(StringBuilder sb, String s, int index) {
        if (s.length() >= index + 2) {
            if (s.charAt(index + 1) == '3' && (s.charAt(index + 2) == 'c' || s.charAt(index + 2) == 'C')) {    // %3c, %3C
                sb.append('＜');
                return;
            }
            if (s.charAt(index + 1) == '6' && s.charAt(index + 2) == '0') {    // %3c (0x3c=60)
                sb.append('＜');
                return;
            }
            if (s.charAt(index + 1) == '3' && (s.charAt(index + 2) == 'e' || s.charAt(index + 2) == 'E')) {    // %3e, %3E
                sb.append('＞');
                return;
            }
            if (s.charAt(index + 1) == '6' && s.charAt(index + 2) == '2') {    // %3e (0x3e=62)
                sb.append('＞');
                return;
            }
        }
        sb.append(s.charAt(index));
    }

}