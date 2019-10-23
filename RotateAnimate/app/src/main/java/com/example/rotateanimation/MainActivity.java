package com.example.rotateanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;
    AnimationDrawable drawable;
    TextView text;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.myLayout);
        text = findViewById(R.id.text);
        drawable = (AnimationDrawable) layout.getBackground();

        drawable.setEnterFadeDuration(2500);

        drawable.setExitFadeDuration(4500);

        drawable.start();

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(5000);

//        text.startAnimation(rotateAnimation);
//
//
//
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                RotateAnimation rotateAnimation1 = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                rotateAnimation1.setInterpolator(new LinearInterpolator());
//                rotateAnimation1.setDuration(7000);
//
//                text.startAnimation(rotateAnimation1);
//            }
//        });

    }
}

