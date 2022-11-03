package com.example.utils;

import android.util.Log;

public class NoDoubleClickUtils {
    private static long lastClickTime;
    //这里设定两次点击时的时间间隔
    private final static int SPACE_TIME = 1000;
    private  static int lastButtonId;

    public static void recordLastClickTime() {
        lastClickTime = 0;
    }

    public synchronized static boolean isDoubleClick  () {
        long currentTime = System.currentTimeMillis();
        boolean isClick2=true;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        return isClick2;
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     * @param diff
     * @return
     * */
    public static boolean isFastDoubleClick(int buttonId, long diff){
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        Log.i("xxxx", "lastButtonId = " + lastButtonId + "  buttonId = " + buttonId);
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff){
            Log.i("isFastDoubleClick", "短时间内按钮多次触发");
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }


}
