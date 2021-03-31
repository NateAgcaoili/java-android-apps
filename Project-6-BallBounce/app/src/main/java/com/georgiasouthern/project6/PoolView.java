package com.georgiasouthern.project6;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class PoolView extends SurfaceView implements SurfaceHolder.Callback {
    BounceThread thread;

    public PoolView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public PoolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public PoolView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        float dx = x - thread.x;
        float dy = y - thread.y;
        if (dx*dx + dy*dy < 101) {
            Toast.makeText(this.getContext(), "Hit!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new BounceThread(holder, this.getWidth(), this.getHeight());
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        thread.setRunning(false);
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException ex){}
        }

    }

}