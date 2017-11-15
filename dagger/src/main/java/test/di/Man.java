package test.di;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Provider;

import static android.content.ContentValues.TAG;

/**
 * Created by clw on 2017/8/31.
 */

public class Man {
  @Inject Provider<Car> car;

  public void test() {
    DaggerComponent.builder().build().inject(this);
    Log.d(TAG, "test: " + car.get());
    Log.d(TAG, "test: " + car.get());
  }
}
