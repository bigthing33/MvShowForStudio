package com.cyq.mvshow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.cyq.mvshow.R;
import com.cyq.mvshow.adapter.GalleryKindsPagerAdapter;
import com.cyq.mvshow.base.BaseFragmentActivity;
import com.cyq.mvshow.mode.GalleryKinds;
import com.cyq.mvshow.mode.ImageType;

import junit.framework.Assert;

public class GalleriesActivity extends BaseFragmentActivity {
    private static final String POSITION = "POSITION";
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private GalleryKindsPagerAdapter pagerAdapter;
    private ImageType mImageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initView
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //initData
        mImageType = (ImageType) getIntent().getSerializableExtra(IMAGE_TYPE);
        GalleryKinds galleryKinds = (GalleryKinds) getIntent().getSerializableExtra(CLASSIFY);
        int position=getIntent().getIntExtra(POSITION,0)-1;
        Assert.assertNotNull(galleryKinds);
        Assert.assertNotNull(galleryKinds.getGalleryKinds());
        Assert.assertTrue(position<galleryKinds.getGalleryKinds().size());
        pagerAdapter = new GalleryKindsPagerAdapter(getSupportFragmentManager(), this, mImageType);
        pagerAdapter.galleryKinds = galleryKinds;
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setCurrentItem(position);
    }

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

    public static void actionStart(Context context, GalleryKinds galleryKinds, int position, ImageType imageType) {
        Intent intent = new Intent(context, GalleriesActivity.class);
        intent.putExtra(IMAGE_TYPE, imageType);
        intent.putExtra(CLASSIFY, galleryKinds);
        intent.putExtra(POSITION, position);
        context.startActivity(intent);
    }
}
