package com.example.admin_xc.basemodule.event;

/**
 * author :   Xchong
 * 项目名：   AndroidStudioMaps
 * 包名   :   com.example.admin_xc.basemodule.event
 * 日期   :   2017/6/6
 * 时间   ：  15:37
 * 描述   ：event基类，
 */

public class BaseEvent {

    /**
     * 收到消息 刷新当前页面数据
     */
    public static String REFRESH_SELF="REFRESH_SELF";

    /**
     * 收到消息 关闭当前页面
     */
    public static String FINISH_SELF="FINISH_SELF";



    public BaseEvent(String eventType) {
        this.eventType = eventType;
    }

    public BaseEvent(String eventType, String values) {
        this.eventType = eventType;
        this.values = values;
    }

    /**
     * 发出的事件 类型
     */
    private String eventType;
    /**
     * 发出的事件 的值
     */
    private String values;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
