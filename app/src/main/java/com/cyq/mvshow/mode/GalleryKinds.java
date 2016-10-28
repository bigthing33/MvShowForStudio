package com.cyq.mvshow.mode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 2016/10/28.
 */

public class GalleryKinds implements Serializable {

    /**
     * status : true
     * tngou : [{"description":"性感美女","id":1,"keywords":"性感美女","name":"性感美女","seq":1,"title":"性感美女"},{"description":"韩日美女","id":2,"keywords":"韩日美女","name":"韩日美女","seq":2,"title":"韩日美女"},{"description":"丝袜美腿","id":3,"keywords":"丝袜美腿","name":"丝袜美腿","seq":3,"title":"丝袜美腿"},{"description":"美女照片","id":4,"keywords":"美女照片","name":"美女照片","seq":4,"title":"美女照片"},{"description":"美女写真","id":5,"keywords":"美女写真","name":"美女写真","seq":5,"title":"美女写真"},{"description":"清纯美女","id":6,"keywords":"清纯美女","name":"清纯美女","seq":6,"title":"清纯美女"},{"description":"性感车模","id":7,"keywords":"性感车模","name":"性感车模","seq":7,"title":"性感车模"}]
     */

    private boolean status;
    /**
     * description : 性感美女
     * id : 1
     * keywords : 性感美女
     * name : 性感美女
     * seq : 1
     * title : 性感美女
     */

    private List<GalleryKind> tngou=new ArrayList<>();

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<GalleryKind> getGalleryKinds() {
        return tngou;
    }

    public void setGalleryKinds(List<GalleryKind> tngou) {
        this.tngou = tngou;
    }
}
