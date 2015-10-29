package com.footprint.androidaircraftcarrier.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import de.greenrobot.event.EventBus;

/**
 * Created by liquanmin on 15/10/20.
 */
public class EBActivity extends Activity {
    public class Message {
        public String msgContent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
        Thread thread = new Thread() {
            @Override
            public void run() {
                Log.e("@@@Msg", "线程开始");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.msgContent = "EB测试";
                Log.e("@@@Msg", "消息发送");
                for (int index = 0; index < 1; index++)
                    EventBus.getDefault().post(msg);
            }
        };
        thread.start();
    }

    public void onEventMainThread(Message msg) {
        Log.e("@@@Msg", msg.msgContent);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
