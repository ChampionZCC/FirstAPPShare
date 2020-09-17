package com.example.myapplication;

import android.content.Context;

import java.util.List;

/**
 * @description:
 * @author: ZhouCong
 * @email: 13328023455@163.com
 * @date :   2020/7/10 9:34 AM
 */
public class TestAdapter extends SimpleAdapter<TestBean> {


    public TestAdapter(Context context, List<TestBean> list) {
        super(context, R.layout.item_test, list);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, TestBean item, int position) {
        viewHolder.getTextView(R.id.tv_name).setText(item.getName());
        String age = String.valueOf(item.getAge());
        viewHolder.getTextView(R.id.tv_age).setText(age);
        String grade = String.valueOf(item.getGrade());
        viewHolder.getTextView(R.id.tv_grade).setText(grade);
    }
}
