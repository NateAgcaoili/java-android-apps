package com.georgiasouthern.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Switch;

public class ScaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
    }

    public void scaleSwitch(View view) {
        Switch sSwitch = findViewById(R.id.scale_switch);
        ImageView georgiaMap = findViewById(R.id.georgia_map);

        if (sSwitch.isChecked()) {
            Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
            scale.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    scaleSwitch(view);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            georgiaMap.startAnimation(scale);
        } else {
            georgiaMap.clearAnimation();
        }
    }
}