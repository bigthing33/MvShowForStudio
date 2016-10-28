package com.cyq.mvshow.other;

public class MyUrl extends MyConstants {
    /*
     * 百度apistore的服务头
     */
    public static final String BAIDU_SERVICE = "http://apis.baidu.com/";
    /*
     * 天狗开放阅图的服务头
     * https://my.oschina.net/mynote/blog/598487
     */
    public static final String TIANGOU_IMAGE_PATH = "http://tnfs.tngou.net/image";//可以再图片后面添加宽度和高度，如：http://tnfs.tngou.net/image/top/default.jpg_180x120
    public static final String TIANGOU_IMAGE_PATH2 = "http://tnfs.tngou.net/img";
    /**
     * 天狗图片加载地址，后面接具体的图片
     */
    public static final String TIANGOU_SERVICE = "http://www.tngou.net/tnfs/api/";
    /*
     * 天狗开放阅图的图片分类
     */
    public static final String SEARCH_TIANGOU_CLASSIFY = TIANGOU_SERVICE + "classify";
    /*
     * 天狗开放阅图的图片类别下的列表
     */
    public static final String SEARCH_TIANGOU_LIST = TIANGOU_SERVICE + "list";
    /*
     * 天狗开放阅图的最新图库
     */
    public static final String SEARCH_TIANGOU_NEWS = TIANGOU_SERVICE + "news";
    /*
     * 天狗开放阅图的图库详情
     */
    public static final String SEARCH_TIANGOU_DETAILS = TIANGOU_SERVICE + "show";
    /*
     * 天狗开放阅图的图片收藏接口
     */
    public static final String SEARCH_TIANGOU_FAVORITE = TIANGOU_SERVICE + "favorite/add";
    /*
     * 天狗开放阅图评论接口，需要用户认证
     */
    public static final String SEARCH_TIANGOU_MEMO_ADD = TIANGOU_SERVICE + "memo/add";
    /*
     * 天狗开放阅图 图片评论列表
     */
    public static final String SEARCH_TIANGOU_MEMO_COMMENT = TIANGOU_SERVICE + "memo/comment";




        /*
     * 百度文本搜图
	 */

    public static final String SEARCH_IMAGE = BAIDU_SERVICE + "image_search/search/search";

}
