package com.footprint.androidaircraftcarrier.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
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
public class ServiceNextActivity extends Activity{
    protected Button bindButton, msgButton;
    protected TextView serviceText;

    LocalService bindService;
    boolean bindBound = false, msgBound = false;

    /** Messenger for communicating with the service. */
    Messenger msgMessenger = null;
    Messenger clientMessenger = null;
    Handler clientHandler = null;

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
                    Toast.makeText(ServiceNextActivity.this, "number: " + num, Toast.LENGTH_SHORT).show();
                    unbindService(bindConnection);
                    bindBound = false;
                }else{
                    // Bind to LocalService
                    Intent intent = new Intent(ServiceNextActivity.this, LocalService.class);
                    bindService(intent, bindConnection, Context.BIND_AUTO_CREATE);
                }
            }
        });

        msgButton = (Button)findViewById(R.id.messagerButton);
        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!msgBound)
                    return;
                // Create and send a message to the service, using a supported 'what' value
                Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
                msg.replyTo = clientMessenger;
                try {
                    msgMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        serviceText = (TextView)findViewById(R.id.serviceText);

        clientHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(ServiceNextActivity.this, "Msg_ID:" + msg.what, Toast.LENGTH_SHORT).show();
            }
        };

//        startService(new Intent(ServiceNextActivity.this, LocalService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        /**
         * Bind操作是异步的，并不直接返回，所以需要Connection对象，这其中是两个回调
         * Only activities, services, and content providers can bind to a service,
         * you cannot bind to a service from a broadcast receiver.
         * */

        // Bind to LocalService
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, bindConnection, Context.BIND_AUTO_CREATE);

        // Messenger Service
        // Bind to the service
        bindService(new Intent(this, MessengerService.class), msgConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        /**
         * When your client is destroyed, it will unbind from the service
         * 但是会报错：has leaked ServiceConnection
         * */
        if (bindBound) {
            unbindService(bindConnection);
            bindBound = false;
        }

        if (msgBound) {
            unbindService(msgConnection);
            msgBound = false;
        }
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

    /**
     * Class for interacting with the main interface of the service.
     */
    private ServiceConnection msgConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            msgMessenger = new Messenger(service);
            clientMessenger = new Messenger(clientHandler);
            msgBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            msgMessenger = null;
            msgBound = false;
        }
    };
}
