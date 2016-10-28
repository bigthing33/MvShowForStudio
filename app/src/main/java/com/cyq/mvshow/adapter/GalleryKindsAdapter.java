package com.cyq.mvshow.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyq.mvshow.R;
import com.cyq.mvshow.base.BaseAbstractListener;
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

    public GalleryKindsAdapter(Context context, ImageType ImageType) {
        mImageType = ImageType;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_viewholder, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textview.setText(list.get(position).getDescription());
        TianGouDataLoader.getGalleries(1, 2, list.get(position).getId(), new BaseAbstractListener<Galleries, Exception>() {
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

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textview;
        private SimpleDraweeView simpleImage;

        public ViewHolder(View itemView) {
            super(itemView);

            textview = (TextView) itemView.findViewById(R.id.tittle_tv);
            simpleImage = (SimpleDraweeView) itemView.findViewById(R.id.simpleImage);
        }
    }
}
