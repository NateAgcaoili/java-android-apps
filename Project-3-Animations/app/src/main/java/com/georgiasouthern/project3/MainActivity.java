package com.georgiasouthern.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rotateClick(View view) {
        Intent intent = new Intent(this, RotateActivity.class);
        startActivity(intent);
    }

    public void scaleClick(View view) {
        Intent intent = new Intent(this, ScaleActivity.class);
        startActivity(intent);
    }
}