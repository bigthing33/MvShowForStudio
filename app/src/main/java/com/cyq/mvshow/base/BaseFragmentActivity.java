package com.cyq.mvshow.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.cyq.mvshow.MyApplication;
import com.umeng.message.PushAgent;

/**
 * Created by win7 on 2016/10/25.
 */

public class BaseFragmentActivity extends FragmentActivity {
    public static final String IMAGE_TYPE ="IMAGE_TYPE" ;
    public static final String CLASSIFY ="CLASSIFY" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(MyApplication.getInstance()).onAppStart();
    }
}
