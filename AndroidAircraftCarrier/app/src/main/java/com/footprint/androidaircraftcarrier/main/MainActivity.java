package com.footprint.androidaircraftcarrier.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liquanmin on 15/4/15.
 */
public class MainActivity extends FPListActivity {

    @Override
    public List<ActivityItem> getDataList() {
        List<ActivityItem> dataList = new ArrayList<ActivityItem>();
        ActivityItem listItem = new ActivityItem("Lab", "lab://main");
        dataList.add(listItem);

        listItem = new ActivityItem("Service", "service://main");
        dataList.add(listItem);

        return dataList;
    }

}
