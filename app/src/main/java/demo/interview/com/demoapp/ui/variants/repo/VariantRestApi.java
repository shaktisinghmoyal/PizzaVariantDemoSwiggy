package demo.interview.com.demoapp.ui.variants.repo;

import demo.interview.com.demoapp.ui.variants.models.VariantsDataResponse;
import demo.interview.com.demoapp.util.Constants.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VariantRestApi {

    @GET(Urls.VARIANTS_API)
    Call<VariantsDataResponse> getVariantsData();


}
