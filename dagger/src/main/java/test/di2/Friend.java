package test.di2;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by clw on 2017/12/19.
 */

public class Friend {
    private static final String TAG = "Friend";
    @Inject
    Car mCar;

    public void goHome(){
        Log.d(TAG, "goHome: ");
        DaggerFriendComponent.builder().manComponent(DaggerManComponent.create()).build().inject(this);
        mCar.go();
    }
}
