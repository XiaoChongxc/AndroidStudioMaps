package com.example.admin_xc.basemodule.base;

import com.example.admin_xc.basemodule.entry.BaseResult;
import com.example.admin_xc.basemodule.util.Const;

import rx.Observer;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.base
 * 日期   :   2017/6/5
 * 时间   ：  14:53
 * 描述   ：
 */

public class BaseSubscriber implements Observer<BaseResult> {
    /**
     * 请求的结果的监听器
     */
    private BaseLisenter lisenter;
    /**
     * 请求的类型，用于区分一个页面的多个请求
     */
    private int requestType = 0;

    public BaseSubscriber(int requestType, BaseLisenter lisenter) {
        this.lisenter = lisenter;
        this.requestType = requestType;
    }

    public BaseSubscriber(BaseLisenter lisenter) {
        this.lisenter = lisenter;
    }

    @Override
    public void onCompleted() {
        lisenter.onCompleted(requestType);
    }

    @Override
    public void onError(Throwable e) {
        if (e.getMessage() != null && e.getMessage().contains("Failed to connect to")) {
            lisenter.onError(requestType, Const.REQUEST_RETRY, "网络连接超时，请稍后重试！");
        } else if (e.getMessage() != null && e.getMessage().contains("timeout")) {
            lisenter.onError(requestType, Const.REQUEST_RETRY, "网络连接超时，请稍后重试！");
        } else if (e.getMessage() != null && e.getMessage().contains("No address associated with hostname")) { //服务器错误
            lisenter.onError(requestType, Const.REQUEST_RETRY, "没有可用的网络，请检查当前网络环境！");
        } else if (e.getMessage() != null && e.getMessage().contains("HTTP 500 Internal Server Error")) {
            lisenter.onError(requestType, Const.REQUEST_RETRY, "服务器错误，请稍后重试！");
        } else {
            lisenter.onError(requestType, Const.REQUEST_TOAST, e.getMessage());
        }

    }

    @Override
    public void onNext(BaseResult baseResult) {
        if (baseResult.getCode() == Const.REQUEST_SUCCESS) {
            lisenter.onNext(requestType, baseResult.getData());
        } else {
            lisenter.onError(requestType, Const.REQUEST_TOAST, baseResult.getMsg());
        }

    }
}
