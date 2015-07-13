package com.footprint.androidaircraftcarrier.flappybirds;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by liquanmin on 15/5/21.
 */
public abstract class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;

    private Canvas mCanvas;

    private Thread thread;

    protected boolean isRunning;

    public GameSurfaceView(Context context) {
        super(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHolder = getHolder();
        mHolder.addCallback(this);

        setZOrderOnTop(true);
        mHolder.setFormat(PixelFormat.TRANSLUCENT);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    @Override
    public void run() {
        while(isRunning){
            long start = System.currentTimeMillis();

            try{
                mCanvas = mHolder.lockCanvas();
                if(mCanvas != null){
                    drawCanvas(mCanvas);
                }
            }catch (Exception e){

            }finally {
                if(mCanvas != null){
                    mHolder.unlockCanvasAndPost(mCanvas);
                }
            }

            long end = System.currentTimeMillis();
            try{
                if(end - start < 20){
                    Thread.sleep(20 - (end - start));
                }
            }catch (InterruptedException e){
                Log.e("GameFlabbyBird", e.toString());
            }
        }
    }

    public abstract void drawCanvas(Canvas canvas);
}
