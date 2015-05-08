package com.footprint.androidaircraftcarrier.fresco;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.footprint.androidaircraftcarrier.R;

public class FrescoActivity extends Activity {

    SimpleDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);

        draweeView = (SimpleDraweeView)findViewById(R.id.my_image_view);
        draweeView.setImageURI(Uri.parse("http://b.hiphotos.baidu.com/image/pic/item/0823dd54564e9258c8d53af19e82d158cdbf4ecf.jpg"));
    }
}
