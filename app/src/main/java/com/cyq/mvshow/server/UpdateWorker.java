package com.cyq.mvshow.server;

import com.lzy.okgo.callback.StringCallback;

/**
 * Created by win7 on 2016/11/1.
 */

public class UpdateWorker extends BaseWorker {
    private static final String TAG = UpdateWorker.class.getSimpleName();
    private static final String FIR_APPID = "581858d9959d696e11000a0f";
    private static final String API_TOKEN = "2b539ef04135a6fe1fd1d0e83a47d62b";
    public static final String DOWNlOAD_URL = "http://fir.im/MvShow";

    /**
     * 获得在fir.im上的版本的信息
     */
    public static void getRemoteVersionName(StringCallback stringCallback) {
        getRequest("http://api.fir.im/apps/latest/" + FIR_APPID + "?api_token=" + API_TOKEN, stringCallback);
    }
}
