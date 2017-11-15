package test.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    public static final String API_URL = "https://api.github.com";

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }

        @Override
        public String toString() {
            return "Contributor{" +
                    "login='" + login + '\'' +
                    ", contributions=" + contributions +
                    '}';
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Observable<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

    private OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("token", "123");
                    return chain.proceed(builder.build());
                }
            })
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d(TAG, "message: " + message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))

            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private CompositeDisposable cd = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();

        GitHub service = retrofit.create(GitHub.class);
        Observable<List<Contributor>> observable = service.contributors("square1", "retrofit");

        observable.compose(RxSchedulers.<List<Contributor>>compose())
                .subscribe(new Consumer<List<Contributor>>() {
                    @Override
                    public void accept(@NonNull List<Contributor> contributors) throws Exception {
                        for (Contributor contributor : contributors) {
                            Log.d(TAG, "contributor: " + contributor);
                        }
                    }
                });


        //observable.compose(RxSchedulers.<List<Contributor>>compose())
        //        .subscribe(new Observer<List<Contributor>>() {
        //            @Override
        //            public void onSubscribe(@NonNull Disposable d) {
        //                Log.d(TAG, "onSubscribe: ");
        //            }
        //
        //            @Override
        //            public void onNext(@NonNull List<Contributor> contributors) {
        //                Toast.makeText(MainActivity.this, "onNext", Toast.LENGTH_SHORT).show();
        //                Log.d(TAG, "onNext: ");
        //                for (Contributor contributor : contributors) {
        //                    Log.d(TAG, "contributor: " + contributor);
        //                }
        //            }
        //
        //            @Override
        //            public void onError(@NonNull Throwable e) {
        //                Log.d(TAG, "onError: " + e);
        //            }
        //
        //            @Override
        //            public void onComplete() {
        //                Log.d(TAG, "onComplete: ");
        //            }
        //        });
    }

}

class RxSchedulers {

    public static <T> ObservableTransformer<T, T> compose() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                            }
                        })
                        .subscribeOn(Schedulers.io());
            }
        };
    }
}


