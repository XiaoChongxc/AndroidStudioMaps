package com.example.admin_xc.basemodule.http;

import android.text.TextUtils;

import com.example.admin_xc.basemodule.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * User:    Xiaoc
 * 项目名:  WZB3.0
 * Package: baizhuan.hangzhou.com.wzb30.http
 * Date:    2016-06-24
 * Time:    15:32
 * 类描述：  请求的参数 封装类
 */
public class HttpParamsHelper {
    public static final String key = "key";

    /**
     * 通用参数列表
     *
     * @return
     */
    public static Map baseParams() {
        Map<String, String> map = new HashMap();
        map.put("sid", BaseApplication.getInstance().getSeesionId());
        return map;
    }

    /**
     * 从本地获取sid
     *
     * @return
     */
    public static Observable<String> getlocalSid() {
        String sid = BaseApplication.getInstance().getSeesionId();
        Observable<String> observable = Observable.just(sid);
        return observable;
    }

    /***
     * 联网获取sid
     *
     * @return
     */
    public static Observable<String> getNetworkSid() {
        Observable<String> observable = HttpAPI.getService().createSid(HttpParamsHelper.createSid());
        return observable;
    }


    /***
     * 创建会话
     *
     * @return
     */
    public static Map createSid() {
        Map map = new HashMap();
        map.put("client", "android");
        return map;
    }


    /***
     * 创建会话
     *
     * @return
     */
    public static Map checkSid() {
        Map map = new HashMap();
        map.put("sid", BaseApplication.getInstance().getSeesionId());
        return map;
    }

    /***
     * 用户注册
     *
     * @param tel
     * @param pwd
     * @param smsverify
     * @param authcode
     * @param invitecode
     * @return
     */
    public static Map register(String tel, String pwd, String smsverify, String authcode, String invitecode) {
        Map map = baseParams();
        map.put("mobile", tel);
        map.put("password", pwd);
//         短信验证码
        if (!TextUtils.isEmpty(smsverify))
            map.put("smsverify", smsverify);
//        //图形验证码
//        if (!TextUtils.isEmpty(smsverify))
//            map.put("smsverify", smsverify);

        //车商授权码
        if (!TextUtils.isEmpty(authcode))
            map.put("authcode", authcode);
//        注册邀请码
        if (!TextUtils.isEmpty(invitecode))
            map.put("invitecode", invitecode);
        return map;
    }

    /***
     * 发送短信验证码
     *
     * @return
     */
    public static Map sendSmsVerify(String tel, String imgverify) {
        Map map = baseParams();
        map.put("mobile", tel);
        if (!TextUtils.isEmpty(imgverify))
            map.put("imgverify", imgverify);
        return map;
    }


    /***
     * 校验短信验证码
     *
     * @return
     */
    public static Map checkSmsVerify(String tel, String smsverify) {
        Map map = baseParams();
        map.put("mobile", tel);
        map.put("smsverify", smsverify);
        return map;
    }

    /**
     * 获取到 产品列表
     *
     * @return
     */
    public static Map getProductList(int page) {
        Map<String, String> map = new HashMap();
        map.put("userid", "");
        map.put("ticket", "");
        map.put("currentPage", "" + page);
        return map;
    }

}
