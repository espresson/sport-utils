package com.example.utils;

/**
 * 作者：hzh on 2022/1/10 15:53
 * 邮箱：565150953qq.com
 * 备注：
 */
public class TcpDataUtils {

    //获取低八位在前，高八位在后
    public static String getTimetoString(int nian){
        byte[] dd=new byte[2];
        dd[1] = (byte)(nian>>8);//取高八位
        dd[0] = (byte)(nian);
        return  ByteUtil.bytes2HexStr(dd);
    }


}
