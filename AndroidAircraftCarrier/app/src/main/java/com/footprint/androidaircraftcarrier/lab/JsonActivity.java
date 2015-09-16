package com.footprint.androidaircraftcarrier.lab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.footprint.androidaircraftcarrier.R;

import org.json.JSONException;
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
        try {
            jsonObject.put("refresh", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        textView.setText(jsonObject.toString());
    }
}
