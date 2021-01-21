package com.georgiasouthern.agcaoiliproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int fibNumOne = 0;
    int fibNumTwo = 1;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextClick(View view) {
        temp = fibNumTwo;
        fibNumTwo += fibNumOne;
        fibNumOne = temp;
        TextView tv = findViewById(R.id.textView);
        tv.setText(Integer.toString(fibNumTwo));
    }

    public void resetClick(View view) {
        fibNumOne = 0;
        fibNumTwo = 1;
        TextView tv = findViewById(R.id.textView);
        tv.setText(Integer.toString(fibNumTwo));
    }
}