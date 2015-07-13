package com.footprint.androidaircraftcarrier.flappybirds;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by liquanmin on 15/5/21.
 */
public class Pipe {
    private int posY;//Y坐标
    private int pipeV = 5;

    private int verDis;//垂直距离
    private int maxHeight, minHeight;//上面管道的高度

    private Bitmap upBitmap, downBitmap;
    private int gameWidth, gameHeight;
    private int upCurHeight, downCurHeight;
    private RectF upRect = new RectF();
    private RectF downRect = new RectF();

    public Pipe(int gameWidth, int gameHeight, Bitmap upBitmap, Bitmap downBitmap) {
        this.upBitmap = upBitmap;
        this.downBitmap = downBitmap;
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;

        verDis = gameHeight / 5;
        maxHeight = gameHeight / 3;
        minHeight = gameHeight * 2 / 3;

        posY = -gameWidth * 2;
    }

    private void updatePos() {
        if (posY < -upBitmap.getWidth() - 10) {
            posY = gameWidth * 5 / 4;

            upCurHeight = maxHeight + (int) (Math.random() * (minHeight - maxHeight));
            downCurHeight = gameHeight - upCurHeight - verDis;
        }
    }

    public void drawPipeSelf(Canvas canvas) {
        updatePos();

        upRect.set(posY, upCurHeight - upBitmap.getHeight(), posY + upBitmap.getWidth(), upCurHeight);
        downRect.set(posY, gameHeight - downCurHeight, posY + downBitmap.getWidth(), gameHeight);
        canvas.drawBitmap(upBitmap, null, upRect, null);
        canvas.drawBitmap(downBitmap, null, downRect, null);

        posY -= pipeV;
    }

    public boolean ifPipeCrash(Bird bird) {
        return RectF.intersects(upRect, bird.getBirdPos()) || RectF.intersects(downRect, bird.getBirdPos());
    }
}
