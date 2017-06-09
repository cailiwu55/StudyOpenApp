package test.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/**
 * ClassName: BaseActivity<p>
 * Author: Alpha<p>
 * Fuction: <p>
 * CreateDate: 2016/7/3 0:17<p>
 */
public class BaseActivity extends AppCompatActivity {

    protected BehaviorSubject<Event> subject = BehaviorSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.interval(3, TimeUnit.SECONDS)
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.i("rxjava", "unsubscribe");
                    }
                })
                .compose(this.<Long>bindLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.i("rxjava", "onCompleted");

                        Toast.makeText(BaseActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final Long aLong) {
                        Log.i("rxjava", "onNext " + aLong);

                        Toast.makeText(BaseActivity.this, "aLong:" + aLong, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    protected <T> Observable.Transformer<T, T> bindLife() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.takeUntil(subject.skipWhile(new Func1<Event, Boolean>() {
                    @Override
                    public Boolean call(Event event) {
                        return event != Event.DESTROY && event != Event.DETACH;
                    }
                }));
            }
        };
    }

    @Override
    protected void onDestroy() {
        subject.onNext(Event.DESTROY);
        super.onDestroy();
    }

    enum Event {
        // Activity life Events
        CREATE,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY,
        // Fragment life  Events
        ATTACH,
        CREATE_VIEW,
        DESTROY_VIEW,
        DETACH
    }
}