package com.footprint.androidaircraftcarrier.lab;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.footprint.androidaircraftcarrier.R;

/**
 * Created by liquanmin on 15/10/28.
 */
public class StrictModeActivity extends Activity implements View.OnClickListener{
    View buttonA, buttonB, buttonC;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strictmode);

        buttonA = findViewById(R.id.buttonA);
        buttonA.setOnClickListener(this);

        buttonB = findViewById(R.id.buttonB);
        buttonB.setOnClickListener(this);

        buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        mHandler.postDelayed(new Runnable() {
            Activity activity = null;
            @Override
            public void run() {
                activity = StrictModeActivity.this;
            }
        }, 180000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonA:
                methodA();
                break;
            case R.id.buttonB:
                methodB();
                break;
            case R.id.buttonC:
                new Thread(){
                    @Override
                    public void run() {
                        methodB();
                    }
                }.start();
                break;
        }
    }

    private void methodA(){
        Log.d("StrictModeActivity", "A - 执行开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("StrictModeActivity", "A - 执行完毕");
    }

    private void methodB(){
        Log.d("StrictModeActivity", "B - 执行开始");
        StrictMode.noteSlowCall("主线程调用耗时函数——StrictMode");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("StrictModeActivity", "B - 执行完毕");
    }
}
