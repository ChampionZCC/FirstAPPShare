package com.example.myapplication;

import android.content.Context;

import java.util.List;


/**
 * 继承自BaseAdapter（基于RecyclerView.Adapter）
 * 配合baseAdapter和baseViewHolder使用
 * 供子类继承使用
 * XxxxAdapter<<--SimpleAdapter<<--BaseAdapter<<--RecyclerView.Adapter
 * @author zhoucong
 */
public abstract class SimpleAdapter<T> extends BaseAdapter<T, BaseViewHolder> {

    public SimpleAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public SimpleAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

}
