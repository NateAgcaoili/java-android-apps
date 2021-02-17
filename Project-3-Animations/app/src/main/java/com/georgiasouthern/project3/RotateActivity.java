package com.georgiasouthern.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Switch;

public class RotateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
    }

    public void rotateSwitch(View view) {
        Switch rSwitch = findViewById(R.id.rotate_switch);
        ImageView georgiaPic = findViewById(R.id.georgia_image);

        if (rSwitch.isChecked()) {
            Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
            georgiaPic.startAnimation(rotate);
        } else {
            georgiaPic.clearAnimation();
        }
    }
}