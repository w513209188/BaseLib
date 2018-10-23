package com.wb.baselib.appconfig;

import com.wb.baselib.app.AppUtils;

import java.util.List;

public class AppConfig {
    private int glideSize;
    private String glidePath;
    private int maxPage;
    private int httpCodeSuccess;
    private List<Integer> httpCodeOff;
    private String appCarshPath;
    private boolean isSendCarshLog;
    private boolean isCatshLog;
    private String rootPackAge;
    private String ddAccount;
    private String ddToken;
    private String flgs;

    public String getFlgs() {
        return flgs;
    }

    public String getDdToken() {
        return ddToken;
    }

    public String getDdAccount() {
        return ddAccount;
    }

    public String getRootPackAge() {
        return rootPackAge;
    }

    public boolean isSendCarshLog() {
        return isSendCarshLog;
    }

    public boolean isCatshLog() {
        return isCatshLog;
    }
    public AppConfig(Bulider bulider) {
        if(bulider.ddToken==null||bulider.ddToken.equals("")){
            bulider.ddToken="ac2fe3f63774fa2f6c7a82ff7dcbb5183da62420f8d5a62a6e1d069dd94392c4";
        }else {
            this.ddToken=bulider.ddToken;
        }
        if(bulider.ddAccount==null||bulider.ddAccount.equals("")){
            bulider.ddAccount="18210352611";
        }else {
            this.ddAccount=bulider.ddAccount;
        }
        if(bulider.rootPackAge==null||bulider.rootPackAge.equals("")){
            bulider.rootPackAge="com.wb.baselib";
        }else {
            this.rootPackAge=bulider.rootPackAge;
        }
        if(bulider.appCarshPath==null||bulider.appCarshPath.equals("")){
            bulider.appCarshPath="appCarshLog";
        }else {
            this.appCarshPath=bulider.appCarshPath;
        }
        if(bulider.glidePath==null||bulider.glidePath.equals("")){
            this.glidePath="basel_catch";
        }else {
            this.glidePath=bulider.glidePath;
        }
        if(bulider.glideSize==0){
            this.glideSize=150 * 1000 * 1000;
        }else {
            this.glideSize=bulider.glideSize;
        }
        if(bulider.httpCodeOff==null||bulider.httpCodeOff.size()==0){
            this.httpCodeOff.add(201);
        }else {
            this.httpCodeOff=bulider.httpCodeOff;
        }
        if(bulider.maxPage==0){
            this.maxPage=10;
        }else {
            this.maxPage=bulider.maxPage;
        }
        if(bulider.httpCodeSuccess==0){
            this.httpCodeSuccess=200;
        }else {
            this.httpCodeSuccess=bulider.httpCodeSuccess;
        }
        if(bulider.flgs==null||bulider.flgs.equals("")){
            this.flgs="1";
        }else {
            this.flgs=bulider.flgs;
        }
        this.isSendCarshLog=bulider.isSendCarshLog;
        this.isCatshLog=bulider.isCatshLog;
    }

    public int getGlideSize() {
        return glideSize;
    }

    public String getGlidePath() {
        return glidePath;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getHttpCodeSuccess() {
        return httpCodeSuccess;
    }

    public List<Integer> getHttpCodeOff() {
        return httpCodeOff;
    }

    public String getAppCarshPath() {
        return appCarshPath;
    }
    public static class Bulider{
        //图片缓存的大小
        private int glideSize;
        //图片缓存的地址
        private String glidePath;
        //每页显示最大的数目
        private int maxPage;
        //网络请求成功code码
        private int httpCodeSuccess;
        //将用户踢下线的code码
        private List<Integer> httpCodeOff;
        //日志奔溃后存储的位置
        private String appCarshPath;
        //是否错误日志发送至钉钉群组
        private boolean isSendCarshLog;
        //是否开启奔溃日志收集
        private boolean isCatshLog;
        //根包名
        private String rootPackAge;
        //钉钉账号
        private String ddAccount;
        //申请的钉钉群组token
        private String ddToken;
        private String flgs;

        public Bulider setFlgs(String flgs) {
            this.flgs = flgs;
            return this;
        }

        public Bulider setDdToken(String ddToken) {
            this.ddToken = ddToken;
            return this;
        }

        public Bulider setDdAccount(String ddAccount) {
            this.ddAccount = ddAccount;
            return this;
        }

        public Bulider setRootPackAge(String rootPackAge) {
            this.rootPackAge = rootPackAge;
            return this;
        }

        public Bulider setSendCarshLog(boolean sendCarshLog) {
            isSendCarshLog = sendCarshLog;
            return this;
        }

        public Bulider setCatshLog(boolean catshLog) {
            isCatshLog = catshLog;
            return this;
        }

        public Bulider setGlideSize(int glideSize) {
            this.glideSize = glideSize;
            return this;
        }

        public Bulider setGlidePath(String glidePath) {
            this.glidePath = glidePath;
            return this;
        }

        public Bulider setMaxPage(int maxPage) {
            this.maxPage = maxPage;
            return this;
        }

        public Bulider setHttpCodeSuccess(int httpCodeSuccess) {
            this.httpCodeSuccess = httpCodeSuccess;
            return this;
        }

        public Bulider setHttpCodeOff(List<Integer> httpCodeOff) {
            this.httpCodeOff = httpCodeOff;
            return this;
        }

        public Bulider setAppCarshPath(String appCarshPath) {
            this.appCarshPath = appCarshPath;
            return this;
        }
        public AppConfig bulider(){
            return new AppConfig(this);
        }
    }
}
