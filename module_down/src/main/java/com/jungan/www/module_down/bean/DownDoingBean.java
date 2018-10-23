package com.jungan.www.module_down.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.baijiayun.download.DownloadTask;

import java.util.ArrayList;
import java.util.List;

public class DownDoingBean implements Parcelable {
    private int group;
    private long videoCont;
    private List<DownloadTask> downloadTasks;

    public List<DownloadTask> getDownloadTasks() {
        return downloadTasks;
    }

    public void setDownloadTasks(List<DownloadTask> downloadTasks) {
        this.downloadTasks = downloadTasks;
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

    public DownDoingBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.group);
        dest.writeLong(this.videoCont);
        dest.writeList(this.downloadTasks);
    }

    protected DownDoingBean(Parcel in) {
        this.group = in.readInt();
        this.videoCont = in.readLong();
        this.downloadTasks = new ArrayList<DownloadTask>();
        in.readList(this.downloadTasks, DownloadTask.class.getClassLoader());
    }

    public static final Creator<DownDoingBean> CREATOR = new Creator<DownDoingBean>() {
        @Override
        public DownDoingBean createFromParcel(Parcel source) {
            return new DownDoingBean(source);
        }

        @Override
        public DownDoingBean[] newArray(int size) {
            return new DownDoingBean[size];
        }
    };
}
