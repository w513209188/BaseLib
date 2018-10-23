package com.jungan.www.module_down.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DownHaveBean implements Parcelable {
    private int group;
    private long videoCont;
    private String videoName;
    private String occName;
    private String courseName;
    private String seachName;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public long getVideoCont() {
        return videoCont;
    }

    public void setVideoCont(long videoCont) {
        this.videoCont = videoCont;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getOccName() {
        return occName;
    }

    public void setOccName(String occName) {
        this.occName = occName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSeachName() {
        return seachName;
    }

    public void setSeachName(String seachName) {
        this.seachName = seachName;
    }

    public DownHaveBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.group);
        dest.writeLong(this.videoCont);
        dest.writeString(this.videoName);
        dest.writeString(this.occName);
        dest.writeString(this.courseName);
        dest.writeString(this.seachName);
        dest.writeString(this.uid);
    }

    protected DownHaveBean(Parcel in) {
        this.group = in.readInt();
        this.videoCont = in.readLong();
        this.videoName = in.readString();
        this.occName = in.readString();
        this.courseName = in.readString();
        this.seachName = in.readString();
        this.uid = in.readString();
    }

    public static final Creator<DownHaveBean> CREATOR = new Creator<DownHaveBean>() {
        @Override
        public DownHaveBean createFromParcel(Parcel source) {
            return new DownHaveBean(source);
        }

        @Override
        public DownHaveBean[] newArray(int size) {
            return new DownHaveBean[size];
        }
    };
}
