package com.cyq.mvshow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyq.mvshow.R;
import com.cyq.mvshow.adapter.GalleryKindsAdapter;
import com.cyq.mvshow.base.BaseAbstractListener;
import com.cyq.mvshow.base.BaseFragment;
import com.cyq.mvshow.mode.GalleryKinds;
import com.cyq.mvshow.mode.ImageType;
import com.cyq.mvshow.server.TianGouDataLoader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by win7 on 2016/10/28.
 */

public class GalleryKindsFragment extends BaseFragment {


    private XRecyclerView mRecyclerView;
    private TextView centerTip_tv;
    private GalleryKindsAdapter myAdapter;
    private ImageType mImageType = ImageType.NEWS_TYPE;//默认是最新的类型


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery_kinds, null);
        mImageType = (ImageType) getArguments().getSerializable(IMAGE_TYPE);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    public static GalleryKindsFragment getInstance(ImageType imageType) {
        GalleryKindsFragment galleryKindsFragment = new GalleryKindsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(IMAGE_TYPE, imageType);
        galleryKindsFragment.setArguments(bundle);
        return galleryKindsFragment;
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
        myAdapter = new GalleryKindsAdapter(getActivity(), mImageType);
        mRecyclerView.setAdapter(myAdapter);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        TianGouDataLoader.getGalleryKinds(new BaseAbstractListener<GalleryKinds, Exception>() {
            @Override
            public void success(GalleryKinds o) {
                super.success(o);
                myAdapter.list.addAll(o.getGalleryKinds());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void fail(Exception o) {
                super.fail(o);
                centerTip_tv.setVisibility(View.VISIBLE);
                centerTip_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadData();
                        centerTip_tv.setVisibility(View.GONE);
                    }
                });
            }
        });
    }


}
