package com.cyq.mvshow.server;

import android.content.Context;

import com.cyq.mvshow.base.BaseInterfaceListener;
import com.cyq.mvshow.mode.Galleries;
import com.cyq.mvshow.mode.GalleryKinds;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by win7 on 2016/10/28.
 */

public class TianGouDataLoader {

    public static void init(Context context) {
    }

    /**
     * 加载图片分类数据
     */
    public static void getGalleryKinds(final BaseInterfaceListener<GalleryKinds, Exception> listener) {
        TianGouWorker.get_kinds(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                GalleryKinds galleryKinds = gson.fromJson(s, GalleryKinds.class);
                listener.success(galleryKinds);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                listener.fail(e);
            }
        });
    }

    /**
     * 加载图片列表
     */
    public static void getGalleries(int page, int rows, int id, final BaseInterfaceListener<Galleries, Exception> listener) {
        TianGouWorker.get_list(page, rows, id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                Galleries galleries = gson.fromJson(s, Galleries.class);
                listener.success(galleries);

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                listener.fail(e);
            }
        });
    }
    /**
     * 加载最新图片
     */
    public static void getGalleriesNews( int rows, int classify,long id, final BaseInterfaceListener<Galleries, Exception> listener) {
        TianGouWorker.get_news(rows, classify,id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                Galleries galleries = gson.fromJson(s, Galleries.class);
                listener.success(galleries);

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                listener.fail(e);
            }
        });
    }
}
