package com.footprint.androidaircraftcarrier.flappybirds;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by liquanmin on 15/5/21.
 */
public class Floor {
    private int floorX, floorY;//位置
    private int floorV = 3;//移动速度
    private Bitmap floorBitmap;
    private int horWidthDiff = 0;

    private int currentDiff = 0;

    private Rect floorRect = new Rect();
    private RectF destRect = new RectF();

    private int gameWidth, gameHeight;

    public Floor(int gameWidth, int gameHeight, Bitmap floorBitmap) {
        floorX = 0;
        floorY = gameHeight - floorBitmap.getHeight();

        this.floorBitmap = floorBitmap;
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;

        horWidthDiff = floorBitmap.getWidth() - gameWidth;

        destRect.set(0, floorY, gameWidth, gameHeight);
    }

    public void drawFloorSelf(Canvas canvas) {
        currentDiff += floorV;
        currentDiff %= (horWidthDiff - floorV);

        floorRect.set(currentDiff, 0, currentDiff + gameWidth, floorBitmap.getHeight());
        canvas.drawBitmap(floorBitmap, floorRect, destRect, null);
    }
}
