package com.wb.rxbus.taskBean;

public class RxTaskBean {
    //1表示编辑本地刷新  2表示网络刷新 3表示刷新新增题库
    private int taskType;
    private String name;
    private String yq;
    private String classN;
    private String isFree;
    private String Time;
    private int postion;

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYq() {
        return yq;
    }

    public void setYq(String yq) {
        this.yq = yq;
    }

    public String getClassN() {
        return classN;
    }

    public void setClassN(String classN) {
        this.classN = classN;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public RxTaskBean(int taskType) {
        this.taskType = taskType;
    }

    public RxTaskBean(int taskType, String name, String yq, String classN, String isFree, String time) {
        this.taskType = taskType;
        this.name = name;
        this.yq = yq;
        this.classN = classN;
        this.isFree = isFree;
        Time = time;
    }
}
