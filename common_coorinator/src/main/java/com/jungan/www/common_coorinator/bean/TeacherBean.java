package com.jungan.www.common_coorinator.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TeacherBean implements Parcelable {

    /**
     * teacher_id : 38
     * teacher_name : 33
     * avatar : http://59.110.125.60/20180714/4db4c8027049765720909c0f2ff8572b.jpg
     */

    private int teacher_id;
    private String teacher_name;
    private String avatar;
    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.teacher_id);
        dest.writeString(this.teacher_name);
        dest.writeString(this.avatar);
    }

    public TeacherBean() {
    }

    protected TeacherBean(Parcel in) {
        this.teacher_id = in.readInt();
        this.teacher_name = in.readString();
        this.avatar = in.readString();
    }

    public static final Parcelable.Creator<TeacherBean> CREATOR = new Parcelable.Creator<TeacherBean>() {
        @Override
        public TeacherBean createFromParcel(Parcel source) {
            return new TeacherBean(source);
        }

        @Override
        public TeacherBean[] newArray(int size) {
            return new TeacherBean[size];
        }
    };
}
