package com.footprint.androidaircraftcarrier.flappybirds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.footprint.androidaircraftcarrier.utils.PixelUtils;

/**
 * Created by liquanmin on 15/5/21.
 */
public class Bird {
    /**
     * 鸟在屏幕高度的2/3位置
     */
    private static final float RADIO_POS_HEIGHT = 2 / 3F;

    private int posX, posY;//鸟的坐标
    private int birdWidth, birdHeight;//鸟的体积

    /**
     * 鸟的宽度 30dp
     */
    private static final int BIRD_SIZE = 30;

    private Bitmap birdBmp;

    private RectF birdRect = new RectF();

    public Bird(Context context, int gameWidth, int gameHeight, Bitmap birdBmp){
        posX = (gameWidth - birdBmp.getWidth())/3;
        posY = (int)(gameHeight * (1 - RADIO_POS_HEIGHT));

        // 计算鸟的宽度和高度
        birdWidth = PixelUtils.dp2px(context, BIRD_SIZE);
        birdHeight = (int) (birdWidth * 1.0f / birdBmp.getWidth() * birdBmp.getHeight());

        this.birdBmp = birdBmp;
    }

    public void drawBirdSelf(Canvas canvas){
        birdRect.set(posX, posY, posX + birdWidth, posY + birdHeight);
        canvas.drawBitmap(birdBmp, null, birdRect, null);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getBirdWidth() {
        return birdWidth;
    }

    public void setBirdWidth(int birdWidth) {
        this.birdWidth = birdWidth;
    }

    public int getBirdHeight() {
        return birdHeight;
    }

    public void setBirdHeight(int birdHeight) {
        this.birdHeight = birdHeight;
    }
}
