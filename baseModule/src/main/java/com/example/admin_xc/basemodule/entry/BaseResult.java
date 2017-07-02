package com.example.admin_xc.basemodule.entry;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.entry
 * 日期   :   2017/6/5
 * 时间   ：  16:36
 * 描述   ：
 */

public class BaseResult<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T t) {
        this.data = t;
    }
}
