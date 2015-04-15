package com.footprint.androidaircraftcarrier.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.footprint.androidaircraftcarrier.R;

import java.util.List;

/**
 * Created by liquanmin on 15/4/15.
 */
public abstract class FPListActivity extends Activity{
    protected ListView listView;
    private List<ActivityItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dataList = getDataList();

        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new FPListAdapter(FPListActivity.this, dataList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = dataList.get(position).getDataUrl();
                if(TextUtils.isEmpty(url))
                    return;

                Intent intent = new Intent();
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    public abstract List<ActivityItem> getDataList();

    public static class FPListHolder{
        public TextView desText;
    }

    public static class FPListAdapter extends BaseAdapter{
        private List<ActivityItem> data;
        private Context context;

        public FPListAdapter(Context context, List<ActivityItem> data){
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            if(data == null)
                return 0;
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FPListHolder holder = null;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
                holder = new FPListHolder();
                holder.desText = (TextView)convertView.findViewById(R.id.desText);
                convertView.setTag(holder);
            }else{
                holder = (FPListHolder)convertView.getTag();
            }

            holder.desText.setText(data.get(position).getDescription());
            return convertView;
        }
    }
}
