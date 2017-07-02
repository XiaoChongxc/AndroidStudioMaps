package com.example.admin_xc.basemodule.mvp.model;

import com.example.admin_xc.basemodule.base.BaseSubscriber;
import com.example.admin_xc.basemodule.http.HttpAPI;
import com.example.admin_xc.basemodule.http.HttpParamsHelper;
import com.example.admin_xc.basemodule.mvp.BaseModel;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp.model
 * 日期   :   2017/6/6
 * 时间   ：  16:42
 * 描述   ： 用户相关 model 登陆注册， 发送验证码啥的
 */

public class UserModel extends BaseModel {

    /**
     * 注册
     *
     * @param tel
     * @param pwd
     * @param code
     * @param subscriber
     */
    public void register(String tel, String pwd, String code, BaseSubscriber subscriber) {
        observable = HttpAPI.getService().register(HttpParamsHelper.register(tel, pwd, code, "", ""));
        addRetryMode(observable, subscriber);
    }

    /**
     * 发送短信验证码
     *
     * @param tel
     * @param imgVerify
     * @param subscriber
     */
    public void sendSms(String tel, String imgVerify, BaseSubscriber subscriber) {
        observable = HttpAPI.getService().sendSmsVerify(HttpParamsHelper.sendSmsVerify(tel, imgVerify));
        addRetryMode(observable, subscriber);
    }

    /**
     * 验证短信验证码
     *
     * @param tel
     * @param imgVerify
     * @param subscriber
     */
    public void verifySms(String tel, String imgVerify, BaseSubscriber subscriber) {
        observable = HttpAPI.getService().sendSmsVerify(HttpParamsHelper.checkSmsVerify(tel, imgVerify));
        addRetryMode(observable, subscriber);
    }

    /**
     * 创建sid
     *
     * @param subscriber
     */
    public void createSid(BaseSubscriber subscriber) {
        subscription = HttpAPI.getService().createSid(HttpParamsHelper.createSid())
                .compose(Rx_Transformer())
                .subscribe(subscriber);
    }

}
