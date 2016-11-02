package com.cyq.mvshow.utils;

import android.net.Uri;

import com.cyq.mvshow.other.MyConstants;
import com.cyq.mvshow.other.MyUrl;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by win7 on 2016/10/28.
 */

public class ImageUtils {
    public static void setImageByUrl(SimpleDraweeView simpleImage, String url) {
        if (MyConstants.isShowMVFromSkyDog) {
            url = MyUrl.TIANGOU_IMAGE_PATH + url;
        } else {
            url = "http://imgbbs.heiguang.net/forum/201510/06/104432cjc7c8tx7xxqqkgq.jpg";
        }
        Uri uri = Uri.parse(url);
        simpleImage.setImageURI(uri);
        //创建DraweeController
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //加载的图片URI地址
                .setUri(uri)
                //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                //设置旧的Controller
                .setOldController(simpleImage.getController())
                //构建
                .build();
        //设置DraweeController
        simpleImage.setController(controller);
    }
}
