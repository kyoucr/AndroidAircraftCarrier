package com.footprint.androidaircraftcarrier.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

/**
 * Created by liquanmin on 15/4/15.
 * 注意：服务必须在AM中注册，否则不能调用
 */
public class LocalService extends Service {
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    @Override
    public void onCreate() {
        Log.e("FP", "Service Create");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        //所有的client unbind之后，即被销毁
        Log.e("FP", "Service Destroy");
        super.onDestroy();
    }
}
