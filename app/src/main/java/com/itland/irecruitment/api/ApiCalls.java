package com.itland.irecruitment.api;

import com.itland.irecruitment.Responses.CitiesListResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Saad on 5/11/2018.
 */

public class ApiCalls {

    private Retrofit retrofit ;

    public Apis apis;

    public ApiCalls() {
        LoggingInterceptor interceptor = new LoggingInterceptor();

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://82.137.221.168")
                .client(client)
                .build();

        apis = retrofit.create(Apis.class);

    }

//    public void CitiesList()
//    {
//        apis.CitiesList(0).enqueue(new Callback<CitiesListResponse>() {
//            @Override
//            public void onResponse(Call<CitiesListResponse> call, Response<CitiesListResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<CitiesListResponse> call, Throwable throwable) {
//
//            }
//        });
//    }


}
