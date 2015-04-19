package com.footprint.androidaircraftcarrier.lab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.footprint.androidaircraftcarrier.R;
/**
 * 【实验】从A-Activity跳转到B-Activity的时候生命周期如下：
 * A-Pause，B-Start，B-Resume，A-Stop
 * 从B-Activity回到A-Activity的生命周期如下：
 * B-Pause，A-Start，A-Resume，B-Stop，B-Destroy
 * 【结论】即将显示的Activity在显示完成（即Resume）之后，之前的Activity才会Stop以及可能的Destroy
 * 【适用】服务的绑定和非绑定，官方文档建议：
 * You should usually not bind and unbind during your activity's onResume() and onPause()
 * 而应该在其余两对生命周期中完成bind和unbind，原因在于：
 * 在其余两对生命周期中执行动作不会引起Service的反复创建和销毁！
 * */
public class LifeCycle extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    @Override
    protected void onStart() {
        Log.e("FP", "LifeCycle-Start");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("FP", "LifeCycle-Resume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("FP", "LifeCycle-Pause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("FP", "LifeCycle-Stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("FP", "LifeCycle-Destroy");
        super.onDestroy();
    }
}
