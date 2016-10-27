package com.cyq.mvshow.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cyq.mvshow.MyApplication;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by win7 on 2015/12/31.
 */
public class PhoneUtils {
    public static HashMap<String, String> getInfo() {
        HashMap<String, String> mapInfo = new HashMap<>();
        mapInfo.put("Model", android.os.Build.MODEL); // 手机型号
        mapInfo.put("Brand", android.os.Build.BRAND);//手机品牌
        return mapInfo;
    }

    /**
     * 检测手机网络是否可用
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 检测手机网络是否链接上wifi
     *
     * @return
     */
    public static boolean isWiFiConnected() {
        ConnectivityManager manager = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null && wifiInfo.isConnected();
    }

    /**
     * 判断手机设置的语言是否是中文
     *
     * @return
     */
    public static boolean isZh() {
        Locale locale = MyApplication.getInstance().getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }
}
