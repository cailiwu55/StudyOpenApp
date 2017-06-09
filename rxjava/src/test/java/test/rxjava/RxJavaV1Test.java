package test.rxjava;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by clw on 2017/6/9.
 */

public class RxJavaV1Test {

    @Test
    public void createObservable() {
        //First create an Observe
        Observer observer = new Observer() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError = [" + e + "]");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext = [" + o + "]");
            }
        };

        //create obserable
        //Only use simply methods in here.
        //create
        Observable createObserable = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext("one");
                subscriber.onNext("two");
                subscriber.onNext("three");
                subscriber.onCompleted();
                subscriber.onError(new NullPointerException());
                subscriber.onNext("four");
            }
        });
        createObserable.subscribe(observer);
        //just
        Observable<String> justObservable = Observable.<String>just("just");
        justObservable.subscribe(observer);

        //from
        Observable<String> fromObservable = Observable.<String>from(new String[]{"fromOne", "fromTwo", "fromThree"});
        fromObservable.subscribe(observer);

        //defer
        Observable<String> deferObservable = Observable.<String>defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return justObservable;
            }
        });
        deferObservable.subscribe(observer);

        //interval ???
        Observable intervalObservable = Observable.interval(1000, 1000, TimeUnit.MILLISECONDS);
        intervalObservable.subscribe(observer);

        //range
        Observable rangeObservable = Observable.range(4, 3);
        rangeObservable.subscribe(observer);

        //timer ???
        Observable timerObservable = Observable.timer(1, TimeUnit.SECONDS);
        timerObservable.subscribe(observer);

        //repeat
        Observable repeatObservable = Observable.merge(justObservable, fromObservable);
        repeatObservable.subscribe(observer);

    }

    @Test
    public void useOperator() {
        //merge
        Observable.just("One").mergeWith(Observable.just("Two")).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("call = [" + s + "]");
            }
        });

        //map
        Observable.just("One").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+" map";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("map call = [" + s + "]");
            }
        });




    }

}
