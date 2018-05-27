package demo.interview.com.demoapp.util;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Response;


public class Util {


    private static Util instance;


    public static Util getInstance(){
        if(instance == null ){
            synchronized (Util.class) {
                if (instance == null) {
                    instance = new Util();
                }
            }
        }

        return instance;
    }

    public static void handleNetworkResponseError(Context context, Throwable error) {

        if (error instanceof SocketTimeoutException) {
            Toast.makeText(context, "Some error occurred, Kindly try after some time ", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show();
        }

    }

    public static void handleErrorResponse(Response<?> response) {

        int code = response.code();

        if (code == 401) {                  //unauthenticated
            System.out.println("UNAUTHENTICATED");
        } else if (code >= 400 && code < 500) {   // clientError
            System.out.println("CLIENT ERROR " + response.code() + " " + response.message());
        } else if (code >= 500 && code < 600) {  // serverError
            System.out.println("SERVER ERROR " + response.code() + " " + response.message());
        } else {
            System.err.println("FATAL ERROR " + response.errorBody());
        }

    }

    public GlobalError globalErrorHandler(ResponseBody response){

        Gson gson = new GsonBuilder().create();

        GlobalError mError;
        try {
            mError = gson.fromJson(response.string(), GlobalError.class);

            return  mError;

        } catch (IOException e) {

        }

        return null;
    }

}