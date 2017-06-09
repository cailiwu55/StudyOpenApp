package test.rxjava;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //simpleUse();
        //useJust();
        useObservable();
    }

    //private void simpleUse() {
    //    Observable<String> obserable = new Observable<String>() {
    //        @Override
    //        protected void subscribeActual(Observer<? super String> observer) {
    //            observer.onNext("111");
    //            observer.onComplete();
    //        }
    //    };
    //
    //    Observer observer = new Observer() {
    //        @Override
    //        public void onSubscribe(@NonNull Disposable d) {
    //            System.out.println("subscribe");
    //        }
    //
    //        @Override
    //        public void onNext(@NonNull Object o) {
    //            System.out.println("receive: " + o.toString());
    //        }
    //
    //        @Override
    //        public void onError(@NonNull Throwable e) {
    //            System.out.println("error, " + e.getMessage());
    //        }
    //
    //        @Override
    //        public void onComplete() {
    //            System.out.println("complete");
    //        }
    //    };
    //    obserable.subscribe(observer);
    //}
    //
    //private void useJust() {
    //    Observable.just("1", "2", "3").subscribe(new Consumer<String>() {
    //        @Override
    //        public void accept(@NonNull String s) throws Exception {
    //            System.out.println("accept:" + s);
    //        }
    //    });
    //    List<String> list = new ArrayList<>();
    //    list.add("a");
    //    list.add("b");
    //    list.add("c");
    //    Observable.fromArray(list).subscribe(new Consumer<List<String>>() {
    //        @Override
    //        public void accept(@NonNull List<String> strings) throws Exception {
    //            System.out.println("accept:" + strings);
    //        }
    //    });
    //}
    private void useObservable() {

        //create
       Subscription subscription =  Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("create");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });


        //just
        Observable.just("just").subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
        //from
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        Observable.from(list)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("from:" + s);
                    }
                });
        //defer
        Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return  Observable.just("defer");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }
}