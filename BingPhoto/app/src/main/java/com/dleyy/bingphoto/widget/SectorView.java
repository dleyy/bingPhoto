package com.dleyy.bingphoto.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.dleyy.bingphoto.Bean.SectorBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dleyy on 2017/10/19.
 */
public class SectorView extends View {

    private static final String TAG = "SectorView";

    private final int ALL_DEGREE = 360;
    private List<SectorBean> mDatas = new ArrayList<>();
    private Paint mpaint;
    private int width, height;

    public SectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SectorView(Context context, AttributeSet attrs, List<SectorBean> mDatas) {
        super(context, attrs);
        this.mDatas = mDatas;
        calculateAngle(mDatas);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mDatas) {
            return;
        }
        //将画布坐标原点移动到中心位置
        canvas.translate(width / 2, height / 2);
        //计算半径
        float r = (float) (Math.min(width, height) / 2 * 0.8);
        //绘制饼状图区域
        RectF rectF = new RectF(-r, -r, r, r);

        //这样写的原因是：在重新setData之后，触发的是onDraw方法。
        //其实可以把这个方法放在onMeasure、onSizeChanged、onLayout中，都可以，因为是这样执行的，但放在onDraw
        //里面就是为了单一职责，绘制的时候计算一下。。（先这样理解）
        if (null == mpaint) {
            initPaint();
            calculateAngle(getDatas());
        }

        for (SectorBean bean : mDatas) {
            Log.e("dddd", "onDraw: " + bean.getColor() + " " + Color.RED
                    + " " + Color.GREEN + " " + Color.WHITE + " " + Color.BLUE);
            mpaint.setColor(bean.getColor());
            canvas.drawArc(rectF, bean.getStartAngle(), bean.getSweepAngle(), true, mpaint);
        }
    }

    /**
     * 计算并设置角度
     * 根据百分比来计算所占的角度，同时更新开始的角度
     * 由于坐标是 下右为正，所以绘制的时候是从
     * 右下->左下->左上->右上
     * 开始的。
     *
     * @param mDatas
     */
    private void calculateAngle(List<SectorBean> mDatas) {
        int nowAngle = 0;
        if (null == mDatas) {
            return;
        }
        for (int i = 0; i < mDatas.size(); i++) {
            SectorBean bean = mDatas.get(i);
            bean.setStartAngle(nowAngle);
            bean.setSweepAngle(ALL_DEGREE * bean.getPercentage());
            nowAngle += bean.getSweepAngle();

        }
    }

    /**
     * 初始化画笔的颜色、填充方式以及抗锯齿
     */
    private void initPaint() {
        if (null == mpaint) {
            mpaint = new Paint();
        }
        mpaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mpaint.setAntiAlias(true);

    }

    public void setDatas(List<SectorBean> mDatas) {
        this.mDatas = mDatas;
    }

    public List<SectorBean> getDatas() {
        return mDatas;
    }
}
