package com.blephone.aidl.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.blephone.aidl.IRemoteService;
import com.blephone.aidl.R;
import com.blephone.aidl.server.MainActivity.RemoteService;

/**
 * 测试跨进程通讯，此activity运行在remote进程中<br>
 * 1.aidl
 * 2.BroadcastReceiver
 * 3.remoteViews
 */
public class TempActivity extends AppCompatActivity {
    private Button mButton, mButton2, mButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                updateByBroadcast();
            }
        });


        mButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                updateByAidl();
            }
        });
        mButton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mConnection != null) {
            unbindService(mConnection);
        }
    }

    private void updateByBroadcast() {
        Intent intent = new Intent();
        intent.setAction("com.blephone.action.update");
        sendBroadcast(intent);

    }

    private ServiceConnection mConnection;
    private IRemoteService mIRemoteService;

    private void updateByAidl() {

        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mIRemoteService = IRemoteService.Stub.asInterface(iBinder);
                try {
                    mIRemoteService.update();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Toast.makeText(TempActivity.this,"service connected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mIRemoteService = null;
            }
        };
        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction(IRemoteService.class.getName());
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void udpateByRemoteView() {

    }
}
