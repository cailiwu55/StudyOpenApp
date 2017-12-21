package test.di2;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by clw on 2017/12/19.
 */

public class Son {
    private static final String TAG = "Son";
    @Inject
    Car mCar;
    @Inject
    Bike mBike;

    public void goSchool() {
        Log.d(TAG, "goSchool: ");
        DaggerManComponent.create().sonComponent().build().inject(this);
        mCar.go();
        mBike.go();
    }
}
