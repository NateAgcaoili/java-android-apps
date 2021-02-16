package com.georgiasouthern.myview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchClicked(View view) {
        Switch sw = findViewById(R.id.switch1);
        if (sw.isChecked()) {
            Toast.makeText(this, "switch is on", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "switch is off", Toast.LENGTH_SHORT).show();
        }
    }
}