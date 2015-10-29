package com.footprint.androidaircraftcarrier.lab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.footprint.androidaircraftcarrier.R;

/**
 * Created by liquanmin on 15/7/20.
 */
public class FragmentLCActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragmentlc);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, LCFragment.newInstance("I am new!")).addToBackStack(null).commit();

        LCFragment testFragment = LCFragment.newInstance("test");
        getSupportFragmentManager().beginTransaction().attach(testFragment).commit();
        getSupportFragmentManager().beginTransaction().detach(testFragment).commit();
        getSupportFragmentManager().beginTransaction().attach(testFragment).commit();
    }
}
