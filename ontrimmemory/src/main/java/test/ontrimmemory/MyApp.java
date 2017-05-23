package test.ontrimmemory;

import android.app.Application;
import android.util.Log;

/**
 * Created by clw on 2017/5/23.
 */

public class MyApp extends Application {
    private static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        Log.d(TAG,"onCreate");
        super.onCreate();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d(TAG, String.format("onTrimMemory,level=%d", level));
        switch (level) {
            case TRIM_MEMORY_RUNNING_MODERATE:
                break;
            case TRIM_MEMORY_RUNNING_LOW:
                break;
            case TRIM_MEMORY_RUNNING_CRITICAL:
                break;
            case TRIM_MEMORY_UI_HIDDEN:
                break;
            case TRIM_MEMORY_BACKGROUND:
                break;
            case TRIM_MEMORY_MODERATE:
                break;
            case TRIM_MEMORY_COMPLETE:
                break;
        }
        super.onTrimMemory(level);
    }

}
