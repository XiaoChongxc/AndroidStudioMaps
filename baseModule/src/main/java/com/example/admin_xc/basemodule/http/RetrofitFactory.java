package com.example.admin_xc.basemodule.http;

import com.example.admin_xc.basemodule.util.L;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author :   Xchong
 * 项目名：   VPdai
 * 包名   :   com.hangzhou.vpdai.http
 * 日期   :   2017/3/6
 * 时间   ：  11:59
 * 描述   ：
 */

public class RetrofitFactory {
    public static Retrofit retrofit;
    public static String baseUrl = "http://t.vpdai.com/";


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(getClient(0))
                            .build();
                    return retrofit;
                }
            }
        }
        return retrofit;
    }

    public static Retrofit getRetrofit(long time) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getClient(time))
                .build();
    }


    private static OkHttpClient getClient(long time) {
        if (time < 10000L) time = 10000L;  //毫秒
        HttpsUtils.SSLParams mSslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(time, TimeUnit.MILLISECONDS)
                .readTimeout(time, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor())
                        //这里是 https 的验证
//                .hostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                })
//                .sslSocketFactory(mSslParams.sSLSocketFactory, mSslParams.trustManager)
                .build();
//
        return mOkHttpClient;

    }


    static class LoggerInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request compressedRequest = request.newBuilder()
                    .build();
            Response response = chain.proceed(compressedRequest);
            L.e("request url:" + request.url() + "\nresponse code:" + response.code());
            return response;
        }
    }
}
