package test.di3;

import android.app.Activity;
import android.util.Log;

/**
 * Created by clw on 2017/12/13.
 */

public class  TestPrester {
    private static final String TAG = "TestPrester";
    private Activity mActivity;

    public TestPrester(Activity activity) {
        mActivity = activity;
    }

    public void showView() {
        Log.d(TAG, "showView: " + mActivity);
    }
}
