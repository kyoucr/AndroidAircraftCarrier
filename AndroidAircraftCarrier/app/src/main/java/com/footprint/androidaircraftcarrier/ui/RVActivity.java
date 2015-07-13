package com.footprint.androidaircraftcarrier.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.footprint.androidaircraftcarrier.R;

/**
 * Created by liquanmin on 15/7/13.
 */
public class RVActivity extends Activity{
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());

        setContentView(R.layout.activity_ui_recyle);
        recyclerView = (RecyclerView)findViewById(R.id.rc_view);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        staggeredGridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RVAdapter());
    }
}
