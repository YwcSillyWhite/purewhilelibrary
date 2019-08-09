package com.purewhite.ywc.purewhitelibrary.config.event;

import org.greenrobot.eventbus.EventBus;


/**
 * eventbusutils
 */
public class EventBusUtils {


    public static void register(Object object)
    {
        EventBus.getDefault().register(object);
    }

    public static void unregister(Object object)
    {
        EventBus.getDefault().unregister(object);
    }

    public static void post(BaseEvent baseEvent)
    {
        EventBus.getDefault().post(baseEvent);
    }

    public static void post(int code)
    {
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCode(code);
        EventBus.getDefault().post(baseEvent);
    }


    public static void post(String content,int code)
    {
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCode(code);
        baseEvent.setContent(content);
        EventBus.getDefault().post(baseEvent);
    }

    public static void post(int code,Object object)
    {
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCode(code);
        baseEvent.setData(object);
        EventBus.getDefault().post(baseEvent);
    }


    public static void post(int code,String content,Object object)
    {
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setCode(code);
        baseEvent.setContent(content);
        baseEvent.setData(object);
        EventBus.getDefault().post(baseEvent);
    }



    public static void sendStickyEvent(BaseEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
