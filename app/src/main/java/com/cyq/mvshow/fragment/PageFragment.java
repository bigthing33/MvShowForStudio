package com.cyq.mvshow.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyq.mvshow.R;
import com.cyq.mvshow.base.BaseAbstractListener;
import com.cyq.mvshow.server.TianGouWorker;
import com.cyq.mvshow.utils.LogUtil;
import com.cyq.mvshow.utils.UpdateUtil;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Administrator on 2015/7/30.
 */
public class PageFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = PageFragment.class.getSimpleName();

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        view.findViewById(R.id.getKinds_btn).setOnClickListener(this);
        view.findViewById(R.id.getLists_btn).setOnClickListener(this);
        view.findViewById(R.id.getNews_btn).setOnClickListener(this);
        view.findViewById(R.id.getDetail_btn).setOnClickListener(this);
        view.findViewById(R.id.getVersionInfo_btn).setOnClickListener(this);
        TextView textView = (TextView) view.findViewById(R.id.tittle_tv);
        textView.setText("Fragment #" + mPage);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getKinds_btn:
                TianGouWorker.get_kinds(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
                break;
            case R.id.getLists_btn:
                TianGouWorker.get_list(1, 20, 1, new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtil.d(TAG, "get_list s:" + s);
                    }
                });
                break;
            case R.id.getNews_btn:
                TianGouWorker.get_news(1, 20, 1, new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
                break;
            case R.id.getDetail_btn:
                TianGouWorker.get_details(10, new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
                break;
            case R.id.getVersionInfo_btn:
                UpdateUtil.checkVersionByRequest(getActivity(),new BaseAbstractListener<Boolean, Exception>() {
                    @Override
                    public void success(Boolean o) {
                        super.success(o);
                    }
                });
                break;
        }
    }
}