package demo.interview.com.demoapp.ui.variants;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import demo.interview.com.demoapp.R;
import demo.interview.com.demoapp.application.DemoApplication;
import demo.interview.com.demoapp.ui.variants.di.DaggerVariantsComponent;
import demo.interview.com.demoapp.ui.variants.di.VariantsComponent;
import demo.interview.com.demoapp.ui.variants.di.VariantsModule;
import demo.interview.com.demoapp.ui.variants.models.VariantGroup;
import demo.interview.com.demoapp.ui.variants.models.VariantsDataResponse;
import demo.interview.com.demoapp.ui.variants.views.VariantsViewModel;

public class VariantsFragment extends Fragment {

    private VariantsViewModel variantsViewModel;
    private VariantsComponent variantsComponent;
    private VariantGroupListAdapter variantGroupListAdapter;
    private ArrayList<VariantGroup> variantGroups = new ArrayList<VariantGroup>();
    private RecyclerView listView;

    public static VariantsFragment newInstance() {
        return new VariantsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.variants_fragment, container, false);
        initializeViews(rootView);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        variantsViewModel = ViewModelProviders.of(this).get(VariantsViewModel.class);
        variantsComponent = DaggerVariantsComponent.builder().applicationComponent(((DemoApplication) getActivity().getApplication())
                .getApplicationComponent()).variantsModule(new VariantsModule()).build();
        variantsComponent.inject(this);
        variantsComponent.inject(variantsViewModel);
        observeVariantData();
        observeVariantDataError();
        variantGroupListAdapter = new VariantGroupListAdapter(getContext(),variantsViewModel);
        listView.setAdapter(variantGroupListAdapter);
        variantsViewModel.getVariantDataResponse();
    }

    private void initializeViews(View rootView) {
        listView = (RecyclerView) rootView.findViewById(R.id.variants);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void observeVariantData() {
        variantsViewModel.getVariantsData().observe(this, new Observer<VariantsDataResponse>() {
            @Override
            public void onChanged(@Nullable VariantsDataResponse variantsDataResponse) {
                variantsDataResponse.getVariants().prepareExcludeListOfVariants();
                variantGroupListAdapter.setVariants(variantsDataResponse);
            }
        });
    }

    private void observeVariantDataError() {
        variantsViewModel.getVariantsDataError().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {
            }
        });
    }

}
