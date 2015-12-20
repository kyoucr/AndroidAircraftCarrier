package com.footprint.androidaircraftcarrier.main.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.footprint.androidaircraftcarrier.R;
import com.footprint.androidaircraftcarrier.app.ShellFragment;
import com.footprint.androidaircraftcarrier.main.model.OpenLib;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liquanmin on 15/12/18.
 */
public class SourceCodeFragment extends ShellFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.recyclerview_code)
    RecyclerView recyclerView;

    @Bind(R.id.swipelayout_code)
    SwipeRefreshLayout swipeRefreshLayout;

    Handler handler = new Handler();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ExploreAdapter(getContext(), getOpenLibs()));

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_code;
    }

    private List<OpenLib> getOpenLibs() {
        List<OpenLib> openLibs = new ArrayList<>(8);

        OpenLib openLib = new OpenLib();
        openLib.logoId = R.drawable.logo_fresco;
        openLib.name = "Fresco";
        openLib.author = "Facebook";
        openLib.des = getString(R.string.des_fresco);

        openLibs.add(openLib);
        openLibs.add(openLib);
        openLibs.add(openLib);
        openLibs.add(openLib);
        openLibs.add(openLib);
        openLibs.add(openLib);
        openLibs.add(openLib);
        openLibs.add(openLib);
        return openLibs;
    }

    @Override
    public void onRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 5000);
        }
    }
}

class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyViewHolder> {
    private Context context;
    private List<OpenLib> openLibs;

    public ExploreAdapter(Context context, List<OpenLib> openLibs) {
        this.context = context;
        this.openLibs = openLibs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.listitem_code, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OpenLib openLib = openLibs.get(position);
        holder.logoView.getHierarchy().setPlaceholderImage(openLib.logoId);
        holder.nameText.setText(openLib.name);
        holder.authorText.setText(openLib.author);
        holder.desText.setText(openLib.des);
    }

    @Override
    public int getItemCount() {
        return openLibs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_openlib_logo)
        SimpleDraweeView logoView;

        @Bind(R.id.textview_openlib_name)
        TextView nameText;

        @Bind(R.id.textview_openlib_author)
        TextView authorText;

        @Bind(R.id.textview_openlib_description)
        TextView desText;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
