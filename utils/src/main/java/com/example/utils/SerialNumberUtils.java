package com.example.utils;

/**
 * 获取流水号工具
 */
public class SerialNumberUtils {


    public static StringBuilder serialNumber(StringBuilder str, String fd, String endA5) {
        if (str.indexOf(fd) % 2 == 0) {
            str = str.replace(str.indexOf(fd), str.indexOf(fd) + 4, endA5);
            serialNumber(str,fd,endA5);
        }
        return str;
    }



    //获取数据长度
    private static int dataLength(String dat) {

        int index0 = (1 + 4 + 16 + 4 + 1 + 1 + 1) * 2;
        if (dat.length() < index0) {
            return -1;
        }
        //A5 52 00 23 32 09 10 00 00 30 9D A5 29 84 2A 57 A7 EF 18 99 19 11 12 13 14 0A 01 00 00 00 00 02 61 08 36 5A
        String data = dat.substring(index0, index0 + 4 * 2);//72

        int dataLength = Integer.parseInt(data.substring(0, 2), 16) + Integer.parseInt(data.substring(2, 4), 16) + Integer.parseInt(data.substring(4, 6), 16) + Integer.parseInt(data.substring(6, 8), 16);
        return dataLength;
    }

    //获取卡号
    public static String getCard(String data) {
        String dat = data;
        StringBuilder str = new StringBuilder(dat);
        if (str.indexOf("FD00") % 2 == 0) {
            str = str.replace(str.indexOf("FD00"), str.indexOf("FD00") + 4, "A5");
            getCard(str.toString());
        }
        if (str.indexOf("FD01") % 2 == 0) {
            str = str.replace(str.indexOf("FD01"), str.indexOf("FD01") + 4, "5A");
            getCard(str.toString());
        }
        if (str.indexOf("FD02") % 2 == 0) {
            str = str.replace(str.indexOf("FD02"), str.indexOf("FD02") + 4, "FD");
            getCard(str.toString());
        }

        String s = str.substring(76, 84);

        //   byte[] sss=IntByteStringHexUtil.hexToByte1(ss);

        return getCard2(s) + "";
    }

    public static Long getCard2(String card) {
        String ss = card.substring(6) + card.substring(4, 6) + card.substring(2, 4) + card.substring(0, 2);
        long cards = Long.parseLong(ss, 16);
        return cards;
    }

    //获取key - value
    public static String getCardKV(String dat) {
        //A5 52002332 09100000309DA529842A57A7EF189919 11121314 0A030000000017 00C0090006A98565F70A9561586D5F000271AF0B0000007A5A
        StringBuilder str = new StringBuilder(dat);
        if (str.indexOf("FD00") % 2 == 0) {
            str = str.replace(str.indexOf("FD00"), str.indexOf("FD00") + 4, "A5");
            getCardKV(str.toString());
        }
        if (str.indexOf("FD01") % 2 == 0) {
            str = str.replace(str.indexOf("FD01"), str.indexOf("FD01") + 4, "A5");
            getCardKV(str.toString());
        }
        if (str.indexOf("FD02") % 2 == 0) {
            str = str.replace(str.indexOf("FD02"), str.indexOf("FD02") + 4, "A5");
            getCardKV(str.toString());
        }
        //A5 52002332 09100000309DA529842A57A7EF189919 11121314 0A0300 00000017 00C0090006A98565F70A95  61586D5F 000271AF 0B0000007A5A
        String s = str.substring(56, 64);
        long sss = Long.parseLong(s, 16);
        long time = 0;
        long num = 0;
        if (sss > 15) {
            time = Long.parseLong(str.substring(str.length() - 20, str.length() - 12), 16);
            num = Long.parseLong(str.substring(str.length() - 12, str.length() - 10), 16);
        }
        return time + ";" + num;
    }

}
