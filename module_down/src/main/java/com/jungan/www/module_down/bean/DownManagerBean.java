package com.jungan.www.module_down.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DownManagerBean implements Parcelable {
   private DownDoingBean downDoingBean;
   private List<DownHaveBean> downHaveBeans;
    private boolean isDoing;
    private boolean isHave;

    public boolean isDoing() {
        return isDoing;
    }

    public void setDoing(boolean doing) {
        isDoing = doing;
    }

    public boolean isHave() {
        return isHave;
    }

    public void setHave(boolean have) {
        isHave = have;
    }

    public DownDoingBean getDownDoingBean() {
        return downDoingBean;
    }

    public void setDownDoingBean(DownDoingBean downDoingBean) {
        this.downDoingBean = downDoingBean;
    }

    public List<DownHaveBean> getDownHaveBeans() {
        return downHaveBeans;
    }

    public void setDownHaveBeans(List<DownHaveBean> downHaveBeans) {
        this.downHaveBeans = downHaveBeans;
    }

    public DownManagerBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.downDoingBean, flags);
        dest.writeTypedList(this.downHaveBeans);
        dest.writeByte(this.isDoing ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isHave ? (byte) 1 : (byte) 0);
    }

    protected DownManagerBean(Parcel in) {
        this.downDoingBean = in.readParcelable(DownDoingBean.class.getClassLoader());
        this.downHaveBeans = in.createTypedArrayList(DownHaveBean.CREATOR);
        this.isDoing = in.readByte() != 0;
        this.isHave = in.readByte() != 0;
    }

    public static final Creator<DownManagerBean> CREATOR = new Creator<DownManagerBean>() {
        @Override
        public DownManagerBean createFromParcel(Parcel source) {
            return new DownManagerBean(source);
        }

        @Override
        public DownManagerBean[] newArray(int size) {
            return new DownManagerBean[size];
        }
    };
}
