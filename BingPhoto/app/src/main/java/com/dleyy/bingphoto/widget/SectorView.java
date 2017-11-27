package com.dleyy.bingphoto.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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
    private float r = 0f;

    /**
     * 画布，用于保存绘制好的状态
     */
    private Canvas canvas;

    /**
     * SectorView圆心的坐标
     */
    private double[] circleCenter = {-1, -1};
    /**
     * SectorView的左边距离与顶部距离。
     */
    private double viewLeft, viewTop;

    private OnClickListener listener;


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

        //获取到View左距离屏幕左的距离，同理 上
        viewLeft = getLeft();
        viewTop = getTop();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mDatas) {
            return;
        }
        //将画布坐标原点移动到中心位置
        canvas.translate(width / 2, height / 2);
        circleCenter[0] = viewLeft + width / 2;
        circleCenter[1] = viewTop + height / 2;
        //计算半径

        r = (float) (Math.min(width, height) / 2 * 0.8);

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
            canvas.save();
            canvas.translate(bean.getPointX(), bean.getPointY());
            mpaint.setColor(bean.getColor());
            canvas.drawArc(rectF, bean.getStartAngle(), bean.getSweepAngle(), true, mpaint);
            canvas.restore();
        }
        this.canvas = canvas;
        canvas.save();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                double x = event.getX();
                double y = event.getY();
                if (isContainerInView(x, y)) {
                    showAnimation(x, y, mDatas);
                } else {
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                canvas.restore();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 动画展示
     * 1、计算出是否在某块区域内。
     * 2、执行动画
     *
     * @param x      点击点的x坐标
     * @param y      点击点的y坐标
     * @param mDatas 数据集合
     */
    private void showAnimation(double x, double y, List<SectorBean> mDatas) {
        double degree = Math.atan((y - circleCenter[1]) / (x - circleCenter[0])) * 180 / Math.PI;
        //获取基于SectorView原点的坐标值
        degree = getReallyDegree(degree, x - circleCenter[0], y - circleCenter[1]);
        float startAngle = 0f;
        for (SectorBean sectorBean : mDatas) {
            startAngle += sectorBean.getSweepAngle() / 2;
            if (degree <= sectorBean.getSweepAngle() + sectorBean.getStartAngle()
                    && degree >= sectorBean.getStartAngle()) {
//                Log.e(TAG, "There should show animation" + sectorBean.getSectorName());
                float moveX = (float) (15f *
                        Math.cos(sectorBean.getCenterAngle() / 180 * Math.PI));
                float moveY = (float) (15f *
                        Math.sin(sectorBean.getCenterAngle() / 180 * Math.PI));

                sectorBean.setPointX(moveX);
                sectorBean.setPointY(moveY);
                invalidate();
                break;


            }
        }
    }

    /**
     * 放大扇形
     *
     * @param bean
     */
    private void drawCycle(SectorBean bean) {
        if (null == mpaint) {
            initPaint();
        }
        mpaint.setColor(bean.getColor());
        canvas.translate(width / 2, height / 2);
        //计算半径
        r = (float) (Math.min(width, height) / 2);
        //绘制饼状图区域
        RectF rectF = new RectF(-r, -r, r, r);
        canvas.drawArc(rectF, bean.getStartAngle(), bean.getSweepAngle(), true, mpaint);
    }

    /**
     * 根据坐标点计算出具体的角度值（0-360），因为以前是-Π-Π；
     *
     * @param degree -Π-Π的角度值
     * @param x      点的x坐标
     * @param y      点的y坐标
     * @return 0-360的角度值
     */
    private double getReallyDegree(double degree, double x, double y) {
        double reallyDegree = 0d;
        if (x < 0) {
            reallyDegree = degree + 180;
            return reallyDegree;
        } else if (y < 0) {
            reallyDegree = 360 + degree;
            return reallyDegree;
        } else {
            return degree;
        }
    }

    //判断手势是否在View内部
    private boolean isContainerInView(double x, double y) {
        double length = Math.sqrt((x - circleCenter[0]) * (x - circleCenter[0])
                + (y - circleCenter[1]) * (y - circleCenter[1]));
        if (length < r) {
            return true;
        } else {
            return false;
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
            bean.setCenterAngle(bean.getStartAngle() + bean.getSweepAngle() / 2);
        }
    }

    private void drawSectorView() {

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
