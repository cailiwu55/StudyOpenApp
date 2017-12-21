package test.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * mvp模式使用dagger2的例子
 */
public class MainActivity extends Activity implements MainContract.View {
    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);

        //调用Presenter方法加载数据
        mainPresenter.loadData();


    }

    @Override
    public void updateUI() {
        Log.d(TAG, "updateUI: ");
    }
}




