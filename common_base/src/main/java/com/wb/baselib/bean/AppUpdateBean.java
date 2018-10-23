package com.wb.baselib.bean;

public class AppUpdateBean {
    private String status;
    private String msg;
    private AppUpdateData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AppUpdateData getData() {
        return data;
    }

    public void setData(AppUpdateData data) {
        this.data = data;
    }

    public class AppUpdateData{
        private VersionInfo version_info;

        public VersionInfo getVersion_info() {
            return version_info;
        }

        public void setVersion_info(VersionInfo version_info) {
            this.version_info = version_info;
        }
    }
}
