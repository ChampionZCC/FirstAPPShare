package com.example.myapplication;


import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


/**
 * 保存RecyclerView的条目的视图引用
 * 配合baseAdapter使用
 * XxxViewHolder<<--BaseViewHolder<<--RecyclerView.ViewHolder
 * @author zhoucong
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SparseArray<View> mViews;
    private BaseAdapter.OnItemClickListener mOnItemClickListener;

    public BaseViewHolder(View itemView, BaseAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        mOnItemClickListener = onItemClickListener;
        mViews = new SparseArray<>();
        itemView.setOnClickListener(this);
    }

    /**
     * 获取textview
     *
     * @param viewId
     * @return
     */
    public TextView getTextView(int viewId) {
        TextView view = getViewById(viewId);
        return view;
    }

    public LinearLayout getLinearLayout(int viewId) {
        LinearLayout view = getViewById(viewId);
        return view;
    }

    public RelativeLayout getRelativeLayout(int viewId) {
        RelativeLayout view = getViewById(viewId);
        return view;
    }

    /**
     * 获取Button
     *
     * @param viewId
     * @return
     */
    public Button getButton(int viewId) {
        return getViewById(viewId);
    }

    /**
     * 获取ImageView
     *
     * @param viewId
     * @return
     */
    public ImageView getImageView(int viewId) {
        return getViewById(viewId);
    }

    /**
     * 获取View
     *
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        return getViewById(viewId);
    }


    /**
     * 根据view的id获取view，并放到SparseArray里面
     *
     * @param viewId view的id
     * @param <T>    view的泛型
     * @return 返回view
     */
    private <T extends View> T getViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //getLayoutPosition()是 RecyclerView.ViewHolder 的方法，获取这个ViewHolder的位置
            mOnItemClickListener.onItemClick(v, getLayoutPosition());
        }
    }
}

