package com.example.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

/**
 * 作者：hzh on 2022/4/11 15:07
 * 邮箱：565150953qq.com
 * 备注：判断网络连接是否可用
 */
public class Network {

    /**
      *判断网络连接是否可用
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context

                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) {

        } else {

            //如果仅仅是用来判断网络连接

            //则可以使用 cm.getActiveNetworkInfo().isAvailable();

            @SuppressLint("MissingPermission") NetworkInfo[] info = cm.getAllNetworkInfo();

            if (info != null) {

                for (int i = 0; i < info.length; i++) {

                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                        return true;

                    }

                }

            }

        }

        return false;

    }


    /*
     * 判断当前是否是wifi
     */
    private static boolean isWifi(Context mContext) {

        ConnectivityManager connectivityManager = (ConnectivityManager) mContext

                .getSystemService(Context.CONNECTIVITY_SERVICE);

        @SuppressLint("MissingPermission") NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetInfo != null

                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {

            return true;

        }

        return false;

    }


    /**
     * 判断GPS是否打开
     */
    public static boolean isGpsEnabled(Context context) {

        LocationManager lm = ((LocationManager) context

                .getSystemService(Context.LOCATION_SERVICE));

        List accessibleProviders = lm.getProviders(true);

        return accessibleProviders != null && accessibleProviders.size() > 0;

    }

}
