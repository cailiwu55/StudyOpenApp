package test.rxjava;

import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends RxAppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Observable.interval(1, TimeUnit.SECONDS)
        //        .doOnUnsubscribe(new Action0() {
        //            @Override
        //            public void call() {
        //                Log.d(TAG, "unsubscribing subscription from onDestory");
        //            }
        //        })
        //        .compose(this.<Long>bindToLifecycle())
        //        .subscribe(new Action1<Long>() {
        //            @Override
        //            public void call(Long s) {
        //                Log.d(TAG, "Started in onCreate, running until onDestory: " + s);
        //            }
        //        });
        //Observable.interval(0, 1, TimeUnit.SECONDS)
        //        .doOnUnsubscribe(new Action0() {
        //            @Override
        //            public void call() {
        //                Log.d(TAG, "unsubscribing subscription from onStart");
        //            }
        //        })
        //        .compose(this.<Long>bindUntilEvent(ActivityEvent.START))
        //        .subscribe(new Action1<Long>() {
        //            @Override
        //            public void call(Long aLong) {
        //                Log.d(TAG, "Started in onCreate, running until onStart:" + aLong);
        //            }
        //        });

        //onPause()后还是停止不下来
        //Observable.just("test")
        //        .doOnUnsubscribe(new Action0() {
        //            @Override
        //            public void call() {
        //                Log.d(TAG, "unsubscribing subscription ");
        //            }
        //        })
        //        .compose(this.<String>bindUntilEvent(ActivityEvent.PAUSE))
        //        .flatMap(new Func1<String, Observable<Long>>() {
        //            @Override
        //            public Observable<Long> call(String s) {
        //                return Observable.interval(1, TimeUnit.SECONDS);
        //            }
        //        })
        //        .subscribe(new Action1<Long>() {
        //            @Override
        //            public void call(Long aLong) {
        //                Log.d(TAG, "compost before flatMap:" + aLong);
        //            }
        //        });
        //onPause后会停下来
        //Observable.just("test")
        //        .doOnUnsubscribe(new Action0() {
        //            @Override
        //            public void call() {
        //                Log.d(TAG, "unsubscribing subscription ");
        //            }
        //        })
        //        .flatMap(new Func1<String, Observable<Long>>() {
        //            @Override
        //            public Observable<Long> call(String s) {
        //                return Observable.interval(1, TimeUnit.SECONDS);
        //            }
        //        })
        //        .compose(this.<Long>bindUntilEvent(ActivityEvent.PAUSE))
        //        .subscribe(new Action1<Long>() {
        //            @Override
        //            public void call(Long aLong) {
        //                Log.d(TAG, "compost before flatMap:" + aLong);
        //            }
        //        });

        Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.i(TAG, "Unsubscribing subscription ......");
                    }
                })
                .doOnNext(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.i(TAG, "........fuck..........." + aLong);
                    }
                })
                .flatMap(new Func1<Long, Observable<String>>() {
                    @Override
                    public Observable<String> call(Long aLong) {
                        return Observable.just(aLong + "");
                    }
                })
                .compose(this.<String>bindUntilEvent(ActivityEvent.PAUSE))
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String num) {
                        Log.i(TAG, "..........shit..........." + num);
                    }
                });

    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestory");
        super.onDestroy();

    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();

    }

}
