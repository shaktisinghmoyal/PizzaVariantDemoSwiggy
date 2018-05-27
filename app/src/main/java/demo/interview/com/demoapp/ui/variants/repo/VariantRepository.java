package demo.interview.com.demoapp.ui.variants.repo;

import android.content.Context;
import android.widget.Toast;

import demo.interview.com.demoapp.ui.variants.models.VariantsDataResponse;
import demo.interview.com.demoapp.util.GlobalError;
import demo.interview.com.demoapp.util.Util;
import demo.interview.com.demoapp.util.interfaces.ResponseCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VariantRepository {


    private static VariantRepository instance;


    private VariantRestApi mRestApi;
    private Util mUtil;
    private Context mContext;


    public static VariantRepository getInstance(){
        if(instance == null ){
            synchronized (VariantRepository.class) {
                if (instance == null) {
                    instance = new VariantRepository();
                }
            }
        }

        return instance;
    }

    public void setVariables(VariantRestApi restApi, Util util, Context context){
        mRestApi =restApi;
        mUtil = util;
        mContext = context;
    }

    public void getVariantsData(final ResponseCallBack<VariantsDataResponse> responseCallBack){


        mRestApi.getVariantsData().enqueue(new Callback<VariantsDataResponse>() {
            @Override
            public void onResponse(Call<VariantsDataResponse> call, Response<VariantsDataResponse> response) {

                if(response.isSuccessful()){

                    responseCallBack.onResponse(response.body());

                }else {
                    mUtil.handleErrorResponse(response);
                    handleError(mUtil.globalErrorHandler(response.errorBody()));
                }


            }

            @Override
            public void onFailure(Call<VariantsDataResponse> call, Throwable t) {
                mUtil.handleNetworkResponseError(mContext, t);
                responseCallBack.onError(t);
            }
        });


    }

    public void handleError(GlobalError response){

        if(response != null) {

            if (response.getError().getErrorCode().equalsIgnoreCase("431")) {

            }

            Toast.makeText(mContext, "" + response.getMessage(), Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show();
        }

    }




}
