package com.example.common.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author winiymissl
 * @Date 2024-04-09 20:09
 * @Version 1.0
 */
public class EventBusUtils {
    /**
     * 绑定 接受者
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 解绑定
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送消息(事件)
     *
     * @param event
     */
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送 粘性 事件
     * <p>
     * 粘性事件，在注册之前便把事件发生出去，等到注册之后便会收到最近发送的粘性事件（必须匹配）
     * 注意：只会接收到最近发送的一次粘性事件，之前的会接受不到。
     *
     * @param event
     */
    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

}
