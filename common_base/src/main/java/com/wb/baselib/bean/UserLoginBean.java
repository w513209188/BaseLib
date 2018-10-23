package com.wb.baselib.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户信息模板
 */
public class UserLoginBean implements Parcelable {
    private String uid;
    //用户账号
   private String userAcc;
   //密码
    private String userPwd;
    //昵称
    private String userNiceName;
    //身份标识
    private String userType;
    //头像
    private String userAval;
    //手机号码
    private String userPhone;
    //token
    private String userToken;
    //性别
    private String userSex;
    //生日
    private String userBri;
    //用户所属类型
    private String userClass;
    //用户扩展字段
    private String userExt;
    public UserLoginBean(String userAcc, String userNiceName, String userType, String userAval, String userToken) {
        this.userAcc = userAcc;
        this.userNiceName = userNiceName;
        this.userType = userType;
        this.userAval = userAval;
        this.userToken = userToken;
    }

    public UserLoginBean() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserNiceName() {
        return userNiceName;
    }

    public void setUserNiceName(String userNiceName) {
        this.userNiceName = userNiceName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserAval() {
        return userAval;
    }

    public void setUserAval(String userAval) {
        this.userAval = userAval;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserBri() {
        return userBri;
    }

    public void setUserBri(String userBri) {
        this.userBri = userBri;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getUserExt() {
        return userExt;
    }

    public void setUserExt(String userExt) {
        this.userExt = userExt;
    }

    @Override
    public String toString() {
        return "UserLoginBean{" +
                "uid='" + uid + '\'' +
                ", userAcc='" + userAcc + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userNiceName='" + userNiceName + '\'' +
                ", userType='" + userType + '\'' +
                ", userAval='" + userAval + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userToken='" + userToken + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBri='" + userBri + '\'' +
                ", userClass='" + userClass + '\'' +
                ", userExt='" + userExt + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.userAcc);
        dest.writeString(this.userPwd);
        dest.writeString(this.userNiceName);
        dest.writeString(this.userType);
        dest.writeString(this.userAval);
        dest.writeString(this.userPhone);
        dest.writeString(this.userToken);
        dest.writeString(this.userSex);
        dest.writeString(this.userBri);
        dest.writeString(this.userClass);
        dest.writeString(this.userExt);
    }

    protected UserLoginBean(Parcel in) {
        this.uid = in.readString();
        this.userAcc = in.readString();
        this.userPwd = in.readString();
        this.userNiceName = in.readString();
        this.userType = in.readString();
        this.userAval = in.readString();
        this.userPhone = in.readString();
        this.userToken = in.readString();
        this.userSex = in.readString();
        this.userBri = in.readString();
        this.userClass = in.readString();
        this.userExt = in.readString();
    }

    public static final Creator<UserLoginBean> CREATOR = new Creator<UserLoginBean>() {
        @Override
        public UserLoginBean createFromParcel(Parcel source) {
            return new UserLoginBean(source);
        }

        @Override
        public UserLoginBean[] newArray(int size) {
            return new UserLoginBean[size];
        }
    };
}
