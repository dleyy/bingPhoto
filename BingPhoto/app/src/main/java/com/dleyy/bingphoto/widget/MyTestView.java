package com.dleyy.bingphoto.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.dleyy.bingphoto.R;

/**
 * Created by dleyy on 2017/11/28.
 */
public class MyTestView extends View {

    private Paint mpaint;

    private Paint rePaint;

    public MyTestView(Context context) {
        super(context);
        initPaint();
    }

    public MyTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public MyTestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = ((BitmapDrawable) getResources()
                .getDrawable(R.mipmap.ic_launcher)).getBitmap();

        canvas.drawBitmap(bitmap, 30, 40, rePaint);

        Matrix matrix = new Matrix();


        canvas.drawBitmap(bitmap, 100, 150, mpaint);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }

    public void initPaint() {
        rePaint = new Paint();
        rePaint.setAntiAlias(true);

        mpaint = new Paint();
        ColorFilter filter = new
                LightingColorFilter(0xffffff, 0x003000);
        mpaint.setColorFilter(filter);
        mpaint.setAntiAlias(true);


    }
}
