package com.footprint.androidaircraftcarrier.utils;

import android.graphics.Color;

/**
 * Created by liquanmin on 15/5/15.
 */
public class ColorUtils {
    public static int getRandomColor(){
        int red = (int)(Math.random() * 255);
        int green = (int)(Math.random() * 255);
        int blue = (int)(Math.random() * 255);
        return Color.rgb(red, green, blue);
    }
}
