package test.mvp;

import android.app.Activity;
import android.os.Bundle;

import test.dagger.R;

/**
 * MVP模式
 * Model：JavaBean
 * View：MainActivity
 * Presenter：MainPresenter
 *
 * MainActivity 持有MainPresenter的实例，并在这里实例化它
 * MainPresenter持有view
 *
 *
 *
 * Created by clw on 2017/8/26.
 */

public class MainActivity extends Activity implements MainContact.View {
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.loadData();
    }

    @Override
    public void updateView() {
        System.out.println("updateView");
    }
}
