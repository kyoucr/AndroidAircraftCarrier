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

        listItem = new ActivityItem("Fresco", "source://fresco");
        dataList.add(listItem);

        listItem = new ActivityItem("EventBus", "source://eventbus");
        dataList.add(listItem);

        listItem = new ActivityItem("ViewPagerAnimation", "anim://viewpager");
        dataList.add(listItem);

        listItem = new ActivityItem("LayoutAnimation", "anim://layout");
        dataList.add(listItem);

        listItem = new ActivityItem("Flappy Birds", "game://flappybirds");
        dataList.add(listItem);

        listItem = new ActivityItem("新UI", "study://ui");
        dataList.add(listItem);
        return dataList;
    }

}
