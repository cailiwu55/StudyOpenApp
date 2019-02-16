package com.blephone.aidl.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.blephone.aidl.IRemoteService;
import com.blephone.aidl.IRemoteService.Stub;
import com.blephone.aidl.R;
import com.blephone.aidl.client.TempActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    MyBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv);

        mReceiver = new MyBroadcastReceiver();
        registerReceiver(mReceiver, new IntentFilter("com.blephone.action.update"));

        startActivity(new Intent(this, TempActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            mTextView.setText("updated");
        }
    }

    /**
     * 向客户端公开接口
     */
    public class RemoteService extends Service {

        @Override
        public void onCreate() {
            super.onCreate();
        }

        @Override
        public IBinder onBind(Intent intent) {
            return mBinder;
        }

        //实现接口
        private IRemoteService.Stub mBinder = new Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble,
                                   String aString) throws RemoteException {

            }

            @Override
            public void update() throws RemoteException {
                mTextView.setText("updated");
            }
        };
    }
}
