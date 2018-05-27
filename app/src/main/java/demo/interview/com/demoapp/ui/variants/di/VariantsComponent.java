package demo.interview.com.demoapp.ui.variants.di;

import demo.interview.com.demoapp.di.component.ApplicationComponent;
import demo.interview.com.demoapp.di.scopes.UserScope;
import demo.interview.com.demoapp.ui.variants.repo.VariantRepository;
import demo.interview.com.demoapp.ui.variants.repo.VariantRestApi;
import demo.interview.com.demoapp.ui.variants.VariantsFragment;

import dagger.Component;
import demo.interview.com.demoapp.ui.variants.views.VariantsViewModel;

@UserScope
@Component(dependencies = ApplicationComponent.class, modules = VariantsModule.class)
public interface VariantsComponent {

    void inject(VariantsFragment fragment);
    void inject(VariantsViewModel variantsViewModel);

    VariantRestApi providesRestApi();
    VariantRepository provideSignInRepository();


}
