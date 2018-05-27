package demo.interview.com.demoapp.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.interview.com.demoapp.util.Constants.Urls;
import demo.interview.com.demoapp.util.Util;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {


    private final Context mContext;
    private final int TIME_OUT_DURATION = 60;

    public ApplicationModule(Application context) {
        mContext = context;
    }


    @Provides
    @Singleton
    Context providesContext() {
        return mContext;
    }


    @Provides
    @Singleton
    @Named("WithoutHeaders")
    public Retrofit provideRetrofitWithoutHeaders(Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    @Named("WithHeaders")
    public Retrofit provideRetrofitWithHeaders(Gson gson, OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.ROOT_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS);
        httpClient.readTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Content-Type","application/json");

                Request request = requestBuilder.build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        return client;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder.create();
    }




    @Provides
    @Singleton
    public Util provideUtility(){
       return Util.getInstance();
    }


}
