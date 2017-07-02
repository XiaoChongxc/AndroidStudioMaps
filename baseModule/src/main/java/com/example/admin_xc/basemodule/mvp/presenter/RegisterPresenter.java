package com.example.admin_xc.basemodule.mvp.presenter;

import android.util.Log;

import com.example.admin_xc.basemodule.base.BaseLisenter;
import com.example.admin_xc.basemodule.base.BaseSubscriber;
import com.example.admin_xc.basemodule.mvp.contract.RegitsterContract;
import com.example.admin_xc.basemodule.mvp.model.UserModel;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp.presenter
 * 日期   :   2017/6/6
 * 时间   ：  16:35
 * 描述   ：
 */

public class RegisterPresenter implements RegitsterContract.Presenter, BaseLisenter {

    RegitsterContract.View view;
    UserModel userModel;
    private final int REQUEST_REGISTER_CODE = 111;
    private final int REQUEST_SENDSMS_CODE = 222;
    private final int REQUEST_VERIFYSMS_CODE = 333;


    public RegisterPresenter(RegitsterContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.view.setTitle();
        userModel = new UserModel();
    }

    @Override
    public void onRegister() {
        String tel = view.getUserName();
        String pwd = view.getPwd();
        String smsVerify = view.getSmsCode();
        userModel.register(tel, pwd, smsVerify, new BaseSubscriber(REQUEST_REGISTER_CODE, this));
    }

    @Override
    public void onSendSms() {
        String tel = view.getUserName();
        String imgVerify = "";
        userModel.sendSms(tel, imgVerify, new BaseSubscriber(REQUEST_REGISTER_CODE, this));

    }

    @Override
    public void varifySms() {
        String tel = view.getUserName();
        String imgVerify = view.getSmsCode();
        userModel.verifySms(tel, imgVerify, new BaseSubscriber(REQUEST_REGISTER_CODE, this));
    }


    @Override
    public void onCompleted(int requestType) {
        view.stopLoading();
    }

    @Override
    public void onError(int requestType, int code, String e) {
        switch (requestType) {
            case REQUEST_REGISTER_CODE:

                break;
            case REQUEST_SENDSMS_CODE:

                break;
            case REQUEST_VERIFYSMS_CODE:
                break;
        }
        view.showError(e);
    }

    @Override
    public void onNext(int requestType, Object o) {
        switch (requestType) {
            case REQUEST_REGISTER_CODE:
                Log.d("TAG", "onNext: 注册");
                break;
            case REQUEST_SENDSMS_CODE:
                //发送成功
                view.showError("发送成功！");
                Log.d("TAG", "onNext: 发送验证码");
                break;
            case REQUEST_VERIFYSMS_CODE:
                Log.d("TAG", "onNext: 验证");
                break;
        }
    }
}
