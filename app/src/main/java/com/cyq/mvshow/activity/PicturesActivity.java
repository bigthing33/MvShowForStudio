package com.cyq.mvshow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cyq.mvshow.R;
import com.cyq.mvshow.adapter.GalleriesPagerAdapter;
import com.cyq.mvshow.base.BaseFragmentActivity;
import com.cyq.mvshow.mode.Galleries;

import junit.framework.Assert;

public class PicturesActivity extends BaseFragmentActivity {
    private static final String GALLERIES ="GALLERIES" ;
    private static final String POSITION = "POSITION";
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar ;
    private ViewPager viewPager;

    private GalleriesPagerAdapter pagerAdapter;
    public PicturesActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.sliding_collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        final Galleries galleries = (Galleries) getIntent().getSerializableExtra(GALLERIES);
        int position = (int) getIntent().getSerializableExtra(POSITION)-1;
        Assert.assertNotNull(galleries);
        Assert.assertNotNull(galleries.getGalleries());
        Assert.assertNotNull(position<galleries.getGalleries().size());
        collapsingToolbarLayout.setTitle(galleries.getGalleries().get(position).getTitle());
        toolbar.setTitle(galleries.getGalleries().get(position).getTitle());
        pagerAdapter = new GalleriesPagerAdapter(getSupportFragmentManager(), this);
        pagerAdapter.galleries=galleries;
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(galleries.getGalleries().get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    /**
     * 处理屏幕转换
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    public static void actionStart(Context context, Galleries galleries,int position) {
        Intent intent = new Intent(context, PicturesActivity.class);
        intent.putExtra(GALLERIES, galleries);
        intent.putExtra(POSITION, position);
        context.startActivity(intent);
    }
}
