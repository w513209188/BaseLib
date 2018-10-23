package com.wb.baselib.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AppCarshLogBean implements Parcelable {
    private String msgtype;
    private AppCarshLogTextBean text;
    private AppCarshLogAtBean at;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public AppCarshLogTextBean getText() {
        return text;
    }

    public void setText(AppCarshLogTextBean text) {
        this.text = text;
    }

    public AppCarshLogAtBean getAt() {
        return at;
    }

    public void setAt(AppCarshLogAtBean at) {
        this.at = at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msgtype);
        dest.writeParcelable(this.text, flags);
        dest.writeParcelable(this.at, flags);
    }

    public AppCarshLogBean() {
    }

    protected AppCarshLogBean(Parcel in) {
        this.msgtype = in.readString();
        this.text = in.readParcelable(AppCarshLogTextBean.class.getClassLoader());
        this.at = in.readParcelable(AppCarshLogAtBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<AppCarshLogBean> CREATOR = new Parcelable.Creator<AppCarshLogBean>() {
        @Override
        public AppCarshLogBean createFromParcel(Parcel source) {
            return new AppCarshLogBean(source);
        }

        @Override
        public AppCarshLogBean[] newArray(int size) {
            return new AppCarshLogBean[size];
        }
    };
}
