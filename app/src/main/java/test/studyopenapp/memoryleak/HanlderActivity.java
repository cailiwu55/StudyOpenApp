package test.studyopenapp.memoryleak;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import test.studyopenapp.R;

/**
 * Android使用Handler造成内存泄露的分析及解决方法
 * https://www.cnblogs.com/xujian2014/p/5025650.html
 */
public class HanlderActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MyHandler mMyHandler;

    static class MyHandler extends Handler {
        private WeakReference<Activity> mWeakRef;

        MyHandler(Activity activity) {
            mWeakRef = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

        public Activity getActivity() {
            return mWeakRef.get();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyHandler = new MyHandler(this);
        mMyHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mMyHandler.getActivity() != null) {
                    //todo
                }
            }
        }, 1000);
    }
}
