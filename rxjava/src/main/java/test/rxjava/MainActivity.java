package test.rxjava;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends RxAppCompatActivity {
    public static final String TAG = "MainActivity";
    private Disposable mIntervalDisposable;

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

        //lifecycle2
        //Observable.interval(1, TimeUnit.SECONDS)
        //        .doOnDispose(new Action() {
        //            @Override
        //            public void run() throws Exception {
        //                Log.i(TAG, "Unsubscribing subscription ......");
        //            }
        //        })
        //        .doOnNext(new Consumer<Long>() {
        //            @Override
        //            public void accept(@NonNull Long aLong) throws Exception {
        //                Log.i(TAG, "........fuck..........." + aLong);
        //            }
        //        })
        //        .flatMap(new Function<Long, ObservableSource<String>>() {
        //            @Override
        //            public ObservableSource<String> apply(@NonNull Long aLong) throws Exception {
        //                return Observable.just(aLong + "");
        //            }
        //        })
        //        .compose(RxLifecycleAndroid.bindActivity(lifecycle()))
        //        .subscribe(new Consumer<String>() {
        //            @Override
        //            public void accept(@NonNull String s) throws Exception {
        //                Log.i(TAG, "..........shit..........." + s);
        //            }
        //        });

        //操作符基本使用方法
        //create
        Log.d(TAG, "==========create");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "subscribe: emit 1");
                e.onNext("1");
                Log.d(TAG, "subscribe: emit 2");
                e.onNext("2");
                Log.d(TAG, "subscribe: emit 3");
                e.onNext("3");
                Log.d(TAG, "subscribe: emit 4");
                e.onNext("4");

            }
        }).subscribe(new Observer<String>() {
            Disposable mDisposable;
            int i;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext: s=" + s);
                i++;
                if (i == 2) {
                    mDisposable.dispose();
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: e=" + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });

        //map
        //subscribeOn,observeOn
        //subscribeOn指定发射线程  observeOn指定接收线程
        //subscribeOn设置多次只有第一个有效
        //observeOn设置多次，每次都会生效
        Log.d(TAG, "==========map");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "subscribe: thread :" + Thread.currentThread().getName());
                e.onNext("1");
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                return s + " ";
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "accept: thread :" + Thread.currentThread().getName());
                    }
                }).observeOn(Schedulers.computation())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "map accept: thread :" + Thread.currentThread().getName());
                    }
                });
        //zip 操作符
        Log.d(TAG, "=========zip");
        Observable<String> stringObservalbe = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    Log.d(TAG, "subscribe: emit A");
                    e.onNext("A");
                    Log.d(TAG, "subscribe: emit B");
                    e.onNext("B");
                    Log.d(TAG, "subscribe: emit C");
                    e.onNext("C");
                    e.onComplete();
                }
            }
        });
        Observable<Integer> intObservalbe = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    Log.d(TAG, "subscribe: emit 1");
                    e.onNext(1);
                    Log.d(TAG, "subscribe: emit 2");
                    e.onNext(2);
                    Log.d(TAG, "subscribe: emit 3");
                    e.onNext(3);
                    Log.d(TAG, "subscribe: emit 4");
                    e.onNext(4);
                    Log.d(TAG, "subscribe: emit 5");
                    e.onNext(5);
                    e.onComplete();
                }
            }
        });
        Observable.zip(stringObservalbe, intObservalbe, new BiFunction<String, Integer, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.d(TAG, "zip accept: " + s);
            }
        });
        //concat
        Log.d(TAG, "==========concat");
        Observable.concat(intObservalbe, stringObservalbe).subscribe(new Consumer<Serializable>() {
            @Override
            public void accept(@NonNull Serializable serializable) throws Exception {
                Log.d(TAG, "accept: " + serializable);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Log.e(TAG, "concat accept: ", throwable);
            }
        });
        Log.d(TAG, "==========flatMap");
        //flatMap
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am " + i);
                }
                int delay = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delay, TimeUnit.SECONDS);
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String r) throws Exception {
                        Log.d(TAG, "flatMap accept: " + r);
                    }
                });

        Log.d(TAG, "==========concatMap");
        //concatMap
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am " + i);
                }
                int delay = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delay, TimeUnit.SECONDS);
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String r) throws Exception {
                        Log.d(TAG, "concatMap accept: " + r);
                    }
                });
        //distinct
        Log.d(TAG, "==========distinct");
        Observable.just(1, 2, 3, 1, 2, 3, 4)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });
        //filter
        Log.d(TAG, "==========filter");
        Observable.just(1, 5, 112, 123, 11, 10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer >= 10;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });

        //buffer
        Log.d(TAG, "==========buffer");
        Observable.just(1, 23, 4, 5, 67, 87, 8, 8, 1, 2)
                .buffer(3)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        Log.d(TAG, "accept: " + integers);
                    }
                });
        //timer
        Log.d(TAG, "==========timer");
        Observable.timer(3L, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.d(TAG, "timer accept: " + aLong);
                    }
                });
        //interval
        Log.d(TAG, "==========interval");
        mIntervalDisposable = Observable.interval(1, 1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.d(TAG, "interval accept: " + aLong);
                    }
                });
        //doOnNext
        Log.d(TAG, "==========doOnNext");
        Observable.just(1, 2, 3, 4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "doOnNext before: " + integer);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.d(TAG, "doOnNext accept: " + integer);
            }
        });
        //skip
        Log.d(TAG, "==========skip");
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "skip accept: " + integer);
                    }
                });
        //take
        Log.d(TAG, "==========take");
        Flowable.fromArray(1, 2, 3, 4, 5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "take accept: " + integer);
                    }
                });
        //just and fromArray
        Log.d(TAG, "==========just & fromArray");
        Observable.just(1, 2, 3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "just accept: " + integer);
                    }
                });
        Observable.fromArray(1, 2, 3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "fromArray accept: " + integer);
                    }
                });
        //single
        Log.d(TAG, "==========single");
        Single.just(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "single accept: " + integer);
                    }
                });
        //debounce
        Log.d(TAG, "==========debounce");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                SystemClock.sleep(400);
                e.onNext(2);
                SystemClock.sleep(500);
                e.onNext(3);
                SystemClock.sleep(567);
                e.onNext(4);
                SystemClock.sleep(899);
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "debounce accept: " + integer);
                    }
                });
        //defer
        Log.d(TAG, "==========defer");
        Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3, 4);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "defer onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });

        //last
        Log.d(TAG, "==========last");
        Observable.just(1, 2, 3)
                .last(8)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "last accept: " + integer);
                    }
                });
        //merge
        Log.d(TAG, "==========merge");
        Observable one = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Thread.sleep(500);
                e.onNext(2);
                Thread.sleep(500);
                e.onNext(3);
            }
        }).subscribeOn(Schedulers.newThread());
        Observable two = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("a");
                Thread.sleep(300);
                e.onNext("b");
                Thread.sleep(300);
                e.onNext("c");
            }
        }).subscribeOn(Schedulers.newThread());
        Observable.merge(one, two)
                .subscribe(new Consumer() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        Log.d(TAG, "merge accept: " + o);

                    }
                });
        //reduce
        Log.d(TAG, "==========reduce");
        Observable.just(1, 2, 3, 4, 5)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.d(TAG, "reduce accept: " + integer);
            }
        });
        //scan
        Log.d(TAG, "===========scan");
        Observable.just(1, 2, 3, 4, 5)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.d(TAG, "scan accept: " + integer);
            }
        });
        //window
        Log.d(TAG, "==========window");
        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .window(3)
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(@NonNull Observable<Long> longObservable) throws Exception {
                        Log.d(TAG, "window accept: " + longObservable);
                        longObservable.subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(@NonNull Long aLong) throws Exception {
                                Log.d(TAG, "window accept: " + aLong);
                            }
                        });
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
        if (mIntervalDisposable != null && !mIntervalDisposable.isDisposed()) {
            mIntervalDisposable.dispose();
        }
        super.onDestroy();

    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();

    }

}
