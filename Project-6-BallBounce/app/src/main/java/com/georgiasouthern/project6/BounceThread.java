package com.georgiasouthern.project6;

import java.util.Random;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class BounceThread extends Thread {
    boolean running = true;
    SurfaceHolder holder;
    int x, y;
    int w, h;
    int xVelocity, yVelocity;


    public BounceThread(SurfaceHolder holder, int w, int h) {
        this.holder = holder;
        this.w = w;
        this.h = h;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        Canvas canvas = null;
        Paint backPaint = new Paint();
        backPaint.setColor(Color.WHITE);
        Paint forePaint = new Paint();
        forePaint.setColor(Color.RED);
        Paint borPaint = new Paint();
        borPaint.setColor(Color.BLUE);
        borPaint.setStyle(Paint.Style.STROKE);
        Random rand = new Random();
        xVelocity = 2 + rand.nextInt(10); //random Direction
        yVelocity = 2 + rand.nextInt(10);
        long previousTime = System.currentTimeMillis();
        while (running) {
            try {
                canvas = holder.lockCanvas(null);
                synchronized(holder) {
                    x = x + xVelocity;
                    y = y + yVelocity;
                    canvas.drawRect(0, 0, w, h, backPaint);
                    canvas.drawRect(0, 0, w-1, h-1, borPaint);
                    canvas.drawCircle(x, y, 10, forePaint);
                    if (y - 10 < 0 || y + 10 > h) { //ball hits top or bottom of rectangle
                        if (y - 10 < 0) { //top
                            y = 10;
                        } else { //bottom
                            y = h - 10;
                        }
                        yVelocity *= -1; //reverse direction
                    }

                    if (x - 10 < 0 || x + 10 > w) { //ball hits right or left side of rectangle
                        if (x - 10 < 0) { //left
                            x = 10;
                        } else { //right
                            x = w - 10;
                        }
                        xVelocity *= -1; //reverse direction
                    }
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}