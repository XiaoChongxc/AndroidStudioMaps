package com.example.admin_xc.basemodule.base;

import android.app.Application;

import com.example.admin_xc.basemodule.util.Const;
import com.example.admin_xc.basemodule.util.SharePerferenceUtils;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.base
 * 日期   :   2017/6/6
 * 时间   ：  15:22
 * 描述   ：
 */

public class BaseApplication extends Application {

    private static BaseApplication mApplication;

    public static BaseApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }


    public String getSeesionId() {
        return SharePerferenceUtils.getIns(this).getString(Const.SEESSION_ID, "111111111111");
    }

    public void saveSeesionId(String sid) {
        SharePerferenceUtils.getIns(this).putString(Const.SEESSION_ID, sid);
    }
}
