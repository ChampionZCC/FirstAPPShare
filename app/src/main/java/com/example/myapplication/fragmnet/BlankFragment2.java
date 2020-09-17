package com.example.myapplication.fragmnet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import androidx.fragment.app.Fragment;

/**
 * @author zhoucong
 */
public class BlankFragment2 extends Fragment {

    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mParam = getArguments().getString(ARG_PARAM);
        Log.e("BlankFragment", "onAttach: " + mParam);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_blank2, container, false);
        return root;
    }

    public static BlankFragment2 newInstance(String str) {
        BlankFragment2 frag = new BlankFragment2();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);
        return frag;
    }
}