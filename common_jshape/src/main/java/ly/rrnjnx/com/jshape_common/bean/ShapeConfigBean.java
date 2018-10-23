package ly.rrnjnx.com.jshape_common.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ShapeConfigBean implements Parcelable {
    private int ShapeConfig;
    private String appId;
    private String addKey;
    private String redirectUrl;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public int getShapeConfig() {
        return ShapeConfig;
    }

    public void setShapeConfig(int shapeConfig) {
        ShapeConfig = shapeConfig;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAddKey() {
        return addKey;
    }

    public void setAddKey(String addKey) {
        this.addKey = addKey;
    }

    public ShapeConfigBean(int shapeConfig, String appId, String addKey) {
        ShapeConfig = shapeConfig;
        this.appId = appId;
        this.addKey = addKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ShapeConfig);
        dest.writeString(this.appId);
        dest.writeString(this.addKey);
        dest.writeString(this.redirectUrl);
    }

    protected ShapeConfigBean(Parcel in) {
        this.ShapeConfig = in.readInt();
        this.appId = in.readString();
        this.addKey = in.readString();
        this.redirectUrl = in.readString();
    }

    public static final Creator<ShapeConfigBean> CREATOR = new Creator<ShapeConfigBean>() {
        @Override
        public ShapeConfigBean createFromParcel(Parcel source) {
            return new ShapeConfigBean(source);
        }

        @Override
        public ShapeConfigBean[] newArray(int size) {
            return new ShapeConfigBean[size];
        }
    };
}
