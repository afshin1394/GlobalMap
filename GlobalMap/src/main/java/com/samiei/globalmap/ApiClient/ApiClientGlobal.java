package com.samiei.globalmap.ApiClient;



import com.samiei.globalmap.Api.APIServiceMapIr;
import com.samiei.globalmap.Api.APIServiceValhalla;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClientGlobal {


    private static ApiClientGlobal instance;
    private static APIServiceValhalla apiServiceValhallaInstance = null;
    private static APIServiceMapIr apiServiceMapIrInstance = null;
    private static final int TIME_OUT = 90;



    public static ApiClientGlobal getInstance(){
        if (instance==null)
            instance=new ApiClientGlobal();
        return instance;
    }




    private static <S> S createService(Class<S> serviceClass, String baseURL,String header) {


       HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       OkHttpClient client ;
       if (header!=null) {
           client = new OkHttpClient.Builder()
                   .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                   .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                   .addInterceptor(chain -> {
                       Request newRequest = chain.request().newBuilder()
                               .addHeader("x-api-key:", header)
                               .build();
                       return chain.proceed(newRequest);
                   })
                   .build();
       }
       else
       {
           client = new OkHttpClient.Builder()
                   .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                   .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                   .addInterceptor(interceptor)
                   .build();
       }




        Retrofit.Builder builder = new Retrofit
                .Builder()
                .client(client)
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }



    public APIServiceValhalla getClientServiceValhalla(String urlOsrm) {
        String baseUrl = urlOsrm;
        if (apiServiceValhallaInstance == null)
            apiServiceValhallaInstance = createService(APIServiceValhalla.class, baseUrl,null);
        return apiServiceValhallaInstance;
    }

    public APIServiceMapIr getClientServiceMapIr(String mapKey) {
        String baseUrl = "https://map.ir/";
        if (apiServiceMapIrInstance == null)
            apiServiceMapIrInstance = createService(APIServiceMapIr.class, baseUrl,mapKey);
        return apiServiceMapIrInstance;
    }


}
