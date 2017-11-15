package test.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.BehaviorSubject;

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
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i("rxjava", "unsubscribe");
                    }
                })
                .compose(this.<Long>bindLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        Log.i("rxjava", "onNext " + o);

                        Toast.makeText(BaseActivity.this, "aLong:" + o, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("rxjava", "onCompleted");

                        Toast.makeText(BaseActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }

    protected <T> ObservableTransformer<T, T> bindLife() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.takeUntil(subject.skipWhile(new Predicate<Event>() {
                    @Override
                    public boolean test(@NonNull Event event) throws Exception {
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