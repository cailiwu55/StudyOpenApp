package test.di2;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

/**
 * Created by clw on 2017/8/31.
 */

public class Man {
    private static final String TAG = "Man2";
    @Inject
    Car car1;
    @Inject
    Lazy<Car> car2;
    @Inject
    Provider<Car> carProvider;

    public void goWork() {
        DaggerManComponent.create().inject(this);
        Log.d(TAG, "goWork: ");
        Log.d(TAG, "test: car1:" + car1);
        Log.d(TAG, "test: car2:" + car2.get());
        for (int i = 0; i < 5; i++) {
            Log.d(TAG, "test: carProvider instance" + i + ":" + carProvider.get());
        }
    }
}
