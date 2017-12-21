package test.di3;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by clw on 2017/12/13.
 */

@Module
public class TestModule {
    private Activity mActivity;

    public TestModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public TestPrester provideTestPrester() {
        return new TestPrester(mActivity);
    }

}
