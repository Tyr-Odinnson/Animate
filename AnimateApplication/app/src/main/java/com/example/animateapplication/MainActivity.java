package com.example.animateapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView bat_img;
    ConstraintLayout MyLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLayout = findViewById(R.id.MyLayout);
        bat_img = findViewById(R.id.bat);
        bat_img.setBackgroundResource(R.drawable.animatebattery);
        animationDrawable = (AnimationDrawable)bat_img.getBackground();
        animationDrawable.start();
    }

    public void onMoveLeft(View view) {
        if (bat_img.getX()+bat_img.getWidth() <= MyLayout.getWidth())
        bat_img.animate().translationXBy(-485).setDuration(1000);
        else {
             bat_img.animate().translationXBy(-485).setDuration(1000);
        }
    }

    public void onMoveRight(View view) {
        if (bat_img.getX()+bat_img.getWidth() <= MyLayout.getWidth())
            bat_img.animate().translationXBy(485).setDuration(1000);
        else {
            bat_img.animate().translationXBy(-485).setDuration(1000);
        }

    }

    public void rotateImage(View view) {

        bat_img.animate().rotationBy(90).setDuration(1000);
//        bat_img.animate().rotationBy(45).setDuration(1000);
//        bat_img.animate().rotationBy(45).setDuration(1000);
//        bat_img.animate().rotationBy(45).setDuration(1000);
        // bat_img.animate().rotationYBy(180).setDuration(1000);
    }

    public void resizeImage(View view) {

        bat_img.animate().scaleXBy(2);
        bat_img.animate().scaleYBy(2);

    }

    public void resizeDown(View view) {
        bat_img.animate().scaleXBy(-2);
        bat_img.animate().scaleYBy(-2);
    }

    public void onMoveUp(View view) {
        if (bat_img.getY()+bat_img.getHeight() <= MyLayout.getHeight())
            bat_img.animate().translationYBy(-492).setDuration(1000);
        else {
            bat_img.animate().translationYBy(492).setDuration(1000);
        }
    }

    public void onMoveDown(View view) {
        if (bat_img.getY()+bat_img.getHeight() <= MyLayout.getHeight())
            bat_img.animate().translationYBy(492).setDuration(1000);
        else {
            bat_img.animate().translationYBy(-492).setDuration(1000);
        }
    }
}
