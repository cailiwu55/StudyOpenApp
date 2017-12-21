package test.di;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by clw on 2017/8/31.
 */

public class Man {
    private static final String TAG = "Man";
    @Inject
    Car car;

    public void goWork() {
        DaggerComponent.create().inject(this);
        Log.d(TAG, "test: " + car);
    }
}
