package com.yoobee.animation_surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.RequiresApi;

public class SurfaceView extends android.view.SurfaceView implements Runnable {

    Context context;
    Bitmap img[] = new Bitmap[10];
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Float imgX, imgY;
    int index = 0;
    int img_list[] = {R.drawable.runningcat1,R.drawable.runningcat2,R.drawable.runningcat3,R.drawable.runningcat4,R.drawable.runningcat5,
            R.drawable.runningcat6,R.drawable.runningcat7,R.drawable.runningcat8};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SurfaceView(Context context) {
        super(context);
        init(context);
    }

    public SurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public SurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    public void init(final Context context){

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                init(context);
                canvas = surfaceHolder.lockCanvas();
                imgX = (float)100;
                imgY = (float)canvas.getHeight()/5;
                draw(canvas);
                Log.d("Testing", "Surface created");
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                Log.d("Testing", "Surface changed");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.d("Testing", "Surface destroyed");
            }
        });
    }

    public void onDrawImage(){
        for (int i = 0; i < img_list.length; i++){
            img[i] = BitmapFactory.decodeResource(getResources(),img_list[i]);
            img[i] = Bitmap.createScaledBitmap(img[i],900,500,true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        onDrawImage();
        new Thread((Runnable) this).start();
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
    @Override
    public void run(){

        while(true) {
            if (surfaceHolder.getSurface().isValid()) {
                canvas = surfaceHolder.lockCanvas();
                canvas.drawBitmap(img[index], imgX, imgY, null);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                updateImage();
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void updateImage(){
        index = ++index % img_list.length;
        Log.d("Testing","Update called");
    }
}
