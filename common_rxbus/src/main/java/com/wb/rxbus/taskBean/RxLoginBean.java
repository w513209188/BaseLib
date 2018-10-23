package com.wb.rxbus.taskBean;

import android.os.Parcel;
import android.os.Parcelable;

public class RxLoginBean   implements Parcelable {
    //1 表示强制下线
    private int LoginType;

    public RxLoginBean() {
    }

    public RxLoginBean(int loginType) {
        LoginType = loginType;
    }

    public int getLoginType() {
        return LoginType;
    }

    public void setLoginType(int loginType) {
        LoginType = loginType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.LoginType);
    }

    protected RxLoginBean(Parcel in) {
        this.LoginType = in.readInt();
    }

    public static final Parcelable.Creator<RxLoginBean> CREATOR = new Parcelable.Creator<RxLoginBean>() {
        @Override
        public RxLoginBean createFromParcel(Parcel source) {
            return new RxLoginBean(source);
        }

        @Override
        public RxLoginBean[] newArray(int size) {
            return new RxLoginBean[size];
        }
    };

    @Override
    public String toString() {
        return "RxLoginBean{" +
                "LoginType=" + LoginType +
                '}';
    }
}
