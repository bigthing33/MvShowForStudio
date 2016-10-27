package com.cyq.mvshow.other;

public class MyUrl extends MyConstants {
    /*
     * 百度apistore的服务头
     */
    public static final String BAIDU_SERVICE = "http://apis.baidu.com/";
    /*
     * 天狗开放阅图的服务头
     */
//    public static final String TIANGOU_SERVICE = "http://tnfs.tngou.net/image";
//	public static final String TIANGOU_SERVICE = "http://tnfs.tngou.net/img";
	public static final String TIANGOU_SERVICE = "http://www.tngou.net/tnfs/api/";
    /*
	 * 百度文本搜图
	 */

    public static final String SEARCH_IMAGE = BAIDU_SERVICE + "image_search/search/search";
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

}
