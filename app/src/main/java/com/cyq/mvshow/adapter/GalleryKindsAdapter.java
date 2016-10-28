package com.cyq.mvshow.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyq.mvshow.R;
import com.cyq.mvshow.base.BaseAbstractListener;
import com.cyq.mvshow.callback.MyItemClickListener;
import com.cyq.mvshow.callback.MyItemLongClickListener;
import com.cyq.mvshow.mode.Galleries;
import com.cyq.mvshow.mode.GalleryKind;
import com.cyq.mvshow.mode.ImageType;
import com.cyq.mvshow.server.TianGouDataLoader;
import com.cyq.mvshow.utils.ImageUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 2016/10/28.
 */

public class GalleryKindsAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    public List<GalleryKind> list = new ArrayList<>();
    private ImageType mImageType;


    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public GalleryKindsAdapter(Context context, ImageType ImageType) {
        mImageType = ImageType;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_viewholder, parent, false), mItemClickListener, mItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final GalleryKind galleryKind = list.get(position);
        viewHolder.textview.setText(list.get(position).getDescription());
        viewHolder.simpleImage.setImageResource(R.mipmap.ic_launcher);
        TianGouDataLoader.getGalleries(1, 2, galleryKind.getId(), new BaseAbstractListener<Galleries, Exception>() {
            @Override
            public void success(Galleries galleries) {
                super.success(galleries);
                Assert.assertNotNull(galleries);
                Assert.assertNotNull(galleries.getGalleries());
                Assert.assertTrue(galleries.getGalleries().size() >= 2);
                switch (mImageType) {
                    case NEWS_TYPE:
                        ImageUtils.setImageByUrl(viewHolder.simpleImage, galleries.getGalleries().get(0).getImg());
                        break;
                    case UN_NES_TYPE:
                        ImageUtils.setImageByUrl(viewHolder.simpleImage, galleries.getGalleries().get(1).getImg());

                }
            }

            @Override
            public void fail(Exception o) {
                super.fail(o);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView textview;
        private SimpleDraweeView simpleImage;

        private MyItemClickListener mListener;
        private MyItemLongClickListener mLongClickListener;

        public ViewHolder(View itemView, MyItemClickListener listener, MyItemLongClickListener longClickListener) {
            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.tittle_tv);
            simpleImage = (SimpleDraweeView) itemView.findViewById(R.id.simpleImage);
            this.mListener = listener;
            this.mLongClickListener = longClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(view, getPosition());
            }

        }

        @Override
        public boolean onLongClick(View view) {
            if (mLongClickListener != null) {
                mLongClickListener.onItemLongClick(view, getPosition());
            }
            return true;
        }
    }
}
