package com.hangzhou.xc.test.customview.util;

import android.content.Context;
import android.widget.Toast;

/**
 * User:    Xiaoc
 * 项目名:  WZB3.0
 * Package: baizhuan.hangzhou.com.wzb30.util
 * Date:    2016-06-27
 * Time:    11:40
 * 类描述：Toast 封装类
 */
public class T {

    public static void showShort(Context ctx, String text) {
        showToast(ctx, text, Toast.LENGTH_SHORT);
    }

    public static void showLong(Context ctx, String text) {
        showToast(ctx, text, Toast.LENGTH_LONG);
    }


    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void showToast(Context context, String s, int time) {
        if (toast == null) {
            toast = Toast.makeText(context, s, time);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > time) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}