package com.footprint.androidaircraftcarrier.lab;

import android.util.Log;

import com.footprint.androidaircraftcarrier.main.ActivityItem;
import com.footprint.androidaircraftcarrier.main.FPListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liquanmin on 15/4/15.
 */
public class LabActivity extends FPListActivity {
    @Override
    public List<ActivityItem> getDataList() {
        List<ActivityItem> labList = new ArrayList<ActivityItem>();
        ActivityItem listItem = new ActivityItem("ListView的Header测试", "lab://listhead");
        labList.add(listItem);

        listItem = new ActivityItem("Activity跳转时生命周期测试", "lab://lifecycle");
        labList.add(listItem);

        return labList;
    }

    @Override
    protected void onStart() {
        Log.e("FP", "Lab-Start");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("FP", "Lab-Resume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("FP", "Lab-Pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("FP", "Lab-Stop");
        super.onStop();
    }
}
