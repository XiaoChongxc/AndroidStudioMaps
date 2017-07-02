package com.example.admin_xc.basemodule.entry;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.entry
 * 日期   :   2017/6/6
 * 时间   ：  15:29
 * 描述   ：
 */

public class Session {

    private String sid;
    private String client;
    private String lang;
    private long expire;
    private long createTime;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
