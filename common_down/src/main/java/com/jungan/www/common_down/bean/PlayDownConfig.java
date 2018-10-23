package com.jungan.www.common_down.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 视频下载模板
 */
public class PlayDownConfig {
    //保存的文件名称
    private String fileName;
    //视频Id
    private long videoId;
    //视频Token
    private String token;
    //下载的视频是否加密  0 不加密，1加密
    private int encryptType;
    //用户id
    private long uId;
    //职业
    private String occName;
    //课程名称
    private String courerName;
    //章名称
    private String sectionName;
    private String extraInfo;
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public String getFileName() {
        return fileName;
    }

    public long getVideoId() {
        return videoId;
    }

    public String getToken() {
        return token;
    }

    public int getEncryptType() {
        return encryptType;
    }

    public long getuId() {
        return uId;
    }

    public String getOccName() {
        return occName;
    }

    public String getCourerName() {
        return courerName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getExtraInfo() {
        return extraInfo;
    }
    public static class Bulider{
        //保存的文件名称
        private String fileName;
        //视频Id
        private long videoId;
        //视频Token
        private String token;
        //下载的视频是否加密  0 不加密，1加密
        private int encryptType;
        //用户id
        private long uId;
        //职业
        private String occName;
        //课程名称
        private String courerName;
        //章名称
        private String sectionName;
        private String extraInfo;
        private String sessionId;

        public Bulider setSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Bulider setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Bulider setVideoId(long videoId) {
            this.videoId = videoId;
            return this;
        }

        public Bulider setToken(String token) {
            this.token = token;
            return this;
        }

        public Bulider setEncryptType(int encryptType) {
            this.encryptType = encryptType;
            return this;
        }

        public Bulider setuId(long uId) {
            this.uId = uId;
            return this;
        }

        public Bulider setOccName(String occName) {
            if(occName==null||occName.equals(""))
                this.occName="";
            this.occName = occName;
            return this;
        }

        public Bulider setCourerName(String courerName) {
            if(courerName==null||courerName.equals(""))
                this.courerName="";
            this.courerName = courerName;
            return this;
        }

        public Bulider setSectionName(String sectionName) {
            if(sectionName==null||sectionName.equals(""))
                this.sectionName="";
            this.sectionName = sectionName;
            return this;
        }

        public Bulider setExtraInfo(String extraInfo) {
            this.extraInfo = extraInfo;
            return this;
        }
        public PlayDownConfig builder(){
            PlayDownConfig config=new PlayDownConfig();
            //必选参数
            config.fileName=this.fileName;
            config.token=this.token;
            config.uId=this.uId;
            config.videoId=this.videoId;
            //可选参数
            config.courerName=this.courerName;
            config.encryptType=this.encryptType;
            config.extraInfo=this.extraInfo;
            config.sectionName=this.sectionName;
            config.occName=this.occName;
            config.sessionId=this.sessionId;
            return config;
        }
    }

}
