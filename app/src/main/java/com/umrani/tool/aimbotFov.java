package com.umrani.tool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.umrani.tool.R;

public class aimbotFov extends View {
    private Paint circle;
    float xCircle, yCircle;
    int radius = 300;

    public aimbotFov(Context context) {
        super(context);
        init();
    }

    public aimbotFov(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public aimbotFov(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circle = new Paint();
        circle.setStyle(Paint.Style.STROKE);
        circle.setStrokeWidth(5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            circle.setColor(getContext().getColor(R.color.accent_red));
        }
        circle.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(xCircle, yCircle, radius, circle);
    }

    public void setPositionCircle(float x, float y) {
        xCircle = x ;
        yCircle = y ;
    }
}
