package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static android.content.Context.WIFI_SERVICE;

/**
 * 作者：hzh on 2022/4/11 20:36
 * 邮箱：565150953qq.com
 * 备注：
 */
public class BluetoothUtils {

    public static BluetoothUtils getInstance() {
        return new BluetoothUtils();
    }


    /**
     * 获取Mac地址
     *
     * @return
     */
    public String getMacAddress(Context context) {
        //在Android6.0的版本以后用原来的getMacAddress()方法获取手机的MAC地址都为：02:00:00:00:00:00这个固定的值
        String macDefault="";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
             macDefault = getMacDefault(context);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            macDefault = getMacAddressM();
        } else {
            macDefault = getMacFromHardware();
        }
        return macDefault;

//        String macAddress = getMacAddressByNetworkInterface();
//        if (isAddressNotInExcepts(macAddress, (String[]) null)) {
//            return macAddress;
//        }
//        macAddress = getMacAddressByInetAddress();
//        if (isAddressNotInExcepts(macAddress, (String[]) null)) {
//            return macAddress;
//        }
//        macAddress = getMacAddressByWifiInfo(context);
//        if (isAddressNotInExcepts(macAddress, (String[]) null)) {
//            return macAddress;
//        }
//
//        return null;
    }

    /**
     * 获取设备唯一ID
     * @return
     */
    public static String getDeviceId() {
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
        String serial = null;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            serial = "serial";
        }
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }



    private String getMacFromHardware() {
        String mac = "未获取到设备mac地址";
        try {
            mac = new BufferedReader(new FileReader("/sys/class/net/wlan0/address")).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    private String getMacAddressM() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equals("wlan0") == true) {
                    continue;
                }
                byte[] macBytes = nif.getHardwareAddress() != null ? nif.getHardwareAddress() : null;
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1 != null) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "未获取到mac地址";
    }

    private String getMacDefault(Context context) {
        String mac = "未获取到mac地址";
        if (context == null) {
            return mac;
        }
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo info = null;
        try {
            info = wifi.getConnectionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (info == null) {
            return mac;
        } else {
            mac = info.getMacAddress();
        }
        if (!TextUtils.isEmpty(mac)) {
            mac = mac.toUpperCase(Locale.ENGLISH);
        }
        return mac;
    }

    private boolean isAddressNotInExcepts(final String address, final String... excepts) {
        if (TextUtils.isEmpty(address)) {
            return false;
        }
        if (excepts == null || excepts.length == 0) {
            return !"02:00:00:00:00:00".equals(address);
        }
        for (String filter : excepts) {
            if (address.equals(filter)) {
                return false;
            }
        }
        return true;
    }

    private InetAddress getInetAddress() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                // To prevent phone of xiaomi return "10.0.2.15"
                if (!ni.isUp()) continue;
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (hostAddress.indexOf(':') < 0) return inetAddress;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String uuid;
    private static final String KEY_UDID = "KEY_UDID";

    /// 设置一个 Activity 参数
    private static Activity _unityActivity;

    private static String getMacAddressByWifiInfo(Context context) {
        try {
            final WifiManager wifi = (WifiManager) context
                    .getApplicationContext().getSystemService(WIFI_SERVICE);
            if (wifi != null) {
                final WifiInfo info = wifi.getConnectionInfo();
                if (info != null) return info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }


    private String getMacAddressByInetAddress() {
        try {
            InetAddress inetAddress = getInetAddress();
            if (inetAddress != null) {
                NetworkInterface ni = NetworkInterface.getByInetAddress(inetAddress);
                if (ni != null) {
                    byte[] macBytes = ni.getHardwareAddress();
                    if (macBytes != null && macBytes.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : macBytes) {
                            sb.append(String.format("%02x:", b));
                        }
                        return sb.substring(0, sb.length() - 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByNetworkInterface() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni == null || !ni.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : macBytes) {
                        sb.append(String.format("%02x:", b));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

}
