package com.itland.irecruitment.api;

import com.itland.irecruitment.Requests.FilterJobSeekersRequest;
import com.itland.irecruitment.Responses.CitiesListResponse;
import com.itland.irecruitment.Responses.FilterJobSeekerResponse;
import com.itland.irecruitment.Responses.TokenResponse;
import com.itland.irecruitment.util.PrefUtil;
import com.itland.irecruitment.util.SharedPreferencesKeys;

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
    private Apis apis;
    private String authorization;
    private String language;

    public ApiCalls() {
        LoggingInterceptor interceptor = new LoggingInterceptor();

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://82.137.221.168")
                .client(client)
                .build();

        apis = retrofit.create(Apis.class);

        authorization = PrefUtil.getStringPreference(SharedPreferencesKeys.token);

    }

    public void CitiesList(Callback<CitiesListResponse> callback)
    {
        apis.CitiesList(0).enqueue(callback);
    }

    public void SignIn(String userName, String password,Callback<TokenResponse> callback)
    {
        apis.token("password",userName,password,"JobSeeker").enqueue(callback);
    }

    public void GetResumes(Callback<FilterJobSeekerResponse> callback)
    {
        apis.FilterJobSeekers(authorization,new FilterJobSeekersRequest()).enqueue(callback);
    }

    public void FilterResumes(Callback<FilterJobSeekerResponse> callback,String searchQuery,Integer cityId,
                              Integer minEduLevelId,Integer countryId,Integer fieldOfWorkId, Integer jobTypeId,
                              Boolean photo)
    {
        FilterJobSeekersRequest request = new FilterJobSeekersRequest();
        request.SearchQuery = searchQuery;
        request.MinEducationLevelId = minEduLevelId;
        request.CityId = cityId;
        request.CountryId = countryId;
        request.FieldOfWorkId = fieldOfWorkId;
        request.JobTypeId = jobTypeId;
        request.WithPhoto = photo;
        apis.FilterJobSeekers(authorization,new FilterJobSeekersRequest()).enqueue(callback);
    }



}
