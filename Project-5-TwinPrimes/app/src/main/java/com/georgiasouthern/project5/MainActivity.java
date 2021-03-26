package com.georgiasouthern.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText userNumEnter;
    TextView twinsDisplay;
    Handler handler;
    String twinsDisplayText;
    int userNum;
    int primeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNumEnter = findViewById(R.id.editTextNumber);
        twinsDisplay = findViewById(R.id.textView2);
        twinsDisplay.setMovementMethod(new ScrollingMovementMethod());
        handler = new Handler(Looper.getMainLooper());
    }

    static boolean primeCheck(int num) {
        boolean isPrime = true;
        int numSquare = (int)Math.sqrt(num);
        for(int i = 2; isPrime && i <= numSquare; i++) {
            if (num % i == 0) {
                isPrime = false;
            }
        }
        return isPrime;
    }

    public void threadButtonClick(View view) {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                try{
                    userNum = Integer.parseInt(userNumEnter.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                twinsDisplayText = "twins:";
                primeCount = 0;
                for(int i = 2; i < userNum-1; i++) {
                    if(primeCheck(i) && primeCheck(i + 2)) {
                        twinsDisplayText = twinsDisplayText + " [" + i + "," + (i+2) + "]";
                        primeCount++;
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        twinsDisplayText = twinsDisplayText + "\ntotal: " + primeCount;
                        twinsDisplay.setText(twinsDisplayText);
                    }
                });
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void asyncTaskButtonClick(View view) {
        new PrimeAsyncTask().execute();
    }

    class PrimeAsyncTask extends AsyncTask<Integer, Integer, Double> {

        @Override
        protected Double doInBackground(Integer... integers) {
            try{
                userNum = Integer.parseInt(userNumEnter.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            twinsDisplayText = "twins:";
            primeCount = 0;
            for(int i = 2; i < userNum-1; i++) {
                if(primeCheck(i) && primeCheck(i + 2)) {
                    twinsDisplayText = twinsDisplayText + " [" + i + "," + (i+2) + "]";
                    publishProgress(i);
                    primeCount++;
                }
            }
            return null;
        }

        protected void onProgressUpdate(Integer...params) {
            twinsDisplay.setText(twinsDisplayText);
        }

        protected void onPostExecute(Double result) {
            twinsDisplayText = twinsDisplayText + "\ntotal: " + primeCount;
            twinsDisplay.setText(twinsDisplayText);
        }
    }
}