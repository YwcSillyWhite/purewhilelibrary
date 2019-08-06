package com.purewhite.ywc.purewhitelibrary.config.event;

import org.greenrobot.eventbus.EventBus;

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

    public static void sendStickyEvent(BaseEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
