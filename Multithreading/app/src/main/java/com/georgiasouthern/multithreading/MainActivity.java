package com.georgiasouthern.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBottonClick(View view) {
        Runnable r = new Runnable(){
            @Override
            public void run() {
                double x = 1;
                for (int i = 0; i < 100; i++) {
                    System.out.println("Hello, runnable");
                    for (int j = 0; j < 1000000; j++) {
                        x = (3 *x*x +3) / ( 4*x*x+5);
                    }
                    System.out.println("x=" + x);
                }
                TextView textView = findViewById(R.id.textView);
                textView.setText("x = " + x);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
//        MyThread myThread = new MyThread();
//        myThread.start();
        Toast.makeText(this, "This is the Main Thread", Toast.LENGTH_LONG).show();
    }

    class MyThread extends Thread {
        public void run() {
            double x = 0;
            for (int i = 0; i < 100; i++) {
                System.out.println("Hello, threads");
                for (int j = 0; j < 1000000; j++) {
                    x = 2 *x*x + 4*x-3;
                }
                System.out.println("x=" + x);
            }
        }
    }
}