package com.cyq.mvshow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyq.mvshow.R;
import com.cyq.mvshow.adapter.MyAdapter;
import com.cyq.mvshow.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 2016/10/28.
 */

public class GalleryKindsFragment extends BaseFragment {

    private XRecyclerView mRecyclerView;
    private MyAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery_kinds, null);
        mRecyclerView = (XRecyclerView) rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(getActivity());
        mRecyclerView.setAdapter(myAdapter);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO: 2016/10/28 获取数据
        myAdapter.list.addAll(getList());
        myAdapter.notifyDataSetChanged();
    }
    //获取List方法
    private List<String> getList(){
        List<String> list = new ArrayList<String>();
        for (int i = 'A';i<'z';++i){
            list.add(""+(char)i);
        }
        return list;
    }
}
