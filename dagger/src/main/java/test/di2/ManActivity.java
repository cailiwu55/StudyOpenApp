package test.di2;

import android.app.Activity;
import android.os.Bundle;

import test.dagger.R;

/**
 * Created by clw on 2017/12/19.
 */

public class ManActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //测试基本的dagger依赖注入
        //构造函数inject
        new test.di.Man().goWork();
        //Module方式
        new test.di2.Man().goWork();
        new test.di2.Friend().goHome();
        new Son().goSchool();
    }
}
