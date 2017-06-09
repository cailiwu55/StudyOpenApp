package test.anr;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试Anr产生的几种原因
 * 1、主线程中执行耗时操作
 * 2、广播接收器中执行耗时操作
 * 3、Service中执行耗时操作
 */
public class MainActivity extends Activity {
    @BindView(R.id.btn1)
    Button mButton1;
    @BindView(R.id.btn2)
    Button mButton2;
    @BindView(R.id.btn3)
    Button mButton3;

    private MyBrocastReciver mMyBrocastReciver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMyBrocastReciver = new MyBrocastReciver();
        registerReceiver(mMyBrocastReciver, new IntentFilter("com.anr.mybr"));

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMyBrocastReciver != null) {
            unregisterReceiver(mMyBrocastReciver);
        }
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
     void submit(Button button) {
        if (button.getId() == R.id.btn1) {
            SystemClock.sleep(6 * 1000);
        } else if (button.getId() == R.id.btn2) {
            Intent intent = new Intent("com.anr.mybr");
            sendBroadcast(intent);
        } else if (button.getId() == R.id.btn3) {
            Intent intent = new Intent();
            intent.setAction("com.anr.myService");
            startService(intent);
        }
    }

    class MyBrocastReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == "com.anr.mybr") {
                SystemClock.sleep(11 * 1000);
            }
        }
    }

}
