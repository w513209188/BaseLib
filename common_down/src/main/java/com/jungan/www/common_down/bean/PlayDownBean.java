package com.jungan.www.common_down.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 视频下载模板
 */
public class PlayDownBean implements Parcelable {
    //保存的文件名称
    private String fileName;
    //视频Id
    private long videoId;
    //视频Token
    private String token;
    //下载的视频是否加密  0 不加密，1加密
    private int encryptType;
    //用户id
    private long uId;
    //职业
    private String occName;
    //课程名称
    private String courerName;
    //章名称
    private String sectionName;
    private String extraInfo;

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(int encryptType) {
        this.encryptType = encryptType;
    }

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public String getOccName() {
        return occName;
    }

    public void setOccName(String occName) {
        this.occName = occName;
    }

    public String getCourerName() {
        return courerName;
    }

    public void setCourerName(String courerName) {
        this.courerName = courerName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public PlayDownBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fileName);
        dest.writeLong(this.videoId);
        dest.writeString(this.token);
        dest.writeInt(this.encryptType);
        dest.writeLong(this.uId);
        dest.writeString(this.occName);
        dest.writeString(this.courerName);
        dest.writeString(this.sectionName);
        dest.writeString(this.extraInfo);
    }

    protected PlayDownBean(Parcel in) {
        this.fileName = in.readString();
        this.videoId = in.readLong();
        this.token = in.readString();
        this.encryptType = in.readInt();
        this.uId = in.readLong();
        this.occName = in.readString();
        this.courerName = in.readString();
        this.sectionName = in.readString();
        this.extraInfo = in.readString();
    }

    public static final Creator<PlayDownBean> CREATOR = new Creator<PlayDownBean>() {
        @Override
        public PlayDownBean createFromParcel(Parcel source) {
            return new PlayDownBean(source);
        }

        @Override
        public PlayDownBean[] newArray(int size) {
            return new PlayDownBean[size];
        }
    };
}
