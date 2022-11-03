package com.example.utils;

/**
 * 作者：hzh on 2022/1/10 14:51
 * 邮箱：565150953qq.com
 * 备注：进制转换
 */
public class HexUtils {
    //十进制转16进制
    public static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char []b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            s = s.append(b[n%16]);
            n = n/16;
        }
        a = s.reverse().toString();
        return a;
    }

}
