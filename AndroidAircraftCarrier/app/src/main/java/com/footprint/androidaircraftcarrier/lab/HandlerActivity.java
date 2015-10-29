package com.footprint.androidaircraftcarrier.lab;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by liquanmin on 15/10/20.
 *
 * Handler里面的消息是串行一个接一个的处理的，此处验证
 */
public class HandlerActivity extends Activity{
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("@@@AAA", msg.what + "  ##");
                }else{
                    Log.d("@@@AAA", msg.what + "  ##");
                }
            }
        };

        mHandler.sendEmptyMessageDelayed(1, 3000);
        mHandler.sendEmptyMessageDelayed(2000, 4000);
    }
}
