package demo.interview.com.demoapp.ui.variants.views;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import demo.interview.com.demoapp.ui.variants.models.Variants;
import demo.interview.com.demoapp.ui.variants.models.VariantsDataResponse;
import demo.interview.com.demoapp.ui.variants.models.Variation;
import demo.interview.com.demoapp.ui.variants.repo.VariantRepository;
import demo.interview.com.demoapp.util.interfaces.ResponseCallBack;


public class VariantsViewModel extends ViewModel {

    @Inject
    VariantRepository signInRepository;


    private MutableLiveData<VariantsDataResponse> variantsData = new MutableLiveData<>();
    private MutableLiveData<Throwable> variantsDataError = new MutableLiveData<>();

    // check login
    public MutableLiveData<VariantsDataResponse> getVariantsData() {
        return variantsData;
    }
    public MutableLiveData<Throwable> getVariantsDataError() {
        return variantsDataError;
    }

    public VariantsViewModel(){

    }

    public void getVariantDataResponse(){
        signInRepository.getVariantsData( new ResponseCallBack<VariantsDataResponse>() {
            @Override
            public void onResponse(VariantsDataResponse response) {
                variantsData.setValue(response);
            }

            @Override
            public void onError(Throwable error) {
                variantsDataError.setValue(error);
            }
        });
    }


    public void setEnableOrDiableBasedOnExcludedList(Variants variants) {

        List<List<Variation>> excludedVariationGroup = variants.getExcludedVariationListGroup();
        for (List<Variation> excludedVariationList :
                excludedVariationGroup) {
            for (Variation variation1 :
                    excludedVariationList) {
                variation1.setEnabled(variation1.getInStock() != 1 ? false : true);
                if (variation1.getRadioButton() != null) {
                    variation1.getRadioButton().setEnabled(variation1.getInStock() != 1 ? false : true);
                }

            }
        }

        for (List<Variation> excludedVariationList :
                excludedVariationGroup) {
            int totalUnSelectedRadioButton = 0;
            int lastUnSelectedButtonPosition = -1;
            for (int i = 0; i < excludedVariationList.size(); i++) {
                if (!excludedVariationList.get(i).getIsSelected()) {
                    totalUnSelectedRadioButton++;
                    lastUnSelectedButtonPosition = i;
                }
            }
            if (totalUnSelectedRadioButton == 1) {
                excludedVariationList.get(lastUnSelectedButtonPosition).setEnabled(false);
                if (excludedVariationList.get(lastUnSelectedButtonPosition).getRadioButton() != null) {
                    excludedVariationList.get(lastUnSelectedButtonPosition).getRadioButton().setEnabled(false);
                }
            }
        }
    }
}