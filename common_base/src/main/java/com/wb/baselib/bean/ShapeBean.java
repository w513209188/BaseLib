package com.wb.baselib.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.hss01248.dialog.bottomsheet.BottomSheetBean;
import com.wb.baselib.appconfig.ShapeTypeConfig;

import java.util.Set;

public class ShapeBean extends BottomSheetBean implements Parcelable {
    private ShapeTypeConfig type;
    private String title;
    private String msg;
    private String openUrl;
    private String imageUrl;
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ShapeBean(int icon, String text, ShapeTypeConfig type, String title, String msg, String openUrl, String imageUrl) {
        super(icon, text);
        this.type = type;
        this.title = title;
        this.msg = msg;
        this.openUrl = openUrl;
        this.imageUrl = imageUrl;
    }
    //    public ShapeBean() {
//    }

    public ShapeTypeConfig getType() {
        return type;
    }

    public void setType(ShapeTypeConfig type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    @Override
    public String toString() {
        return "ShapeBean{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", msg='" + msg + '\'' +
                ", openUrl='" + openUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", icon=" + icon +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.title);
        dest.writeString(this.msg);
        dest.writeString(this.openUrl);
        dest.writeString(this.imageUrl);
    }

    protected ShapeBean(Parcel in) {
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : ShapeTypeConfig.values()[tmpType];
        this.title = in.readString();
        this.msg = in.readString();
        this.openUrl = in.readString();
        this.imageUrl = in.readString();
    }

    public static final Creator<ShapeBean> CREATOR = new Creator<ShapeBean>() {
        @Override
        public ShapeBean createFromParcel(Parcel source) {
            return new ShapeBean(source);
        }

        @Override
        public ShapeBean[] newArray(int size) {
            return new ShapeBean[size];
        }
    };
}
