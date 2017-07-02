package com.example.admin_xc.basemodule.mvp;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.base
 * 日期   :   2017/6/6
 * 时间   ：  16:24
 * 描述   ：
 */

public interface BaseView<T> {


    void setTitle();

    void setPresenter(T presenter);

    void showError(String error);

    void showLoading();

    void stopLoading();

    void loadError(String title, SweetAlertDialog.OnSweetClickListener linstener);

    void showNetworkError();

    boolean isNetworkAvailable();

    void toLogin();

    void finish();
}
