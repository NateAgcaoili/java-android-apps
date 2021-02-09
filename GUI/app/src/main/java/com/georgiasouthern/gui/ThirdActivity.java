package com.georgiasouthern.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void buttonReturn(View view) {
        EditText et = findViewById(R.id.editT);
        String msg = et.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Ret", msg);
        setResult(Activity.RESULT_OK, returnIntent);
    }
}