package com.footprint.androidaircraftcarrier.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footprint.androidaircraftcarrier.R;

/**
 * Created by liquanmin on 15/4/15.
 */
public class ServiceActivity extends Activity{
    protected Button bindButton, msgButton;
    protected TextView serviceText;

    LocalService bindService;
    boolean bindBound = false, msgBound = false;

    /** Messenger for communicating with the service. */
    Messenger msgMessenger = null;

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
        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!msgBound)
                    return;
                // Create and send a message to the service, using a supported 'what' value
                Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
                try {
                    msgMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        serviceText = (TextView)findViewById(R.id.serviceText);
    }

    @Override
    protected void onStart() {
        super.onStart();
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

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            bindService = binder.getService();
            bindBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bindBound = false;
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
