package demo.interview.com.demoapp.application;

import android.app.Application;

import demo.interview.com.demoapp.di.component.ApplicationComponent;
import demo.interview.com.demoapp.di.component.DaggerApplicationComponent;
import demo.interview.com.demoapp.di.module.ApplicationModule;


public class DemoApplication extends Application {


    private static DemoApplication applicationContext;
    private ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public static DemoApplication app() {
        return applicationContext;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}