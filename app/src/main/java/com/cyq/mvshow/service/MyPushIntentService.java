package com.cyq.mvshow.service;

import android.content.Context;
import android.content.Intent;

import com.cyq.mvshow.manager.EventMessageManager;
import com.cyq.mvshow.manager.SharedPreferencesManager;
import com.cyq.mvshow.utils.LogUtil;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageService;
import com.umeng.message.entity.UMessage;

import org.android.agoo.common.AgooConstants;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * Created by win7 on 2016/9/26.
 */
public class MyPushIntentService extends UmengMessageService {
    private static final String TAG = MyPushIntentService.class.getName();

    @Override
    public void onMessage(Context context, Intent intent) {
        try {
            //可以通过MESSAGE_BODY取得消息体
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            UMessage msg = new UMessage(new JSONObject(message));
            LogUtil.d(TAG, "message=" + message);      //消息体
            LogUtil.d(TAG, "custom=" + msg.custom);    //自定义消息的内容
            LogUtil.d(TAG, "title=" + msg.title);      //通知标题
            LogUtil.d(TAG, "text=" + msg.text);        //通知内容
            LogUtil.d(TAG, "extra=" + msg.extra);        //通知内容
            // code  to handle message here
            // ...

            // 对完全自定义消息的处理方式，
            boolean isClickOrDismissed = true;
            if (isClickOrDismissed) {
                //完全自定义消息的点击统计
                UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
            } else {
                //完全自定义消息的忽略统计
                UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
            }

            // 使用完全自定义消息来开启应用服务进程的示例代码
            // 首先需要设置完全自定义消息处理方式
            // mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
            // code to handle to start/stop service for app process
            JSONObject json = new JSONObject(msg.extra);
            if (json.has("versionName")){
                String versionName = json.getString("versionName");
                SharedPreferencesManager.setLastVersionName(versionName);
                //通知主页检查更新
                EventBus.getDefault().post(new EventMessageManager.UpdateAppMessage(versionName));
            }
            LogUtil.d(TAG, "versionName=" + json.getString("versionName"));

        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }
    }
}