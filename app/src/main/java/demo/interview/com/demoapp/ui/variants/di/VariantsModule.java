package demo.interview.com.demoapp.ui.variants.di;

import android.content.Context;

import demo.interview.com.demoapp.di.scopes.UserScope;
import demo.interview.com.demoapp.ui.variants.repo.VariantRepository;
import demo.interview.com.demoapp.ui.variants.repo.VariantRestApi;
import demo.interview.com.demoapp.util.Util;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class VariantsModule {

    @Provides
    @UserScope
    public VariantRestApi providesSignInRestApi(@Named("WithHeaders") Retrofit retrofit) {
        return retrofit.create(VariantRestApi.class);
    }

    @Provides
    @UserScope
    VariantRepository provideSignInRepository(VariantRestApi signInRestApi, Util util , Context context){

        VariantRepository signInRepository = VariantRepository.getInstance();
        signInRepository.setVariables(signInRestApi, util, context);

        return signInRepository;

    }

}
