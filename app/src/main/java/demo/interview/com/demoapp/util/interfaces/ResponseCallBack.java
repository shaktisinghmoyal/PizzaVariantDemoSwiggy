package demo.interview.com.demoapp.util.interfaces;

public interface ResponseCallBack<T> {

    void onResponse(T response) ;

    void onError(Throwable error);

}