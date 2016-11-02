package com.cyq.mvshow.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.cyq.mvshow.R;
import com.cyq.mvshow.adapter.SimpleFragmentPagerAdapter;
import com.cyq.mvshow.base.BaseFragmentActivity;
import com.cyq.mvshow.manager.EventMessageManager;
import com.cyq.mvshow.manager.SharedPreferencesManager;
import com.cyq.mvshow.utils.DialogUtils;
import com.cyq.mvshow.utils.LogUtil;
import com.cyq.mvshow.utils.UpdateUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseFragmentActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String POSITION = "POSITION";
    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        updateApp();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessageManager.UpdateAppMessage updateAppMessage) {
        updateApp();
    }

    ;

    /**
     * 处理屏幕转换
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    /**
     * 更新App
     */
    private void updateApp() {
        LogUtil.d(TAG, "LastVersionName: " + SharedPreferencesManager.getLastVersionName());
        if (UpdateUtil.checkVersionBySP(this)) {
            //如果需要更新
            DialogUtils.showUpdateDialog(this, SharedPreferencesManager.getLastVersionName());
        }
    }
}
