package com.example.utils;



/**
 * 作者：hzh on 2021/12/10 11:04
 * 邮箱：565150953qq.com
 * 备注：socket管理工具
 */
public class SocketManageUtils {
//    settingTimes = DaoDBUtils.getDaoSession().getSettingDao().load(Constants.Settings.KEY_TEST_IGNORE_TIME + 8);//获取忽略时间
//            if (settingTimes == null) {
//        settingTimes = new Setting();

    // 、public static  int TIME_LIMIT=1000;
    /**
     * hex转byte数组
     * @param hex
     * @return
     */
    public static byte[] hexToByte(String hex) {
        int m = 0, n = 0;
        int byteLen = hex.length() / 2; // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte) intVal);
        }
        return ret;
    }
    //将byte[]转成String
    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return "00";
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    // 拼接字节组btA 和btB =btZ
    public static byte[] addTwoArray(byte[] btX, byte[] btY){
        //定义目标数组  目标数组应该等于将要拼接的两个数组的总长度
        byte[] btZ = new byte[btX.length + btY.length];

        System.arraycopy(btX, 0, btZ, 0, btX.length);
        System.arraycopy(btY, 0, btZ, btX.length, btY.length);
        return btZ;
    }

}
