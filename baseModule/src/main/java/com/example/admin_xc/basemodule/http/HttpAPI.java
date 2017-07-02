package com.example.admin_xc.basemodule.http;

import com.example.admin_xc.basemodule.entry.BaseResult;
import com.example.admin_xc.basemodule.entry.Product;
import com.example.admin_xc.basemodule.entry.Session;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.http
 * 日期   :   2017/6/6
 * 时间   ：  14:40
 * 描述   ：  请求的接口地址 返回 一个observer 订阅者
 */

public class HttpAPI {

    private static HttpService service;

    public static HttpService getService() {
        if (service == null) {
            synchronized (HttpService.class) {
                if (service == null) {
                    service = RetrofitFactory.getRetrofit().create(HttpService.class);
                }
            }
        }
        return service;
    }

    public interface HttpService {

        /***
         * 创建一个 会话
         *
         * @param map
         * @return
         */
        @POST("api/session/create")
        Observable<BaseResult<Session>> createSid(@QueryMap Map<String, Object> map);

        /***
         * 检查会话
         * api/session/check
         *
         * @param map
         * @return
         */
        @POST("api/session/check")
        Observable<BaseResult> checkSid(@QueryMap Map<String, Object> map);

        /***
         * 用户注册
         * /api/user/reg
         *
         * @param map
         * @return
         */
        @POST("api/user/reg")
        Observable<BaseResult<Map>> register(@QueryMap Map<String, Object> map);

        /***
         * 发送短信验证码
         *
         * @param map
         * @return
         */
        @POST("api/user/sendSmsVerify")
        Observable<BaseResult> sendSmsVerify(@QueryMap Map<String, Object> map);


        @Multipart
        @POST("api/order/upload")
        Observable<BaseResult> uploadFile(@Path("url") String url, @PartMap Map<String, RequestBody> map);


        @POST("https://dev.52wzb.com/wzb2/{url}")
        Observable<BaseResult<List<Product>>> getProductList(@Path("url") String url, @QueryMap Map<String, Object> map);


    }


}
