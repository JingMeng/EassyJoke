package com.baimeng.eassyjoke.imageselector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.baimeng.eassyjoke.R;
import com.baimeng.framelibrary.recyclerview.commonadapter.RecyclerViewCommonAdapter;
import com.baimeng.framelibrary.recyclerview.commonadapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class SelectImageListAdapter extends RecyclerViewCommonAdapter<ImageEntity> {
    private Context mContext ;
    private List<ImageEntity> mData ;
    public SelectImageListAdapter( List<ImageEntity> mData, Context context) {
        super(R.layout.media_chooser_item, mData, context);
        this.mContext = context ;
        this.mData = mData ;
    }

    @Override
    protected void convert(ViewHolder holder, ImageEntity imageEntity, int position) {
        if(TextUtils.isEmpty(imageEntity.path)){
            //显示相机，否则显示图片
            holder.setViewVisibility(R.id.camera_ll, View.VISIBLE);
            holder.setViewVisibility(R.id.media_selected_indicator,View.INVISIBLE);
            holder.setViewVisibility(R.id.image,View.INVISIBLE);
        }else {
            holder.setViewVisibility(R.id.camera_ll, View.INVISIBLE);
            holder.setViewVisibility(R.id.media_selected_indicator,View.VISIBLE);
            holder.setViewVisibility(R.id.image,View.VISIBLE);
            ImageView imageView = holder.getView(R.id.image);
            Glide.with(mContext).load(imageEntity.path)
                    .centerCrop().into(imageView);
        }
    }

}
