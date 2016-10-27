package com.cyq.mvshow.server;

import com.cyq.mvshow.base.BaseInterfaceListener;
import com.cyq.mvshow.other.MyUrl;
import com.cyq.mvshow.utils.LogUtil;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by win7 on 2016/10/27.
 * 获取天狗资源的一个类
 */

public class TianGouWorker extends BaseWorker {
    private static final String TAG = TianGouWorker.class.getSimpleName();

    /**
     * 获得图片分类数据
     */
    public static void get_kinds(BaseInterfaceListener<JSONObject, Exception> listener) {
        getRequest(MyUrl.SEARCH_TIANGOU_CLASSIFY, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtil.d(TAG, TAG);
            }
        });

    }

    /**
     * 图片列表,取得分类下的图片了吧，通过分类取得相关的图片
     *
     * @param listener page	否	int	请求页数，默认page=1
     *                 rows	否	int	每页返回的条数，默认rows=20
     *                 id	否	int	分类ID，默认返回的是全部。这里的ID就是指分类的ID
     *                 注意
     *                 total返回的是数据总数，而并非返回的条数（返回条数有参数rows决定）
     *
     *                 img字段返回的是不完整的图片路径src，
     *                 需要在前面添加【http://tnfs.tngou.net/image】或者【http://tnfs.tngou.net/img】
     *                 前者可以再图片后面添加宽度和高度，如：http://tnfs.tngou.net/image/top/default.jpg_180x120
     */
    public static void get_list(int page, int rows, String id, BaseInterfaceListener<JSONObject, Exception> listener) {
        getRequest(MyUrl.SEARCH_TIANGOU_LIST + "?page=" + page + "&rows=" + rows + "&id=" + id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtil.d(TAG, TAG);
            }
        });

    }

    /**
     * 最新图片,通过当前最新的ID，取得最新的图库列表。通过该方法可以做到数据的不重复。但改接口也并非一定要用！
     * rows	否	int	返回最新关键词的条数，默认rows=20
     classify	否	int	分类ID，取得该分类下的最新数据
     id	是	long	当前最新的图库关键词id
     */
    public static void get_news(int rows, int classify, long id, BaseInterfaceListener<JSONObject, Exception> listener) {
        getRequest(MyUrl.SEARCH_TIANGOU_NEWS + "?rows=" + rows + "&classify=" + classify + "&id=" + id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtil.d(TAG, TAG);
            }
        });

    }

    /**
     * 图库详情信息，通过热点热词的id，取得数据详情。
     * @param id 	图库的id
     * @param listener
     */
    public static void get_details(long id, BaseInterfaceListener<JSONObject, Exception> listener) {
        getRequest(MyUrl.SEARCH_TIANGOU_DETAILS + "?id=" + id, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtil.d(TAG, TAG);
            }
        });

    }
}
