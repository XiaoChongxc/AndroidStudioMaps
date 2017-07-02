package com.example.admin_xc.basemodule.entry;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.entry
 * 日期   :   2017/6/6
 * 时间   ：  17:08
 * 描述   ： 登陆 票据失效 异常
 */

public class TokenOverdueException extends RuntimeException {
    public TokenOverdueException() {
    }

    public TokenOverdueException(String message) {
        super(message);
    }
}
