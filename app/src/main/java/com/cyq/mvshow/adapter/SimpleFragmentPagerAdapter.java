package com.cyq.mvshow.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cyq.mvshow.fragment.GalleryKindsFragment;
import com.cyq.mvshow.fragment.GalleryNewsFragment;
import com.cyq.mvshow.fragment.PageFragment;
import com.cyq.mvshow.mode.ImageType;

/**
 * Created by win7 on 2016/10/25.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"网络请求API", "最新", "分类"};
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PageFragment.newInstance(position + 1);
            case 1:
                return GalleryKindsFragment.getInstance(ImageType.NEWS_TYPE);
            case 2:
                return GalleryKindsFragment.getInstance(ImageType.UN_NES_TYPE);
            default:
                return GalleryNewsFragment.getInstance(position);
        }

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
