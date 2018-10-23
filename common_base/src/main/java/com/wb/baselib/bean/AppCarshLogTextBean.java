package com.wb.baselib.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AppCarshLogTextBean implements Parcelable {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
    }

    public AppCarshLogTextBean() {
    }

    protected AppCarshLogTextBean(Parcel in) {
        this.content = in.readString();
    }

    public static final Creator<AppCarshLogTextBean> CREATOR = new Creator<AppCarshLogTextBean>() {
        @Override
        public AppCarshLogTextBean createFromParcel(Parcel source) {
            return new AppCarshLogTextBean(source);
        }

        @Override
        public AppCarshLogTextBean[] newArray(int size) {
            return new AppCarshLogTextBean[size];
        }
    };
}
