package com.dleyy.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dleyy on 2017/11/24.
 */
public class BingBean {

    private int ret_code;
    private ArrayList<ListBean> list;

    protected BingBean(Parcel in) {
        ret_code = in.readInt();
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public ArrayList<ListBean> getList() {
        return list;
    }

    public void setList(ArrayList<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        @SerializedName("1920*1080")
        private String _$_19201080197; // FIXME check this code

        @SerializedName("1920x1080")
        private String _$_19202;

        @SerializedName("720x1280")
        private String _$720x1280;

        @SerializedName("800x600")
        private String _$800x600;

        private String pic;

        @SerializedName("640x480")
        private String _$640x480;

        @SerializedName("240x320")
        private String _$240x320;

        private String country;

        private String city;

        private String content;

        private String title;

        @SerializedName("320x240")
        private String _$320x240;

        private String subtitle;

        @SerializedName("1024x768")
        private String _$1024x768;

        @SerializedName("400x240")
        private String _$400x240;

        private String day;

        @SerializedName("1280x768")
        private String _$1280x768;

        @SerializedName("480x800")
        private String _$480x800;

        @SerializedName("800x480")
        private String _$800x480;

        @SerializedName("1366x768")
        private String _$1366x768;

        protected ListBean(Parcel in) {
            _$_19201080197 = in.readString();
            _$_19202 = in.readString();
            _$720x1280 = in.readString();
            _$800x600 = in.readString();
            pic = in.readString();
            _$640x480 = in.readString();
            _$240x320 = in.readString();
            country = in.readString();
            city = in.readString();
            content = in.readString();
            title = in.readString();
            _$320x240 = in.readString();
            subtitle = in.readString();
            _$1024x768 = in.readString();
            _$400x240 = in.readString();
            day = in.readString();
            _$1280x768 = in.readString();
            _$480x800 = in.readString();
            _$800x480 = in.readString();
            _$1366x768 = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel in) {
                return new ListBean(in);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };

        public String get_$_19201080197() {
            return _$_19201080197;
        }

        public void set_$_19201080197(String _$_19201080197) {
            this._$_19201080197 = _$_19201080197;
        }

        public String get_$720x1280() {
            return _$720x1280;
        }

        public void set_$720x1280(String _$720x1280) {
            this._$720x1280 = _$720x1280;
        }

        public String get_$800x600() {
            return _$800x600;
        }

        public void set_$800x600(String _$800x600) {
            this._$800x600 = _$800x600;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String get_$640x480() {
            return _$640x480;
        }

        public void set_$640x480(String _$640x480) {
            this._$640x480 = _$640x480;
        }

        public String get_$240x320() {
            return _$240x320;
        }

        public void set_$240x320(String _$240x320) {
            this._$240x320 = _$240x320;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String get_$320x240() {
            return _$320x240;
        }

        public void set_$320x240(String _$320x240) {
            this._$320x240 = _$320x240;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String get_$1024x768() {
            return _$1024x768;
        }

        public void set_$1024x768(String _$1024x768) {
            this._$1024x768 = _$1024x768;
        }

        public String get_$400x240() {
            return _$400x240;
        }

        public void set_$400x240(String _$400x240) {
            this._$400x240 = _$400x240;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String get_$1280x768() {
            return _$1280x768;
        }

        public void set_$1280x768(String _$1280x768) {
            this._$1280x768 = _$1280x768;
        }

        public String get_$480x800() {
            return _$480x800;
        }

        public void set_$480x800(String _$480x800) {
            this._$480x800 = _$480x800;
        }

        public String get_$800x480() {
            return _$800x480;
        }

        public void set_$800x480(String _$800x480) {
            this._$800x480 = _$800x480;
        }

        public String get_$1366x768() {
            return _$1366x768;
        }

        public void set_$1366x768(String _$1366x768) {
            this._$1366x768 = _$1366x768;
        }

        public String get_$_19202() {
            return _$_19202;
        }

        public void set_$_19202(String _$_19202) {
            this._$_19202 = _$_19202;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(_$_19201080197);
            dest.writeString(_$_19202);
            dest.writeString(_$720x1280);
            dest.writeString(_$800x600);
            dest.writeString(pic);
            dest.writeString(_$640x480);
            dest.writeString(_$240x320);
            dest.writeString(country);
            dest.writeString(city);
            dest.writeString(content);
            dest.writeString(title);
            dest.writeString(_$320x240);
            dest.writeString(subtitle);
            dest.writeString(_$1024x768);
            dest.writeString(_$400x240);
            dest.writeString(day);
            dest.writeString(_$1280x768);
            dest.writeString(_$480x800);
            dest.writeString(_$800x480);
            dest.writeString(_$1366x768);
        }
    }
}
