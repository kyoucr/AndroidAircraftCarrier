package com.footprint.androidaircraftcarrier.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.footprint.androidaircraftcarrier.IMyAidlInterface;
import com.footprint.androidaircraftcarrier.ITaskCallback;

import java.util.ArrayList;

/**
 * Created by liquanmin on 15/4/20.
 *
 * AIDL的使用方法：
 * 1）建立一个aidl文件（app右键——>New——>AIDL）
 * 2）Run——>Make Project
 * 之后就会生成对应的.java文件，Project模式下，app_build_generated_source_aidl_debug
 * 下面会看到对应生成的接口文件
 * 之后的使用过程详见代码
 */
public class RemoteAIDLService extends Service{
    public ArrayList<ITaskCallback> callbacks = new ArrayList<ITaskCallback>();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        public String getMsg(){
            for(ITaskCallback cb : callbacks)
                try {
                    cb.actionCallback(0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            return "Hello, this is RPC";
        }

        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
            // Does nothing
        }

        @Override
        public void registerCallback(ITaskCallback cb) throws RemoteException {
            callbacks.add(cb);
        }

        @Override
        public void unregisterCallback(ITaskCallback cb) throws RemoteException {
            callbacks.remove(cb);
        }
    };
}
