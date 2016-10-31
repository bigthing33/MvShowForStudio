package com.cyq.mvshow.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyq.mvshow.utils.LogUtil;

/**
 * Created by win7 on 2016/10/28.
 */

public class BaseFragment extends Fragment {
    public static final String CLASSIFY = "CLASSIFY";
    public static final String IMAGE_TYPE = "IMAGE_TYPE";
    private static final String TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, this.getClass().getSimpleName() + "---------onCreate---------");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, this.getClass().getSimpleName() + "---------onCreateView---------");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d(TAG, this.getClass().getSimpleName() + "---------onActivityCreated---------");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d(TAG, this.getClass().getSimpleName() + "---------onStart---------");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d(TAG, this.getClass().getSimpleName() + "---------onResume---------");
    }
}
