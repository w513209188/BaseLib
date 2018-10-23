package com.jungan.www.common_down.config;

import android.app.Activity;

public  class BjyPlayDownConfig {
    private Activity mActivity;
    private String FilePath;
    private long BjyId;
    private static BjyPlayDownConfig bjyPlayDownConfig;
    public static BjyPlayDownConfig newInstacne(){
        synchronized (BjyPlayDownConfig.class){
            if(bjyPlayDownConfig==null){
                bjyPlayDownConfig=new BjyPlayDownConfig();
            }
        }
        return bjyPlayDownConfig;
    }

    public Activity getmActivity() {
        return mActivity;
    }

    public String getFilePath() {
        return FilePath;
    }

    public long getBjyId() {
        return BjyId;
    }
    public static class Builder{
        private String FilePath;
        private long BjyId;
        private Activity mActivity;

        public Builder with(Activity mActivity) {
            this.mActivity = mActivity;
            return this;
        }

        public Builder setFilePath(String filePath) {
            FilePath = filePath;
            return this;
        }

        public Builder setBjyId(long bjyId) {
            BjyId = bjyId;
            return this;
        }
        public BjyPlayDownConfig bulider(){
            BjyPlayDownConfig bjyPlayDownConfig=BjyPlayDownConfig.newInstacne();
            bjyPlayDownConfig.BjyId=this.BjyId;
            bjyPlayDownConfig.FilePath=this.FilePath;
            bjyPlayDownConfig.mActivity=this.mActivity;
            return bjyPlayDownConfig;
        }
    }
}
