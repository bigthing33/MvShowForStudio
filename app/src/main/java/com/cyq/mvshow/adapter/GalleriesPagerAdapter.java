package com.cyq.mvshow.adapter;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cyq.mvshow.fragment.GalleryKindsFragment;
import com.cyq.mvshow.fragment.PageFragment;
import com.cyq.mvshow.fragment.PicturesFragment;
import com.cyq.mvshow.mode.Galleries;
import com.cyq.mvshow.mode.ImageType;

import junit.framework.Assert;

/**
 * Created by win7 on 2016/10/25.
 */
public class GalleriesPagerAdapter extends FragmentPagerAdapter {

    public Galleries galleries=new Galleries();
    private Context context;

    public GalleriesPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        int size=galleries.getGalleries().size();
        return PicturesFragment.getInstance(galleries.getGalleries().get(position));
    }

    @Override
    public int getCount() {
        return galleries.getGalleries().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return galleries.getGalleries().get(position).getTitle();
    }
}
