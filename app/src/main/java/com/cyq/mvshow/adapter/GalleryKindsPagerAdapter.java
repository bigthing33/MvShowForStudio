package com.cyq.mvshow.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cyq.mvshow.fragment.GalleryListFragment;
import com.cyq.mvshow.fragment.GalleryNewsFragment;
import com.cyq.mvshow.mode.GalleryKinds;
import com.cyq.mvshow.mode.ImageType;

import junit.framework.Assert;

/**
 * Created by win7 on 2016/10/25.
 */
public class GalleryKindsPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    public GalleryKinds galleryKinds = new GalleryKinds();
    private ImageType mImageType;

    public GalleryKindsPagerAdapter(FragmentManager fm, Context context,ImageType imageType) {
        super(fm);
        this.context = context;
        mImageType=imageType;
    }

    @Override
    public Fragment getItem(int position) {
        Assert.assertTrue(position < galleryKinds.getGalleryKinds().size());
        switch (mImageType) {
            case NEWS_TYPE:
                return GalleryNewsFragment.getInstance(galleryKinds.getGalleryKinds().get(position));
            case UN_NES_TYPE:
                return GalleryListFragment.getInstance(galleryKinds.getGalleryKinds().get(position));
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return galleryKinds.getGalleryKinds().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return galleryKinds.getGalleryKinds().get(position).getTitle();
    }
}
