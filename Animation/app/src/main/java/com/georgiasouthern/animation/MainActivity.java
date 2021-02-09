package com.georgiasouthern.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    boolean isOn;

    public void startAnim(View view) {
        isOn = true;
        runAnim();
    }

    private void runAnim() {
        ImageView iv = findViewById(R.id.imageView);
        if (isOn){
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.move); //loading rotate animation from anim folder
            anim.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) { //as soon as animation ends, restart it
                    runAnim();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            iv.startAnimation(anim);
        } else {
            iv.clearAnimation();
        }
    }

    public void stopAnim(View view) {
//        ImageView iv = findViewById(R.id.imageView);
//        iv.clearAnimation();
        isOn = false;
        runAnim();
    }
}