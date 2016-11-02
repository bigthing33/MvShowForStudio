package com.cyq.mvshow.manager;

/**
 * Created by win7 on 2016/11/2.
 */

public class EventMessageManager {
    /**
     * 基本的StringMessage
     */
    public static class BaseStringMessag {
        public String message;

        public BaseStringMessag(String message) {
            this.message = message;
        }
    }

    /**
     * 接收到友盟的更新推送时发送的消息
     */
    public static class UpdateAppMessage extends BaseStringMessag {

        public UpdateAppMessage(String versionName) {
            super(versionName);
        }
    }
}
