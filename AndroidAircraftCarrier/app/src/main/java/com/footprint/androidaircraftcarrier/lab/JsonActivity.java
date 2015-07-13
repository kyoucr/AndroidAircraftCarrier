package com.footprint.androidaircraftcarrier.lab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.footprint.androidaircraftcarrier.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by liquanmin on 15/5/25.
 */
public class JsonActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_json);

        textView = (TextView) findViewById(R.id.textView);

        JSONObject jsonObject = new JSONObject();
        int arr[] = new int[]{1, 2, 3};
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(1000).put(1200).put(1400);

        textView.setText(jsonArray.toString());
    }
}
