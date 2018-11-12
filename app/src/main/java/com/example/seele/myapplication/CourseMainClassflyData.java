package com.example.seele.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CourseMainClassflyData implements Parcelable{
    private List<CourseMainClassflyChildData> child;
    private String  id;
    private String  title;
    private String  parent_id;

    public List<CourseMainClassflyChildData> getChild() {
        return child;
    }

    public void setChild(List<CourseMainClassflyChildData> child) {
        this.child = child;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.child);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.parent_id);
    }

    public CourseMainClassflyData() {
    }

    protected CourseMainClassflyData(Parcel in) {
        this.child = in.createTypedArrayList(CourseMainClassflyChildData.CREATOR);
        this.id = in.readString();
        this.title = in.readString();
        this.parent_id = in.readString();
    }

    public static final Creator<CourseMainClassflyData> CREATOR = new Creator<CourseMainClassflyData>() {
        @Override
        public CourseMainClassflyData createFromParcel(Parcel source) {
            return new CourseMainClassflyData(source);
        }

        @Override
        public CourseMainClassflyData[] newArray(int size) {
            return new CourseMainClassflyData[size];
        }
    };
}
