package com.footprint.androidaircraftcarrier.lab;

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
        ActivityItem listItem = new ActivityItem("ListView的Header测试", "fp://lab");
        labList.add(listItem);
        return labList;
    }
}
