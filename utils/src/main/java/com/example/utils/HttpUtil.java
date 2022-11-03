package com.example.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpUtil
{
    private final static int CONNENT_TIMEOUT = 10000;
    private final static int READ_TIMEOUT = 10000;
    static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    /**
     *
     * @function trustAllHosts
     * @Description 信任所有主机-对于任何证书都不做检查
     */
    private static void trustAllHosts()
    {
        TrustManager[] arrayOfTrustManager = new TrustManager[1];
        //实现自己的信任管理器类
        arrayOfTrustManager[0] = new X509TrustManager()
        {

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException
            {
                // TODO Auto-generated method stub

            }

            @Override
            public X509Certificate[] getAcceptedIssuers()
            {
                // TODO Auto-generated method stub
                return new X509Certificate[0];
            }

        };
        try
        {
            SSLContext localSSLContext = SSLContext.getInstance("TLS");
            localSSLContext.init(null, arrayOfTrustManager, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
            return;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }
    /**
     *
     * @function
     * @Description  https post方法，返回值是https请求
     * @param httpsurl
     * @param data
     * @return
     */
    public static String HttpsPost(String httpsurl, String data) {
        String result = null;
        HttpURLConnection http = null;
        URL url;
        try {
            url = new URL(httpsurl);
            // 判断是http请求还是https请求
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                http = (HttpsURLConnection) url.openConnection();
                ((HttpsURLConnection) http).setHostnameVerifier(DO_NOT_VERIFY);// 对所有主机都进行确认
            } else {
                http = (HttpURLConnection) url.openConnection();
            }

            http.setConnectTimeout(CONNENT_TIMEOUT);// 设置超时时间
            http.setReadTimeout(READ_TIMEOUT);
            if (data == null) {
                http.setRequestMethod("GET");// 设置请求类型
                http.setDoInput(true);
                http.setRequestProperty("Content-Type", "text/xml");
            } else {
                http.setRequestMethod("POST");// 设置请求类型为post
                http.setDoInput(true);
                http.setDoOutput(true);
                http.setRequestProperty("Content-Type", "text/xml");
                DataOutputStream out = new DataOutputStream(
                        http.getOutputStream());
                out.writeBytes(data);
                out.flush();
                out.close();
            }

            // 设置http返回状态200（ok）还是403
            int responseCode = http.getResponseCode();
            BufferedReader in = null;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(
                        http.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(
                        http.getErrorStream()));
            }
            String temp = in.readLine();
            while (temp != null) {
                if (result != null) {
                    result += temp;
                } else {
                    result = temp;
                }
                temp = in.readLine();
            }
            in.close();
            http.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}