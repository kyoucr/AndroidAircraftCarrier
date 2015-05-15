package com.footprint.androidaircraftcarrier.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.footprint.androidaircraftcarrier.R;
import com.footprint.androidaircraftcarrier.utils.ColorUtils;

/**
 * Created by liquanmin on 15/5/15.
 */
public class LayoutAnimationActivity extends Activity {

    LinearLayout testLayout;
    Button removeButton, addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutanimation);

        testLayout = (LinearLayout)findViewById(R.id.testLayout);


        addButton = (Button)findViewById(R.id.addButton);
        removeButton = (Button)findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(testLayout.getChildCount() > 0)
                    testLayout.removeViewAt(0);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                TextView textView = new TextView(v.getContext());
                textView.setText("####@@@@@@@@@");
                textView.setBackgroundColor(ColorUtils.getRandomColor());
                testLayout.addView(textView);
            }
        });
    }
}
