package com.wb.baselib.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AppCarshLogAtBean implements Parcelable {
    private List<String> atMobiles;
    private boolean isAtAll;

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public boolean isAtAll() {
        return isAtAll;
    }

    public void setAtAll(boolean atAll) {
        isAtAll = atAll;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.atMobiles);
        dest.writeByte(this.isAtAll ? (byte) 1 : (byte) 0);
    }

    public AppCarshLogAtBean() {
    }

    protected AppCarshLogAtBean(Parcel in) {
        this.atMobiles = in.createStringArrayList();
        this.isAtAll = in.readByte() != 0;
    }

    public static final Creator<AppCarshLogAtBean> CREATOR = new Creator<AppCarshLogAtBean>() {
        @Override
        public AppCarshLogAtBean createFromParcel(Parcel source) {
            return new AppCarshLogAtBean(source);
        }

        @Override
        public AppCarshLogAtBean[] newArray(int size) {
            return new AppCarshLogAtBean[size];
        }
    };
}
