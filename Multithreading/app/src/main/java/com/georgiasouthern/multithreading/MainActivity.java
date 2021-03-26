package com.georgiasouthern.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    double x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler(Looper.getMainLooper());
    }

    public void onBottonClick(View view) {
        new MyAsyncTask().execute(2);
//        Runnable r = new Runnable(){
//            @Override
//            public void run() {
//                x = 1;
//                for (int i = 0; i < 100; i++) {
//                    System.out.println("Hello, runnable");
//                    for (int j = 0; j < 1000000; j++) {
//                        x = (3 *x*x +3) / ( 4*x*x+5);
//                    }
//                    System.out.println("x=" + x);
//                }
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        TextView textView = findViewById(R.id.textView);
//                        textView.setText("x = " + x);
//                    }
//                });
//            }
//        };
//        Thread thread = new Thread(r);
//        thread.start();
////        MyThread myThread = new MyThread();
////        myThread.start();
//        Toast.makeText(this, "This is the Main Thread", Toast.LENGTH_LONG).show();
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

    class MyAsyncTask extends AsyncTask<Integer, Integer, Double> {

        @Override
        protected Double doInBackground(Integer... integers) {
            x = integers[0];
            for (int i = 0; i < 100; i++) {
                publishProgress(i);
                System.out.println("Hello, runnable");
                for (int j = 0; j < 1000000; j++) {
                    x = (3 *x*x +3) / ( 4*x*x+5);
                }
                System.out.println("x=" + x);
            }
            return x;
        }
        protected void onProgressUpdate(Integer...params) {
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(params[0]);
        }
        protected void onPostExecute(Double result) {
            TextView textView = findViewById(R.id.textView);
            textView.setText("x = " + result);
        }
    }
}