package com.cyq.mvshow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cyq.mvshow.R;
import com.cyq.mvshow.adapter.GalleriesPagerAdapter;
import com.cyq.mvshow.base.BaseAbstractListener;
import com.cyq.mvshow.base.BaseFragmentActivity;
import com.cyq.mvshow.mode.Galleries;
import com.cyq.mvshow.mode.GalleryKind;
import com.cyq.mvshow.mode.ImageType;
import com.cyq.mvshow.other.MyConstants;
import com.cyq.mvshow.server.TianGouDataLoader;
import com.cyq.mvshow.utils.DataUtils;
import com.cyq.mvshow.utils.DialogUtils;
import com.cyq.mvshow.utils.LogUtil;
import com.cyq.mvshow.utils.UIUtils;

import junit.framework.Assert;

public class PicturesActivity extends BaseFragmentActivity {
    private static final String GALLERIES = "GALLERIES";
    private static final String PAGE = "PAGE";
    private static final String POSITION = "POSITION";
    private static final String TAG = PicturesActivity.class.getSimpleName();
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;

    private GalleriesPagerAdapter mPagerAdapter;
    private GalleryKind mGalleryKind;
    private int mPage = 1;
    private boolean isLoading;
    private ImageType mImageType;


    public PicturesActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures);
        final Galleries galleries = (Galleries) getIntent().getSerializableExtra(GALLERIES);
        mPage = getIntent().getIntExtra(PAGE, 1);
        mImageType = (ImageType) getIntent().getSerializableExtra(IMAGE_TYPE);
        int position = (int) getIntent().getSerializableExtra(POSITION) - 1;

        mGalleryKind = (GalleryKind) getIntent().getSerializableExtra(CLASSIFY);
        Assert.assertNotNull(mGalleryKind);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.sliding_collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);


        Assert.assertNotNull(galleries);
        Assert.assertNotNull(galleries.getGalleries());
        Assert.assertNotNull(position < galleries.getGalleries().size());
        collapsingToolbarLayout.setTitle(galleries.getGalleries().get(position).getTitle());
        toolbar.setTitle(galleries.getGalleries().get(position).getTitle());
        mPagerAdapter = new GalleriesPagerAdapter(getSupportFragmentManager(), this);
        mPagerAdapter.galleries = galleries;
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public int count;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LogUtil.d(TAG, "onPageScrolled::: position:" + position + " positionOffset:" + positionOffset + " positionOffsetPixels:" + positionOffsetPixels);
                if (position == mPagerAdapter.getCount() - 1) {
                    if (positionOffset == 0 && positionOffsetPixels == 0) {
                        count++;
                    }

                }

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.d(TAG, "onPageSelected::: position:" + position);
                toolbar.setTitle(galleries.getGalleries().get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogUtil.d(TAG, "onPageScrollStateChanged::: state:" + state);
                if (state == 0) {
                    if (count >= 5) {
                        switch (mImageType) {
                            case UN_NES_TYPE:
                                loadDataMoreByList(mPage, MyConstants.PAGE_SIZE, mGalleryKind.getId());
                                break;
                            case NEWS_TYPE:
                                loadDataByNews(MyConstants.PAGE_SIZE, mGalleryKind.getId(), DataUtils.getRandomLong3());
                                break;
                        }
                    }
                    count = 0;
                }

            }
        });
    }

    /**
     * 根据id加载图库
     *
     * @param rows
     * @param classify
     * @param id
     */
    private void loadDataByNews(int rows, int classify, long id) {
        DialogUtils.showProgressDialogWithoutRate(PicturesActivity.this);
        if (isLoading) {
            return;
        }
        isLoading = true;
        TianGouDataLoader.getGalleriesNews(rows, classify, id, new BaseAbstractListener<Galleries, Exception>() {
            @Override
            public void success(Galleries o) {
                super.success(o);
                mPagerAdapter.galleries.getGalleries().clear();
                mPagerAdapter.galleries.getGalleries().addAll(o.getGalleries());
                mPagerAdapter.notifyDataSetChanged();
                DialogUtils.dismissProgressDialogWithoutRate();
                isLoading = false;
            }

            @Override
            public void fail(Exception o) {
                super.fail(o);
                UIUtils.toastShort(PicturesActivity.this, R.string.request_fail);
                DialogUtils.dismissProgressDialogWithoutRate();
                isLoading = false;
            }
        });
    }

    /**
     * 加载下一页列表
     *
     * @param page
     * @param rows
     * @param classify
     */
    private void loadDataMoreByList(final int page, int rows, int classify) {
        DialogUtils.showProgressDialogWithoutRate(PicturesActivity.this);
        if (isLoading) {
            return;
        }
        isLoading = true;
        TianGouDataLoader.getGalleries(page, rows, classify, new BaseAbstractListener<Galleries, Exception>() {
            @Override
            public void success(Galleries o) {
                super.success(o);
                mPage = page + 1;
                mPagerAdapter.galleries.getGalleries().addAll(o.getGalleries());
                mPagerAdapter.notifyDataSetChanged();
                DialogUtils.dismissProgressDialogWithoutRate();
                isLoading = false;
            }

            @Override
            public void fail(Exception o) {
                super.fail(o);
                mPage = page;
                UIUtils.toastShort(PicturesActivity.this, R.string.request_fail);
                DialogUtils.dismissProgressDialogWithoutRate();
                isLoading = false;
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

    public static void actionStart(Context context, GalleryKind galleryKind, Galleries galleries, int page, int position, ImageType imageType) {
        Intent intent = new Intent(context, PicturesActivity.class);
        intent.putExtra(CLASSIFY, galleryKind);
        intent.putExtra(GALLERIES, galleries);
        intent.putExtra(PAGE, page);
        intent.putExtra(POSITION, position);
        intent.putExtra(IMAGE_TYPE, imageType);
        context.startActivity(intent);
    }
}
