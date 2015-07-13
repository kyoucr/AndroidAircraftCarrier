package com.footprint.androidaircraftcarrier.ui;

import com.footprint.androidaircraftcarrier.main.ActivityItem;
import com.footprint.androidaircraftcarrier.main.FPListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liquanmin on 15/7/13.
 */
public class UIActivity extends FPListActivity{
    @Override
    public List<ActivityItem> getDataList() {
        List<ActivityItem> labList = new ArrayList<ActivityItem>();
        ActivityItem listItem = new ActivityItem("RecyleView", "study://rv");
        labList.add(listItem);

        return labList;
    }
}
