package com.georgiasouthern.project4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    float cx = 200;
    float cy = 300;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int def) {
        super(context, attrs, def);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint whitePaint = new Paint();
        Paint blackPaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        blackPaint.setColor(Color.BLACK);
        canvas.drawCircle(cx, cy, 81, blackPaint);
        canvas.drawArc(cx-80, cy-80, cx+80, cy+80, 0, -180, true, whitePaint);
        canvas.drawCircle(cx + 40, cy, 40, whitePaint);
        canvas.drawCircle(cx - 40, cy, 40, blackPaint);
        canvas.drawCircle(cx + 40, cy, 10, blackPaint);
        canvas.drawCircle(cx - 40, cy, 10, whitePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        cx = event.getX();
        cy = event.getY();
        this.invalidate();
        return true;
    }
}
