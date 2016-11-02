package com.cyq.mvshow.utils;

import android.content.Context;
import android.text.TextUtils;

import com.cyq.mvshow.base.BaseAbstractListener;
import com.cyq.mvshow.base.BaseInterfaceListener;
import com.cyq.mvshow.manager.SharedPreferencesManager;
import com.cyq.mvshow.mode.VersionInfo;
import com.cyq.mvshow.server.UpdateWorker;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by win7 on 2016/9/26.
 */
public class UpdateUtil {
    private static final String TAG = UpdateUtil.class.getSimpleName();

    /**
     * 检查是否需要更新
     *
     * @return true表示需要更新，false表示不需要更新
     */
    public static boolean checkVersionBySP(Context context) {
        String lastVersionName = SharedPreferencesManager.getLastVersionName();
        if (TextUtils.isEmpty(lastVersionName)) {
            return false;
        }
        return checkVersionByVersionName(lastVersionName, context);
    }


    public static void checkVersionByRequest(final Context context, final BaseInterfaceListener<Boolean, Exception> listener) {
        UpdateUtil.getRemoteVersionInfo(new BaseAbstractListener<VersionInfo, Exception>() {
            @Override
            public void success(VersionInfo versionInfo) {
                super.success(versionInfo);
                String versionName = versionInfo.getVersionShort();
                // 将获取的最新版本信息存储到本地SP中
                SharedPreferencesManager.setLastVersionName(versionName);
                listener.success(checkVersionByVersionName(versionName, context));
            }

            @Override
            public void fail(Exception o) {
                super.fail(o);
                listener.fail(o);
            }
        });

    }

    /**
     * 是否需要更新,是根据versionName
     *
     * @param versionInSP 存储在SharedPreferences中的版本信息
     * @param context
     * @return true表示需要更新，false表示不需要更新
     */
    private static boolean checkVersionByVersionName(String versionInSP, Context context) {
        if (TextUtils.isEmpty(versionInSP)) {
            return false;
        } else {
            int compareResult = versionInSP.compareTo(AppUtils.getAppVerName(context));
            if (compareResult <= 0) {
                //如果本地存储的版本信息小于当前App的版本信息，则表示不需要更新
                return false;
            } else {
                return true;
            }
        }
    }

    private static void getRemoteVersionInfo(final BaseInterfaceListener<VersionInfo, Exception> listener) {
        UpdateWorker.getRemoteVersionName(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtil.d(TAG, "s :" + s);
                DataUtils.stringConvertClass(s, VersionInfo.class, listener);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                listener.fail(e);
            }
        });
    }
}
