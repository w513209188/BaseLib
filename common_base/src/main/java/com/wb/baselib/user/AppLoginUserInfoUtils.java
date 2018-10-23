package com.wb.baselib.user;

import com.wb.baselib.app.AppUtils;
import com.wb.baselib.appconfig.UserLoginInfoSp;
import com.wb.baselib.bean.UserLoginBean;
import com.wb.baselib.prase.GsonUtils;
import com.wb.baselib.utils.SharedPrefsUtil;

public class AppLoginUserInfoUtils {
    private static  AppLoginUserInfoUtils userInfoUtils;
    public static AppLoginUserInfoUtils getInstance(){
        if(userInfoUtils==null)
        {
            userInfoUtils=new AppLoginUserInfoUtils();
        }
        return userInfoUtils;
    }

    /**
     * 保存登录信息
     * @param userLoginBean
     * @return
     */
    public boolean saveLoginInfo(UserLoginBean userLoginBean){
        try {
            SharedPrefsUtil.putValue(AppUtils.getContext(), UserLoginInfoSp.SAVE_LOGIN_DATA,UserLoginInfoSp.SAVE_LOGIN_DATA, GsonUtils.newInstance().GsonToString(userLoginBean));
            SharedPrefsUtil.putValue(AppUtils.getContext(), UserLoginInfoSp.LAST_LOGIN_PHONE,UserLoginInfoSp.LAST_LOGIN_PHONE,userLoginBean.getUserAcc());
        return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获取保存的用户信息
     * @return
     */
    public UserLoginBean getUserLoginInfo(){
        String json=SharedPrefsUtil.getValue(AppUtils.getContext(),UserLoginInfoSp.SAVE_LOGIN_DATA,UserLoginInfoSp.SAVE_LOGIN_DATA,"");
           try {
               UserLoginBean userLoginBean=GsonUtils.newInstance().getBean(json,UserLoginBean.class);
               return userLoginBean;
           }catch (Exception e){
               return null;
           }
    }

    /**
     * 清除所有信息
     * @return
     */
    public boolean clearAllInfo(){
        try {
            SharedPrefsUtil.clearName(AppUtils.getContext(),UserLoginInfoSp.SAVE_LOGIN_DATA);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获取到最后一次登录的账号
     * @return
     */
    public String getLastAccount(){
        try {
            return SharedPrefsUtil.getValue(AppUtils.getContext(),UserLoginInfoSp.SAVE_LOGIN_DATA,UserLoginInfoSp.SAVE_LOGIN_DATA,"");
        }catch (Exception e){
            return "";
        }
    }
}
