package com.cyq.mvshow.mode;

import java.io.Serializable;

/**
 * Created by win7 on 2016/10/27.
 */

public class GalleryKind implements Serializable {
    /**
     * description : 性感美女
     * id : 1
     * keywords : 性感美女
     * name : 性感美女
     * seq : 1
     * title : 性感美女
     */
    private int id;
    private String name;
    private String title;
    private String keywords;
    private String description;
    private int seq;//排序 从0。。。。10开始
    private String src; //图片地址

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
    /* {"status":true,"tngou":[
        {"description":"……","id":1,"keywords":"……","name":"性感美女","seq":1,"title":"……"},
        {"description":"……","id":2,"keywords":"……","name":"韩日美女","seq":2,"title":"……"},
        {"description":"……","id":3,"keywords":"……","name":"丝袜美腿","seq":3,"title":"……"},
        {"description":"……","id":4,"keywords":"……","name":"美女照片","seq":4,"title":"……"},
        {"description":"……","id":5,"keywords":"……","name":"美女写真","seq":5,"title":"……"},
        {"description":"……","id":6,"keywords":"……","name":"清纯美女","seq":6,"title":"……"},
        {"description":"……","id":2,"keywords":"……","name":"性感车模","seq":7,"title":"……"}
        ]}*/
}