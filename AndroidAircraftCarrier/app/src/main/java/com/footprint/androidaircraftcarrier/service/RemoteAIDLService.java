package com.footprint.androidaircraftcarrier.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.footprint.androidaircraftcarrier.IMyAidlInterface;

/**
 * Created by liquanmin on 15/4/20.
 */
public class RemoteAIDLService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        public String getMsg(){
            return "Hello, this is RPC";
        }

        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
            // Does nothing
        }
    };
}
