package com.cyq.mvshow.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cyq.mvshow.fragment.GalleryKindsFragment;
import com.cyq.mvshow.fragment.PageFragment;

/**
 * Created by win7 on 2016/10/25.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"网络请求API", "分类", "tab3", "tab4"};
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new GalleryKindsFragment();
            default:
                return PageFragment.newInstance(position + 1);
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
