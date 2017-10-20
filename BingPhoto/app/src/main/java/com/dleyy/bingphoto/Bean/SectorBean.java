package com.dleyy.bingphoto.Bean;

/**
 * Created by dleyy on 2017/10/19.
 */
public class SectorBean {

    /**
     * 区域名
     */
    private String sectorName;

    /**
     * 百分比
     */
    private float percentage;

    /**
     * 扇形值
     */
    private String value;

    /**
     * 颜色
     */
    private int color;

    /**
     * 扫过的角度
     */
    private float sweepAngle;

    /**
     * 开始的角度
     */
    private float startAngle;


    public SectorBean(String sectorName, float percentage) {
        this.sectorName = sectorName;
        this.percentage = percentage;
    }

    public SectorBean(String sectorName, float percentage, String value, int color) {
        this.sectorName = sectorName;
        this.percentage = percentage;
        this.value = value;
        this.color = color;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getSweepAngle() {
        return sweepAngle;
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    public float getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }
}
