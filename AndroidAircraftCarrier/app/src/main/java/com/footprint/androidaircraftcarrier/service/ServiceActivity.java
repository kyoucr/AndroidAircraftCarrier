package com.footprint.androidaircraftcarrier.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footprint.androidaircraftcarrier.R;

/**
 * Created by liquanmin on 15/4/15.
 * Service学习三大点：1)Bind/Start Service;  2)Messenger;  3)AIDL
 */
public class ServiceActivity extends Activity{
    protected Button bindButton, msgButton;
    protected TextView serviceText;

    LocalService bindService;
    boolean bindBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        bindButton = (Button)findViewById(R.id.bindButton);
        bindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bindBound) {
                    int num = bindService.getRandomNumber();
                    Toast.makeText(ServiceActivity.this, "number: " + num, Toast.LENGTH_SHORT).show();
                }
            }
        });

        msgButton = (Button)findViewById(R.id.messagerButton);
        msgButton.setText("第二页面");
        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, ServiceNextActivity.class);
                startActivity(intent);
            }
        });

        serviceText = (TextView)findViewById(R.id.serviceText);

        startService(new Intent(ServiceActivity.this, LocalService.class));

        /**
         * Bind操作是异步的，并不直接返回，所以需要Connection对象，这其中是两个回调
         * Only activities, services, and content providers can bind to a service,
         * you cannot bind to a service from a broadcast receiver.
         * */

        // Bind to LocalService
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, bindConnection, Context.BIND_AUTO_CREATE);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection bindConnection = new ServiceConnection() {

        /**
         * The Android system calls this when the connection to the service is unexpectedly lost,
         * such as when the service has crashed or has been killed.
         * This is not called when the client unbinds.
         * 【实验】全部unbind之后，Service的onDestroy()方法被调用，但是该方法没有被调用。
         * */
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.e("FP", "Service Disconnected");
            bindBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            bindService = binder.getService();
            bindBound = true;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unbind from the service
        /**
         * When your client is destroyed, it will unbind from the service
         * 但是会报错：has leaked ServiceConnection
         * */
        if (bindBound) {
            unbindService(bindConnection);
            bindBound = false;
        }
    }
}
