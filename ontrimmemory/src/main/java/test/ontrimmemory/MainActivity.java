package test.ontrimmemory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }
}
