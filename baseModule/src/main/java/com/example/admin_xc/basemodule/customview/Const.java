package com.example.admin_xc.basemodule.customview;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.customview
 * 日期   :   2017/6/16
 * 时间   ：  17:47
 * 描述   ：
 */

public class Const {
    public static String hanlderAction(int action) {
        switch (action) {
            case 2:
                return "MOVE";
            case 1:
                return "UP";
            case 0:
                return "DOWN";
        }
        return "=====";
    }
}
