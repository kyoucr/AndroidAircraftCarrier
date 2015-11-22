package com.footprint.androidaircraftcarrier.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by liquanmin on 15/11/21.
 */
public class ShellApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(getApplicationContext());
    }
}
