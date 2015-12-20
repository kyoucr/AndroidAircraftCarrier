package com.footprint.androidaircraftcarrier.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by liquanmin on 15/12/19.
 */
public abstract class ShellFragment extends Fragment {
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getRootViewId(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }

    protected abstract int getRootViewId();

    //可以通过这个方式去实例化View，或者注解
    protected void initView(View rootView) {
    }

    protected View getRootView() {
        return rootView;
    }

    /**
     * 修复一个Bug
     * https://code.google.com/p/android/issues/detail?id=78062
     */
    public void stopRefresh() {
    }
}
