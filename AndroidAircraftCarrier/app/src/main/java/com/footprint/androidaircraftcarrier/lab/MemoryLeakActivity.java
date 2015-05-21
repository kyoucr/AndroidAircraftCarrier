package com.footprint.androidaircraftcarrier.lab;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.footprint.androidaircraftcarrier.R;

/**
 * Created by liquanmin on 15/5/21.
 * 【测试】造成严重的内存泄露，可以使用MAT探查，非常简单的看出该Activity的问题，另外：App的内存限制已经可以
 * 进行灵活调节，试验时本App最高可使用内存接近200M，系统会逐步扩大应用可使用的内存量，每次扩大都会导致应用卡顿，
 * 直至最后内存不足，应用挂掉。
 */
public class MemoryLeakActivity extends Activity{
    private byte[] memoryEater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoryleak);

        memoryEater = new byte[1024 * 1024];

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10000 * 1000);
    }
}
