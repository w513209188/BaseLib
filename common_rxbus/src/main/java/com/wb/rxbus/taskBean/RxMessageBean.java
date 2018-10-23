package com.wb.rxbus.taskBean;

import android.os.Parcel;
import android.os.Parcelable;

public class RxMessageBean implements Parcelable {
    //1 表示Registration Id推送 2表示 自定义消息推送 3表示通知消息 4表示打开了通知 5表示用户收到到RICH PUSH CALLBACK 6表示 7表示分享
    private int messageType;
    private String message;
    private String extras;
    public RxMessageBean(int messageType, String message, String extras) {
        this.messageType = messageType;
        this.message = message;
        this.extras = extras;
    }

    public RxMessageBean() {
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.messageType);
        dest.writeString(this.message);
        dest.writeString(this.extras);
    }

    protected RxMessageBean(Parcel in) {
        this.messageType = in.readInt();
        this.message = in.readString();
        this.extras = in.readString();
    }

    public static final Parcelable.Creator<RxMessageBean> CREATOR = new Parcelable.Creator<RxMessageBean>() {
        @Override
        public RxMessageBean createFromParcel(Parcel source) {
            return new RxMessageBean(source);
        }

        @Override
        public RxMessageBean[] newArray(int size) {
            return new RxMessageBean[size];
        }
    };
}
