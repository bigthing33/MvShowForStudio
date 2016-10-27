package com.cyq.mvshow.server;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.AbsCallback;

/**
 * Created by win7 on 2016/10/27.
 */

public class BaseWorker {
    public static void getRequest(String url, String tag, AbsCallback callBack) {
        OkGo.get(url)     // 请求方式和请求url
                .tag(tag)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(callBack);

    }
    public static void getRequest(String url,  AbsCallback callBack) {
        OkGo.get(url)     // 请求方式和请求url
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(callBack);

    }
}
