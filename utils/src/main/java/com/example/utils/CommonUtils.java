package com.example.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

/**
 * Android6.0以后动态权限的申请
 */
public class CommonUtils {
    public static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE }; //Android6.0以后操作系统的动态权限申请


    /**
     * 用于Android6.0以后的操作系统，动态申请存储的读写权限
     * @param context
     */
    public static void requestPermissions(Activity context){
        //用于Android6.0以后的操作系统，动态申请存储的读写权限
        int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(context,PERMISSIONS_STORAGE, 1 );
        }
    }

}
