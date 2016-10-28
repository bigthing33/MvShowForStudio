package com.cyq.mvshow.callback;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.cyq.mvshow.utils.DialogUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/4/8
 * 描    述：我的Github地址  https://github.com/jeasonlzy0216
 * 修订历史：
 * ================================================
 */
public abstract class StringDialogCallback extends StringCallback {

    private Activity mActivity;

    public StringDialogCallback(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
        DialogUtils.showProgressDialogWithoutRate(mActivity);
    }

    @Override
    public void onAfter(@Nullable String s, @Nullable Exception e) {
        super.onAfter(s, e);
        //网络请求结束后关闭对话框
        DialogUtils.dismissProgressDialogWithoutRate();
    }
}
