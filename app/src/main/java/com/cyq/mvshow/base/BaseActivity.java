package com.cyq.mvshow.base;

import android.app.Activity;
import android.os.Bundle;

import com.umeng.message.PushAgent;

/**
 * Created by win7 on 2016/10/25.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
    }
}
