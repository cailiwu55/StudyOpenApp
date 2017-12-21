package test.di3;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

import test.dagger.R;

/**
 * Created by clw on 2017/12/13.
 * MVP简单例子
 * //TODO 理解@BindsInstance
 */
public class TestActivity extends Activity {
    @Inject
    TestPrester mDaggerPrester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerTestComponent.builder().testModule(new TestModule(this)).build().inject(this);
        mDaggerPrester.showView();
    }
}
