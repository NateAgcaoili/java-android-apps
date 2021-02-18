package com.georgiasouthern.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.rgb(200,200,100));
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(72);
        canvas.drawText("Hello", 500, 200, paint);
        paint.setColor(Color.rgb(64,120, 120));
        canvas.drawArc(250, 600, 500, 800, 45, 270, true, paint);
        Path path = new Path();
        path.moveTo(400, 400);
        path.quadTo(500, 200, 600, 400);
        path.cubicTo(600, 600, 400, 300, 400, 500);
        paint.setColor(Color.CYAN);
        canvas.drawPath(path, paint);
        paint.setColor(Color.argb(128, 0, 0, 255));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawCircle(cx,cy, 80, paint);
        paint.setColor(Color.argb(128,200,200,100));
        canvas.drawCircle(cx-30,cy,80,paint);
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(200, 200, 500, 500, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        cx = event.getX();
        cy = event.getY();
        this.invalidate();
        return true;
    }

}