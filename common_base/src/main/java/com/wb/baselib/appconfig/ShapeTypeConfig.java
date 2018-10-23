package com.wb.baselib.appconfig;
public enum ShapeTypeConfig {
    QQ(0),
    QQZONE(1),
    WX(2),
    WXP(3),
    SINA(4);
    private int type;
    private ShapeTypeConfig(int var3) {
        this.type = var3;
    }
    public static ShapeTypeConfig from(int var0) {
        switch(var0) {
            case 0:
                return QQ;
            case 1:
                return QQZONE;
            case 2:
                return WX;
            case 3:
                return WXP;
            case 4:
                return SINA;
            default:
                return QQ;
        }
    }

    public int getType() {
        return this.type;
    }
}
