package com.cyq.mvshow.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyq.mvshow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 2016/10/28.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    public List<String> list=new ArrayList<String>();

    public MyAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_viewholder, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textview.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textview;

        public ViewHolder(View itemView) {
            super(itemView);

            textview = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
