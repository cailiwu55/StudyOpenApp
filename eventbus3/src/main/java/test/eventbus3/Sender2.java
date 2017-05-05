package test.eventbus3;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by clw on 2017/3/14.
 */

public class Sender2 {

    public static void postMessageEvent(){
        EventBus.getDefault().post(new MessageEvent());
    }
}
