package com.example.seele.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CourseMainClassflyBean implements Parcelable {
    private List<CourseMainClassflyData> list;

    public List<CourseMainClassflyData> getList() {
        return list;
    }

    public void setList(List<CourseMainClassflyData> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
    }

    public CourseMainClassflyBean() {
    }

    protected CourseMainClassflyBean(Parcel in) {
        this.list = in.createTypedArrayList(CourseMainClassflyData.CREATOR);
    }

    public static final Parcelable.Creator<CourseMainClassflyBean> CREATOR = new Parcelable.Creator<CourseMainClassflyBean>() {
        @Override
        public CourseMainClassflyBean createFromParcel(Parcel source) {
            return new CourseMainClassflyBean(source);
        }

        @Override
        public CourseMainClassflyBean[] newArray(int size) {
            return new CourseMainClassflyBean[size];
        }
    };
}
