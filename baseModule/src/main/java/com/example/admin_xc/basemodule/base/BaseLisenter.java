package com.example.admin_xc.basemodule.base;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.base
 * 日期   :   2017/6/1
 * 时间   ：  17:52
 * 描述   ：  通用的监听接口
 */

public interface BaseLisenter<T> {
    void onCompleted(int requestType);

    void onError(int requestType, int code, String e);

    void onNext(int requestType, T t);

}
