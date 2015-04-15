package com.footprint.androidaircraftcarrier.lab;

import android.os.Bundle;
import android.widget.TextView;

import com.footprint.androidaircraftcarrier.R;
import com.footprint.androidaircraftcarrier.main.ActivityItem;
import com.footprint.androidaircraftcarrier.main.FPListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liquanmin on 15/4/15.
 * 验证:当一个ListView的Item为空，显示EmptyView的时候，Head是否显示
 * 结果:不显示，只显示EmptyView
 */
public class ListViewHeader extends FPListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView headView = new TextView(this);
        headView.setText("I am HeadView");
        listView.addHeaderView(headView);

        //EmptyView设置限制：设置的View必需在当前的View hierarchy里
        TextView emptyView = (TextView) findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);
    }

    @Override
    public List<ActivityItem> getDataList() {
        List<ActivityItem> testList = new ArrayList<ActivityItem>();
        ActivityItem listItem = new ActivityItem("测试Item", "");
        testList.add(listItem);
        return testList;
    }
}
