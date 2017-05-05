package com.hangzhou.xc.test.customview.model;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.hangzhou.xc.test.customview.model
 * 日期   :   2017/04/07
 * 时间   ：  16:57
 * 描述   ：
 */

public class ItemModel  {
    private String title;
    private String description;
    private Class clazz;
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public ItemModel(String title, String description, Class clazz) {
        this.title = title;
        this.description = description;
        this.clazz = clazz;
    }
}
