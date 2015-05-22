package com.footprint.androidaircraftcarrier.flappybirds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.footprint.androidaircraftcarrier.R;

/**
 * Created by liquanmin on 15/5/21.
 */
public class FlappyBirdView extends GameSurfaceView {

    private Bitmap bgBitmap, birdBitmap, floorBitmap, upPipeBitmap, downPipeBitmap;
    private int viewHeight, viewWidth;
    private RectF viewRect = new RectF();

    private Bird bird;
    private Floor floor;
    private Pipe pipe;

    public FlappyBirdView(Context context) {
        super(context);
    }

    public FlappyBirdView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bgBitmap = loadBitmap(R.drawable.bg1);
        birdBitmap = loadBitmap(R.drawable.b1);
        floorBitmap = loadBitmap(R.drawable.floor);

        upPipeBitmap = loadBitmap(R.drawable.g2);
        downPipeBitmap = loadBitmap(R.drawable.g1);
    }

    private Bitmap loadBitmap(int resId) {
        return BitmapFactory.decodeResource(getResources(), resId);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.viewHeight = height;
        this.viewWidth = width;
        viewRect.set(0, 0, width, height);

        bird = new Bird(getContext(), width, height, birdBitmap);
        floor = new Floor(width, height, floorBitmap);
        pipe = new Pipe(width,height, upPipeBitmap, downPipeBitmap);
        super.surfaceChanged(holder, format, width, height);
    }

    @Override
    public void drawCanvas(Canvas canvas) {
        canvas.drawBitmap(bgBitmap, null, viewRect, null);
        floor.drawFloorSelf(canvas);
        pipe.drawPipeSelf(canvas);
        bird.drawBirdSelf(canvas);

        calculateIfGameOver();
    }

    public void calculateIfGameOver(){
        if(!bird.ifBirdStillFlying() || pipe.ifPipeCrash(bird)) {
            isRunning = false;
            Toast.makeText(getContext(), "Game Over!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            bird.upFly();
        }
        return true;
    }
}
