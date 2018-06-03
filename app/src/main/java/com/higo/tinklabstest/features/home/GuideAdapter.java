package com.higo.tinklabstest.features.home;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.higo.tinklabstest.R;
import com.higo.tinklabstest.entity.CityGuide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sharkliu on 2018/6/2.
 */

public class GuideAdapter extends BaseQuickAdapter<CityGuide,BaseViewHolder> {
    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_TEXT = 1;
    public GuideAdapter(@Nullable List data) {
        super(data);
    }
    @Override
    protected void convert(BaseViewHolder helper, CityGuide item) {
        if(helper instanceof  ImageViewHolder){
             ImageViewHolder viewHolder=(ImageViewHolder) helper;
            Glide.with(mContext).load(item.getImage())
                    .placeholder(R.mipmap.ic_city_big_image)
                    .error(R.mipmap.ic_city_big_image)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(viewHolder.guideImage);

        }else if(helper instanceof  TextViewHolder){
            TextViewHolder viewHolder=(TextViewHolder) helper;
            viewHolder.guideDescription.setText(item.getDescription());
            viewHolder.guideTitle.setText(item.getTitle());
            Glide.with(mContext).load(item.getImage())
                    .placeholder(R.mipmap.ic_city_small_image)
                    .error(R.mipmap.ic_city_small_image)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(viewHolder.guideImage);
        }
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);
        if (viewType == TYPE_IMAGE) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_city_guide_image, parent, false));
        } else if(viewType == TYPE_TEXT) {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_city_guide, parent, false));
        }else{
            return super.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).getType() == TYPE_IMAGE) {
            return TYPE_IMAGE;
        } else if(mData.get(position).getType() == TYPE_TEXT) {
            return TYPE_TEXT;
        }else{
            return super.getItemViewType(position);
        }
    }

    public static class TextViewHolder extends BaseViewHolder {
        @BindView(R.id.guideDescription)
        TextView guideDescription;
        @BindView(R.id.guideTitle)
        TextView guideTitle;
        @BindView(R.id.guideImage)
        ImageView guideImage;

        TextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class ImageViewHolder extends BaseViewHolder {
        @BindView(R.id.guideImage)
        ImageView guideImage;

        ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
