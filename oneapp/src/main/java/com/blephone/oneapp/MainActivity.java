package com.blephone.oneapp;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean needAlarm;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.image);

        String packageName = "com.github.shadowsocks";
        PackageManager pm = getPackageManager();
        try {
            Drawable drawable = pm.getApplicationIcon(packageName);
            mImageView.setImageDrawable(drawable);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (needAlarm) {
            Toast.makeText(this, "真的登录页面进入了后台，请注意登录环境是否安全！", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        needAlarm = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) && event.getRepeatCount() == 0) {
            needAlarm = false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
