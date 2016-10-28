package com.cyq.mvshow.mode;

import java.security.Timestamp;

/**
 * Created by win7 on 2016/10/27.
 */

public class  Memo  {
    //total:最新数据总数,，tngou[] 这里返回的是Array
    private long user     ;//          留言的主人
    private String otype         ;//     对象类型
    private long oid     ;//     对象ID
    private String  ptype     ;//      NULL
    private long  pid     ;//       回复
    private String title ;// 回复内容的标题
    private String url;  // 回复内容的URL地址
    private String memo     ;//      内容
    private String img     ;//       图片
    private int rcount     ;//       回复次数
    private Timestamp time ;//          发送时间
    private int isread     ;//              留言状态  1:读取 ，0  有读取
    private String account     ;    //       登录帐号
    private String domain     ;    //           用户主页域名
    private String avatar     ;    //       头像地址
    private int ct;// 通信次数
}
