package com.example.admin_xc.basemodule.util;
import com.orhanobut.logger.Logger;

/**
 * author :   Xchong
 * 项目名：   VPdai
 * 包名   :   com.hangzhou.vpdai.util
 * 日期   :   2017/3/6
 * 时间   ：  11:38
 * 描述   ：  debug类
 */

public class L {


    public static void d(String msg) {
       
            Logger.d(msg);
    }

    public static void e(String msg) {
       
            Logger.e(msg);
    }

    public static void i(String msg) {
       
            Logger.i(msg);
    }

    public static void v(String msg) {
       
            Logger.v(msg);
    }

    public static void w(String msg) {
       
            Logger.w(msg);
    }


    public static void d(String tag, String msg) {
        {
            Logger.init(tag);
            Logger.d(msg);
        }
    }

    public static void e(String tag, String msg) {
        {
            Logger.init(tag);
            Logger.e(msg);
        }

    }

    public static void i(String tag, String msg) {
        {
            Logger.init(tag);
            Logger.i(msg);
        }
    }

    public static void v(String tag, String msg) {
        {
            Logger.init(tag);
            Logger.v(msg);
        }
    }

    public static void w(String tag, String msg) {
        {
            Logger.init(tag);
            Logger.w(msg);
        }
    }

}
