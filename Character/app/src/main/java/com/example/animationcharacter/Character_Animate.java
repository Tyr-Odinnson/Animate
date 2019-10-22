package com.example.animationcharacter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.RequiresApi;

public class Character_Animate extends SurfaceView implements Runnable, View.OnTouchListener {

    SurfaceHolder holder;
    Canvas canvas;
    Paint paint;

    Point framesize;

    int FPS = 24;
    int delay = 0;
    int interval = 800;
    long starttime, endtime;
    Rect src, dest;
    int srcx = 0, srcy = 0;
    int frameindex = 0;
    int framecount = 6;
    Bitmap bird;
    int xspeed, yspeed;
    Thread t;

    public Character_Animate(Context context) {
        super(context);
        init(context);
    }

    public Character_Animate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Character_Animate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Character_Animate(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(final Context context) {
        holder = getHolder();
        bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        paint = new Paint();

        framesize = new Point(bird.getWidth()/3, bird.getHeight()/2);
        src = new Rect(srcx, srcy, framesize.x, framesize.y);

        dest = new Rect(src);
        xspeed = 5;
        yspeed = 5;

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                init(getContext());
                canvas = holder.lockCanvas();
                starttime = System.currentTimeMillis();
                draw();
                holder.unlockCanvasAndPost(canvas);
                Log.d("Testing", "surface created called");
            }


            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                src = new Rect(srcx, srcy, framesize.x, framesize.y);
                dest = new Rect(src);
                init(context);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                init(context);
            }
        });
    }

    private void draw() {
        canvas.drawBitmap(bird, src, dest, paint);
        new Thread(this).start();
        Log.d("Testing lol", "draw called");
    }
    private void update_frame() {
        ++frameindex;
        frameindex %= framecount;
        src.set(framesize.x * frameindex, 0, framesize.x * (frameindex + 1), framesize.y);
        endtime = System.currentTimeMillis();
        long timediff = endtime - starttime;
        FPS = (int) (interval/timediff);
        delay = (int)Math.abs(timediff - delay/FPS);
        // Bird Fly
        starttime = endtime;
        Log.d("Testng", "delay" + delay + "FPS" + FPS);
    }

    @Override
    public void run() {
        while (true) {
            if (holder.getSurface().isValid()) {
                canvas = holder.lockCanvas();
                try {
                    Thread.sleep(delay);
                    canvas.drawColor(Color.WHITE);
                    draw();
                    update_frame();
                    bird_fly();
                    holder.unlockCanvasAndPost(canvas);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.d("Testiiiing", "run called!");
        }
    }

    public void bird_fly() {
        dest.left += xspeed;
        dest.right += yspeed;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            bird_fly();
        }
        Log.d("Testing", "on Touch called");
        return true;
    }


}
