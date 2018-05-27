package demo.interview.com.demoapp.di.component;

import android.content.Context;

import demo.interview.com.demoapp.di.module.ApplicationModule;
import demo.interview.com.demoapp.util.Util;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @Named("WithoutHeaders")
    Retrofit provideRetrofitWithoutHeaders();

    @Named("WithHeaders")
    Retrofit provideRetrofitWithHeaders();

    Context providesContext();

    Util provideUtility();

}
