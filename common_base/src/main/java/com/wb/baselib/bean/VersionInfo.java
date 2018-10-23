package com.wb.baselib.bean;

public class VersionInfo {

    /**
     * version_code : 1
     * version_name : 1.2
     * is_force_update : 1
     * version_detail : 合适的教父垃圾袋是否是东方丽景
     */

    private String version_code;
    private String version_name;
    private String is_force_update;
    private String version_detail;
    private String apk_address;

    public String getApk_address() {
        return apk_address;
    }

    public void setApk_address(String apk_address) {
        this.apk_address = apk_address;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getIs_force_update() {
        return is_force_update;
    }

    public void setIs_force_update(String is_force_update) {
        this.is_force_update = is_force_update;
    }

    public String getVersion_detail() {
        return version_detail;
    }

    public void setVersion_detail(String version_detail) {
        this.version_detail = version_detail;
    }
}
