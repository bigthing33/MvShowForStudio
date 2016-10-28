package com.cyq.mvshow.utils;

import android.net.Uri;

import com.cyq.mvshow.other.MyConstants;
import com.cyq.mvshow.other.MyUrl;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by win7 on 2016/10/28.
 */

public class ImageUtils {
    public static void setImageByUrl(SimpleDraweeView simpleImage, String url) {
        if (MyConstants.isDebug) {
            url = "http://imgbbs.heiguang.net/forum/201510/06/104432cjc7c8tx7xxqqkgq.jpg";
        } else {
            url = MyUrl.TIANGOU_IMAGE_PATH + url;
        }
        Uri uri = Uri.parse(url);
        simpleImage.setImageURI(uri);
    }
}
