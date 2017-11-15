package test.multicast;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //send
        final String ip = "230.0.0.1";
        final int port = 8800;
        final String message = "test";
        new Thread(new Runnable() {
            @Override
            public void run() {
                TUdpSender sender = new TUdpSender(MainActivity.this);
                sender.DoSendMsg(ip, port, message);
            }
        }).start();

        //receiver
        TUdpRecevier recevier = new TUdpRecevier();
        recevier.DoStart();
    }

}
