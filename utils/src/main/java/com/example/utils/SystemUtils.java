package com.example.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.List;

public final class SystemUtils {
    /**
     * 获取本机IP地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            String ipv4 = "";
            String ipv6 = "";
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        List<InterfaceAddress> listIa = intf.getInterfaceAddresses();
                        for (int i = 0; i < listIa.size(); i++) {
                            if (listIa.get(i).getNetworkPrefixLength() <= 32) { //IP V4
                                ipv4 = listIa.get(i).getAddress().getHostAddress();
                                return ipv4;
                            } else if (listIa.get(i).getNetworkPrefixLength() == 64) { //IP V6
                                ipv6 = listIa.get(i).getAddress().getHostAddress();
                            }
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(ipv6)) {
                return ipv6;
            }
        } catch (SocketException ex) {
        }
        return "";
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static String getDiskSpace(Context context) {
        try {
            StatFs statFs = new StatFs("/sdcard");
            long blockSize;
            long totalBlocks;
            long availableBlocks;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = statFs.getBlockSizeLong();
                totalBlocks = statFs.getBlockCountLong();
                availableBlocks = statFs.getAvailableBlocksLong();

            } else {
                blockSize = statFs.getBlockSize();
                totalBlocks = statFs.getBlockCount();
                availableBlocks = statFs.getAvailableBlocks();

            }

            String useDiskSpace = Formatter.formatFileSize(context, (totalBlocks - availableBlocks) * blockSize);
            String totalDiskSpace = Formatter.formatFileSize(context, totalBlocks * blockSize);
            float percent = (float) (availableBlocks * 1.0 / totalBlocks) * 100;

            DecimalFormat fnum = new DecimalFormat("##0.0");
            String percentStr = fnum.format(percent);

            return "存储空间\n" + useDiskSpace + "/" + totalDiskSpace + "\n" + percentStr+ "%";
            //return new Object[]{percentStr.concat("%"), useDiskSpace.concat("/").concat(totalDiskSpace).replace("B", ""), percent};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
