package com.example.utils;

/**
 * 作者：hzh on 2021/12/27 16:41
 * 邮箱：565150953qq.com
 * 备注：获得卡号
 */
public class CardUtils {
    //获得卡号
    public static String getCard(byte[] cardhex) {
        if(cardhex.length!=4){
            return "";
        }
        byte[] card = new byte[4];
        card[0] = cardhex[3];
        card[1] = cardhex[2];
        card[2] = cardhex[1];
        card[3] = cardhex[0];

        String cardStr = Long.parseLong(SocketManageUtils.byteArrayToHexStr(card), 16) + "";
       // Log.e(  "获取卡号:", cardStr);
        return cardStr;
    }
}
