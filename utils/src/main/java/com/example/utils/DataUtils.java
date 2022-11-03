package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：hzh on 2022/1/10 16:42
 * 邮箱：565150953qq.com
 * 备注：
 */
public class DataUtils {
    public static   String yyyy;
    public static String MM;
    public static String dd;
    public static String HH;
    public static String mm;
    public static String ss;
    public static String sss;
    //获取时间
    public static void   getTime(Long timeStamp){
        //传入时间戳即可
            //yyyy-MM-dd HH:mm:ss 转换的时间格式  可以自定义
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss:sss");
            //转换
            String time = sdf.format(new Date(timeStamp));
            String[] times=  time.split(":");
            yyyy=times[0];
            MM=times[1];
            dd=times[2];
            HH=times[3];
            mm=times[4];
            ss=times[5];
            sss=times[6];
    }

    public static String getyyYy() {
        return TcpDataUtils.getTimetoString(Integer.parseInt(yyyy));
    }

    public static String getMM() {
        return ByteUtil.decimal2fitHex(Integer.parseInt(MM),2);
    }

    public static String getDd() {
        return ByteUtil.decimal2fitHex(Integer.parseInt(dd),2);
    }

    public static String getHH() {
        return ByteUtil.decimal2fitHex(Integer.parseInt(HH),2);
    }

    public static String getMm() {
        return ByteUtil.decimal2fitHex(Integer.parseInt(mm),2);
    }

    public static String getSs() {
        return ByteUtil.decimal2fitHex(Integer.parseInt(ss),2);
    }

    public static String getSss() {
        return TcpDataUtils.getTimetoString(Integer.parseInt(sss));
    }
}
