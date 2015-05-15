package com.footprint.androidaircraftcarrier.animation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footprint.androidaircraftcarrier.R;
import com.footprint.androidaircraftcarrier.utils.ColorUtils;

/**
 * Created by liquanmin on 15/5/15.
 */
public class ScreenSlidePageFragment extends Fragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_viewpager, container, false);

        textView = (TextView)rootView.findViewById(R.id.textView);
        textView.setBackgroundColor(ColorUtils.getRandomColor());

        return rootView;
    }
}
