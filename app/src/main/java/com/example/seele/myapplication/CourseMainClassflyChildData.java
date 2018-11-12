package com.example.seele.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseMainClassflyChildData implements Parcelable {
    /**
     * id : 79
     * title : 物理
     * parent_id : 40
     */

    private String id;
    private String title;
    private String parent_id;

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
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.parent_id);
    }

    public CourseMainClassflyChildData() {
    }

    protected CourseMainClassflyChildData(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.parent_id = in.readString();
    }

    public static final Parcelable.Creator<CourseMainClassflyChildData> CREATOR = new Parcelable.Creator<CourseMainClassflyChildData>() {
        @Override
        public CourseMainClassflyChildData createFromParcel(Parcel source) {
            return new CourseMainClassflyChildData(source);
        }

        @Override
        public CourseMainClassflyChildData[] newArray(int size) {
            return new CourseMainClassflyChildData[size];
        }
    };
}
