package com.cyq.mvshow.manager;

import android.text.TextUtils;

import com.cyq.mvshow.MyApplication;
import com.cyq.mvshow.utils.Settings;


public class SharedPreferencesManager {

    private static Settings settings = Settings.getInstance(MyApplication.getInstance().getApplicationContext());

    /**
     * 设置当前网络上最新的版本
     *
     * @param versionName
     */
    public static void setLastVersionName(String versionName) {
        if (!TextUtils.isEmpty(versionName)) {
            settings.set("LastVersionName", versionName);
        }
    }

    public static String getLastVersionName() {
        return settings.get("LastVersionName");

    }

}
