package com.cyq.mvshow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyq.mvshow.R;
import com.cyq.mvshow.activity.PicturesActivity;
import com.cyq.mvshow.adapter.PicturesAdapter;
import com.cyq.mvshow.base.BaseAbstractListener;
import com.cyq.mvshow.base.BaseFragment;
import com.cyq.mvshow.callback.MyItemClickListener;
import com.cyq.mvshow.mode.Galleries;
import com.cyq.mvshow.mode.Gallery;
import com.cyq.mvshow.mode.GalleryDetails;
import com.cyq.mvshow.other.MyConstants;
import com.cyq.mvshow.server.TianGouDataLoader;
import com.cyq.mvshow.utils.DataUtils;
import com.cyq.mvshow.utils.UIUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by win7 on 2016/10/28.
 */

public class PicturesFragment extends BaseFragment {

    private static final String GALLERY = "GALLERY";
    private XRecyclerView mRecyclerView;
    private TextView centerTip_tv;
    private PicturesAdapter myAdapter;

    private Gallery gallery;
    private long mPage = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pictures, null);
        initView(rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        gallery = (Gallery) getArguments().getSerializable(GALLERY);
        if (gallery != null) {
            loadData(gallery.getId());
        }
    }

    public static PicturesFragment getInstance(Gallery gallery) {
        PicturesFragment galleryNewsFragment = new PicturesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(GALLERY, gallery);
        galleryNewsFragment.setArguments(bundle);
        return galleryNewsFragment;
    }

    /**
     * view的初始化
     *
     * @param rootView
     */
    private void initView(View rootView) {
        mRecyclerView = (XRecyclerView) rootView.findViewById(R.id.recyclerView);
        centerTip_tv = (TextView) rootView.findViewById(R.id.centerTip_tv);
        centerTip_tv.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        myAdapter = new PicturesAdapter(getActivity());
        myAdapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLoadingMoreEnabled(false);// TODO: 2016/10/29 上拉加载更多的功能
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadData(gallery.getId());

            }

            @Override
            public void onLoadMore() {
                loadDataMore(gallery.getId() + mPage);
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData(final long id) {
        mPage = 1;
        TianGouDataLoader.getGalleryDetails(id, new BaseAbstractListener<GalleryDetails, Exception>() {
            @Override
            public void success(GalleryDetails o) {
                super.success(o);
                mPage++;
                myAdapter.galleryDetails.getPictures().clear();
                myAdapter.galleryDetails = o;
                myAdapter.notifyDataSetChanged();
                mRecyclerView.refreshComplete();
            }

            @Override
            public void fail(Exception o) {
                super.fail(o);
                centerTip_tv.setVisibility(View.VISIBLE);
                centerTip_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadData(id);
                        centerTip_tv.setVisibility(View.GONE);
                    }
                });
                UIUtils.toastShort(getActivity(), R.string.request_fail);
                mRecyclerView.refreshComplete();
            }
        });
    }

    private void loadDataMore(final long page) {

        TianGouDataLoader.getGalleryDetails(gallery.getId() + page, new BaseAbstractListener<GalleryDetails, Exception>() {
            @Override
            public void success(GalleryDetails o) {
                super.success(o);
                mPage++;
                myAdapter.galleryDetails.getPictures().addAll(o.getPictures());
                myAdapter.notifyDataSetChanged();
                mRecyclerView.loadMoreComplete();
            }

            @Override
            public void fail(Exception o) {
                super.fail(o);
                centerTip_tv.setVisibility(View.VISIBLE);
                centerTip_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadDataMore(page);
                        centerTip_tv.setVisibility(View.GONE);
                    }
                });
                UIUtils.toastShort(getActivity(), R.string.request_fail);
                mRecyclerView.loadMoreComplete();
            }
        });
    }
}
