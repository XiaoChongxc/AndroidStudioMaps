package com.example.admin_xc.basemodule.mvp;

import com.example.admin_xc.basemodule.base.BaseApplication;
import com.example.admin_xc.basemodule.base.BaseSubscriber;
import com.example.admin_xc.basemodule.entry.BaseResult;
import com.example.admin_xc.basemodule.entry.Session;
import com.example.admin_xc.basemodule.entry.TokenOverdueException;
import com.example.admin_xc.basemodule.http.HttpAPI;
import com.example.admin_xc.basemodule.http.HttpParamsHelper;
import com.example.admin_xc.basemodule.util.Const;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.mvp
 * 日期   :   2017/6/6
 * 时间   ：  17:04
 * 描述   ：
 */

public class BaseModel {

    public Subscription subscription;

    public Observable observable;

    /***
     * 产生一个通用的 订阅器
     *
     * @param <T>
     * @return
     */
    public <T> Observable.Transformer<T, T> Rx_Transformer() {

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onTerminateDetach() //和unsubscribe()结合使用
                        .unsubscribeOn(AndroidSchedulers.mainThread())
                        .filter(new Func1<T, Boolean>() {   //过滤 token失效
                            @Override
                            public Boolean call(T baseResult) {
                                return ResultFilter((BaseResult) baseResult);
                            }
                        });
            }
        };
    }

    /***
     * 刷新token
     *
     * @param toBeResumed
     * @param <T>
     * @return
     */
    public <T> Func1<Throwable, ? extends Observable<? extends T>> refreshTokenAndRetry(final Observable<T> toBeResumed) {
        return new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(final Throwable throwable) {
                throwable.printStackTrace();
                // 这里判断是不是登陆失效 如果是， 就重试
                if (isTokenOverdueException(throwable)) {
                    return HttpAPI.getService().createSid(HttpParamsHelper.createSid()).subscribeOn(Schedulers.io()).flatMap(new Func1<BaseResult<Session>, Observable<? extends T>>() {
                        @Override
                        public Observable<? extends T> call(BaseResult<Session> token) {
                            BaseApplication.getInstance().saveSeesionId(token.getData().getSid());
//                            return Observable.error(throwable);
                            return toBeResumed.retry();
                        }
                    }).observeOn(AndroidSchedulers.mainThread());
                }
                // re-throw this error because it's not recoverable from here
                return Observable.error(throwable);
            }

            public boolean isTokenOverdueException(Throwable throwable) {
                return throwable instanceof TokenOverdueException;
            }
        };
    }


    /**
     * 返回结果过滤
     */
    public boolean ResultFilter(BaseResult result) {
        if (Const.REQUEST_ERROR_SESSION_UNABLE == result.getCode()) {
            //token失效 重新登录
            throw new TokenOverdueException("");
        } else {
            return true;
        }
    }

    /***
     * 取消请求
     */
    public void cancel() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    /**
     * 添加session 失效重试机制
     *
     * @param observable
     * @param subscriber
     */
    public void addRetryMode(Observable observable, BaseSubscriber subscriber) {
        subscription = observable.compose(Rx_Transformer())
                .onErrorResumeNext(refreshTokenAndRetry(observable))
                .subscribe(subscriber);
    }
}
