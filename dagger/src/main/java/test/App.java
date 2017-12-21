package test;

import android.app.Application;

import javax.inject.Inject;

import dagger.releasablereferences.ForReleasableReferences;
import dagger.releasablereferences.ReleasableReferenceManager;
import test.di2.ManScope;

/**
 * Created by clw on 2017/12/12.
 */

public class App extends Application {
    @Inject
    @ForReleasableReferences(ManScope.class)
    ReleasableReferenceManager mReferenceManager;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //在内存不足时调用releaseStrongReferences()方法把 ManComponent 间接持有的强引用变为弱引用
        mReferenceManager.releaseStrongReferences();
    }
}
