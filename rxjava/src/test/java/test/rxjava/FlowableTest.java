package test.rxjava;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * Created by clw on 2019/1/17.
 */
public class FlowableTest {

    @Test
    public void testFlowable() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onComplete();
            }
        }, BackpressureStrategy.DROP).subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Integer.MAX_VALUE);
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String o) {
                System.out.println("onNext,o=" + o);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError,e="+t);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
