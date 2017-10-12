package com.dleyy.bingphoto.Bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dleyy on 2017/9/30.
 */
public class PhonePixel implements Serializable {
    private int heightPixels;
    private int widthPixels;

    public PhonePixel(int heightPixels, int widthPixels) {
        this.heightPixels = heightPixels;
        this.widthPixels = widthPixels;
    }

    public int getHeightPixels() {
        return heightPixels;
    }

    public void setHeightPixels(int heightPixels) {
        this.heightPixels = heightPixels;
    }

    public int getWidthPixels() {
        return widthPixels;
    }

    public void setWidthPixels(int widthPixels) {
        this.widthPixels = widthPixels;
    }

    public ArrayList<String> getPhonePixelList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(widthPixels + "x" + heightPixels);
        list.add(widthPixels + "*" + heightPixels);
        list.add(heightPixels + "x" + widthPixels);
        list.add(heightPixels + "*" + widthPixels);
        return list;
    }
}
