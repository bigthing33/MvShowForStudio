package com.cyq.mvshow.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyq.mvshow.R;
import com.cyq.mvshow.callback.MyItemClickListener;
import com.cyq.mvshow.callback.MyItemLongClickListener;
import com.cyq.mvshow.mode.GalleryDetails;
import com.cyq.mvshow.utils.ImageUtils;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by win7 on 2016/10/28.
 */

public class PicturesAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    public GalleryDetails galleryDetails=new GalleryDetails();

    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public PicturesAdapter(Context context) {

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
        return new ViewHolder(inflater.inflate(R.layout.item_gallery, parent, false), mItemClickListener, mItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textview.setText((position+1)+" ");// TODO: 2016/10/29
        ImageUtils.setImageByUrl(viewHolder.simpleImage, galleryDetails.getPictures().get(position).getSrc());

    }


    @Override
    public int getItemCount() {
        return galleryDetails.getPictures().size();
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
