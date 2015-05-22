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
    private int gameHeight, gameWidth;

    /**
     * 鸟的宽度 30dp
     */
    private static final int BIRD_SIZE = 30;

    private Bitmap birdBmp;

    private RectF birdRect = new RectF();

    private final int UP_FLY_DIS;
    private final int DOWN_DIS = 15;
    private int upFlyTimes;

    public Bird(Context context, int gameWidth, int gameHeight, Bitmap birdBmp) {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;

        posX = gameWidth / 3 - birdBmp.getWidth();
        posY = (int) (gameHeight * (1 - RADIO_POS_HEIGHT));

        // 计算鸟的宽度和高度
        birdWidth = PixelUtils.dp2px(context, BIRD_SIZE);
        birdHeight = (int) (birdWidth * 1.0f / birdBmp.getWidth() * birdBmp.getHeight());

        this.birdBmp = birdBmp;

        UP_FLY_DIS = gameHeight / 8;
    }

    public void drawBirdSelf(Canvas canvas) {
        birdRect.set(posX, posY, posX + birdWidth, posY + birdHeight);
        canvas.drawBitmap(birdBmp, null, birdRect, null);

        posY += DOWN_DIS;
    }

    public void upFly() {
        posY -= UP_FLY_DIS;
        if (posY < 0)
            posY = 0;
    }

    public boolean ifBirdStillFlying(){
        return posY + birdHeight < gameHeight;
    }

    public RectF getBirdPos(){
        return birdRect;
    }
}
