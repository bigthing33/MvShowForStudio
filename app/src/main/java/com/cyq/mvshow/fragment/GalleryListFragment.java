package com.cyq.mvshow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyq.mvshow.R;
import com.cyq.mvshow.adapter.GalleriesAdapter;
import com.cyq.mvshow.base.BaseAbstractListener;
import com.cyq.mvshow.base.BaseFragment;
import com.cyq.mvshow.mode.Galleries;
import com.cyq.mvshow.mode.GalleryKind;
import com.cyq.mvshow.other.MyConstants;
import com.cyq.mvshow.server.TianGouDataLoader;
import com.cyq.mvshow.utils.UIUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by win7 on 2016/10/28.
 */

public class GalleryListFragment extends BaseFragment {

    private XRecyclerView mRecyclerView;
    private TextView centerTip_tv;
    private GalleriesAdapter myAdapter;

    private GalleryKind galleryKind;
    private int mPage=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery_kinds, null);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        galleryKind = (GalleryKind) getArguments().getSerializable(CLASSIFY);
        if (galleryKind != null) {
            loadData(1, MyConstants.PAGE_SIZE, galleryKind.getId());
        }
    }

    public static GalleryListFragment getInstance(GalleryKind galleryKind) {
        GalleryListFragment galleryNewsFragment = new GalleryListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CLASSIFY, galleryKind);
        galleryNewsFragment.setArguments(bundle);
        return galleryNewsFragment;
    }

    /**
     * view的初始化
     *
     * @param rootView
     */
    private void initView(View rootView) {
        mRecyclerView = (XRecyclerView) rootView.findViewById(R.id.recyclerview);
        centerTip_tv = (TextView) rootView.findViewById(R.id.centerTip_tv);
        centerTip_tv.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        myAdapter = new GalleriesAdapter(getActivity());
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadData(1, MyConstants.PAGE_SIZE, galleryKind.getId());
            }

            @Override
            public void onLoadMore() {
                loadDataMore(mPage, MyConstants.PAGE_SIZE, galleryKind.getId());

            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData(final int page, int rows, int classify) {
        TianGouDataLoader.getGalleries(page, rows, classify, new BaseAbstractListener<Galleries, Exception>() {
            @Override
            public void success(Galleries o) {
                super.success(o);
                mPage=page+1;
                myAdapter.list.clear();
                myAdapter.list.addAll(o.getGalleries());
                myAdapter.notifyDataSetChanged();
                mRecyclerView.refreshComplete();

            }

            @Override
            public void fail(Exception o) {
                super.fail(o);
                mPage=page;
                UIUtils.toastShort(getActivity(),R.string.request_fail);
                centerTip_tv.setVisibility(View.VISIBLE);
                centerTip_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadData(1, MyConstants.PAGE_SIZE, galleryKind.getId());
                        centerTip_tv.setVisibility(View.GONE);
                    }
                });
                mRecyclerView.refreshComplete();
            }
        });
    }
    private void loadDataMore(final int page, int rows, int classify) {
        TianGouDataLoader.getGalleries(page, rows, classify, new BaseAbstractListener<Galleries, Exception>() {
            @Override
            public void success(Galleries o) {
                super.success(o);
                mPage=page+1;
                myAdapter.list.addAll(o.getGalleries());
                myAdapter.notifyDataSetChanged();
                mRecyclerView.loadMoreComplete();
            }

            @Override
            public void fail(Exception o) {
                super.fail(o);
                mPage=page;
                UIUtils.toastShort(getActivity(),R.string.request_fail);
                centerTip_tv.setVisibility(View.VISIBLE);
                centerTip_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadData(1, MyConstants.PAGE_SIZE, galleryKind.getId());
                        centerTip_tv.setVisibility(View.GONE);
                    }
                });
                mRecyclerView.loadMoreComplete();
            }
        });
    }
}
