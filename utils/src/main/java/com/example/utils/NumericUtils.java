package com.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：hzh on 2021/12/28 20:38
 * 邮箱：565150953qq.com
 * 备注：
 */
public class NumericUtils {
    //判断是否是数字
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
