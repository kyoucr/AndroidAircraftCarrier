package com.footprint.androidaircraftcarrier.lab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footprint.androidaircraftcarrier.R;

/**
 * Created by liquanmin on 15/7/20.
 */
public class LCFragment extends Fragment{
    private TextView textView;
    String text = "";
    public static LCFragment newInstance(String text){
        LCFragment lcFragment = new LCFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", text);
        lcFragment.setArguments(bundle);
        return lcFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null){
            text = savedInstanceState.getString("msg");
        }else{
            text = getArguments().getString("msg");
        }

        View view = inflater.inflate(R.layout.fragment_lc, null);
        textView = (TextView)view.findViewById(R.id.textView);

        textView.setText(text);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("msg", "Hello, I am saved!");
        super.onSaveInstanceState(outState);
    }
}
