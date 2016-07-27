package com.wlht.oa.net;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wlht.oa.AppContext;
import com.wlht.oa.base.BaseFragment;
import com.wlht.oa.base.net.ErrorSubscriber;
import com.wlht.oa.base.net.HttpResponseFunc;
import com.wlht.oa.base.net.RxSubscriber;
import com.wlht.oa.base.net.ServerResponseFunc;
import com.wlht.oa.base.util.NetWorkUtil;
import com.wlht.oa.base.util.helper.RxSchedulers;
import com.wlht.oa.bean.Menu;
import com.wlht.oa.bean.Result;
import com.wlht.oa.base.exception.ApiException;
import com.wlht.oa.base.exception.ExceptionEngine;
import com.wlht.oa.base.exception.ServerException;
import com.wlht.oa.util.TLog;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by baixiaokang on 16/3/9.
 *
 * BASE_URL  推荐使用/结尾
 * @URL 推荐不要使用/开始,
 *      使用/开始表示截断BASE_URL的尾部,只保留域名部分
 *      可以使用完整的url,完整url会忽略base_url
 */
public class Api {

    public static final String X_LC_Id = "i7j2k7bm26g7csk7uuegxlvfyw79gkk4p200geei8jmaevmx";
    public static final String X_LC_Key = "n6elpebcs84yjeaj5ht7x0eii9z83iea8bec9szerejj7zy3";
//    public static final String BASE_URL = "http://172.17.52.75:8080";
//    http://localhost:8080/
    public static final String BASE_URL = "http://172.17.52.182:8080/";
//    http://172.17.52.75:8080/Login/Login
    public static final int DEFAULT_TIMEOUT = 5;

    public Retrofit retrofit;
    public ApiService movieService;
//    ASP.NET_SessionId=3xe5er5urwnndk1sxupvvl1v; path=/
//    __RequestVerificationToken=iF0Ff3nE8_MQci-dnS7aJBVtbzxc1o8iUSawfy-PM87TVbzevGd2_9lNvvzKopOJrhwYpiObYL0JPN7vqaC6xO39Ab_VZSjspPkmRtZx6tI1; path=/
    Interceptor mInterceptor = (chain) -> chain.proceed(chain.request().newBuilder()
            .addHeader("__RequestVerificationToken",X_LC_Id)
            .addHeader("ASP.NET_SessionId", X_LC_Key)
//            .addHeader("Content-Type", "application/json")
            .build());

    private final Interceptor loggingInterceptor = chain -> {
        Request request = chain.request();
        long t1 = System.nanoTime();
        TLog.log(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        TLog.log(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    };


    private HashMap<String,String> mHeaderParams = new HashMap<>();
    public void addHeader(String name,String value)
    {
        mHeaderParams.put(name,value);
    }


    private Interceptor interceptor(){
        return  new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                for (Map.Entry<String,String> entry : mHeaderParams.entrySet())
                {
                    builder.addHeader(entry.getKey(),entry.getValue());
                }

               return chain.proceed(builder.build());
            }
        };

//        chain.proceed(chain.request().newBuilder()
//                .addHeader("__RequestVerificationToken",X_LC_Id)
//                .addHeader("ASP.NET_SessionId", X_LC_Key)
////            .addHeader("Content-Type", "application/json")
//                .build());
//       return chain.proceed(chain.request().newBuilder().addHeader("","").build());
    }




    private HttpCacheInterceptor cacheInterceptor = new HttpCacheInterceptor();
    //构造方法私有
    private Api() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(AppContext.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .cookieJar(new CookiesManager())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .addInterceptor(interceptor())
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
//                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache)
                .build();



        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .setLenient()
                .create();




        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(ApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    //获取单例
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }


    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetConnected(AppContext.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                TLog.log("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetConnected(AppContext.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }


    protected void test()
    {

        getInstance()
                .movieService.menu()
                .compose(RxSchedulers.error_handle_io_main())
                .subscribe(new RxSubscriber<Menu>(null){
                    @Override
                    public void onNext(Menu menu) {
                        super.onNext(menu);
                    }
                });


        Subscription subscriber =
        getInstance()
                .movieService.menu()
                .map(new ServerResponseFunc<>())
                .onErrorResumeNext(new HttpResponseFunc<>())
                .subscribe(new ErrorSubscriber<Menu>() {
                    @Override
                    protected void onError(ApiException ex) {

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(Menu menu) {

                    }
                });


        subscriber.unsubscribe();


    }







}