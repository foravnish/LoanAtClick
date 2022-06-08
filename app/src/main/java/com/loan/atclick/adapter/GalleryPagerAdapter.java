package com.loan.atclick.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.loan.atclick.R;

import java.util.ArrayList;

/**
 * Created by anoop on 11/27/2017.
 */

public class GalleryPagerAdapter extends PagerAdapter {


    private ArrayList<String> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public GalleryPagerAdapter(Context context, ArrayList<String> mList) {
        mContext = context;
        this.mList = mList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.gallery_preview_layout, container, false);
        Log.d("ImageUrlInside", position + ", " + mList.get(position));


        com.ortiz.touchview.TouchImageView full_image = (com.ortiz.touchview.TouchImageView) itemView.findViewById(R.id.fullImage);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform();
        Glide.with(itemView.getContext())
                .load(mList.get(position)) // Uri of the picture
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(full_image);


        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


}