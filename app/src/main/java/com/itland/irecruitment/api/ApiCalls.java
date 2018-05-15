package com.itland.irecruitment.api;

import com.itland.irecruitment.MainActivity;
import com.itland.irecruitment.Requests.FilterJobSeekersRequest;
import com.itland.irecruitment.Responses.CitiesListResponse;
import com.itland.irecruitment.Responses.FilterJobSeekerResponse;
import com.itland.irecruitment.Responses.IndicesListResponse;
import com.itland.irecruitment.Responses.JobApplicationsListResponse;
import com.itland.irecruitment.Responses.MyVacanciesResponse;
import com.itland.irecruitment.Responses.TokenResponse;
import com.itland.irecruitment.entities.JobApplicationDetails;
import com.itland.irecruitment.entities.ResumeDetails;
import com.itland.irecruitment.entities.VacancyDetails;
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
    private MainActivity activity;

    public ApiCalls()
    {
        this(null);
    }

    public ApiCalls(MainActivity activity) {
        LoggingInterceptor interceptor = new LoggingInterceptor();

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://82.137.221.168")
                .client(client)
                .build();

        apis = retrofit.create(Apis.class);

        authorization = PrefUtil.getStringPreference(SharedPreferencesKeys.token);
        this.activity = activity;

    }

    private<T> Callback<T> convertCallback(final CallbackWrapped<T> callback)
    {
        if(activity != null) activity.showProgressIndicator(true);
        Callback<T> cb = new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(activity != null) activity.showProgressIndicator(false);
                if(response.body() != null)
                {
                    callback.onResponse(response.body());
                }
                else
                {
                    callback.onFailure(ErrorMessage.UNKNOWN_ERROR);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                callback.onFailure(ErrorMessage.UNKNOWN_ERROR);
                if(activity != null) activity.showProgressIndicator(false);

            }
        };
        return cb;
    }

    public void citiesList(CallbackWrapped<CitiesListResponse> callback)
    {
        apis.CitiesList(0).enqueue(convertCallback(callback));
    }

    public void signIn(String userName, String password,CallbackWrapped<TokenResponse> callback)
    {
        apis.token("password",userName,password,"JobSeeker").enqueue(convertCallback(callback));
    }

    public void getResumesList(CallbackWrapped<FilterJobSeekerResponse> callback)
    {
        apis.FilterJobSeekers(authorization,new FilterJobSeekersRequest()).enqueue(convertCallback(callback));
    }

    public void filterResumes(String searchQuery,Integer cityId, Integer minEduLevelId,Integer countryId,
                              Integer fieldOfWorkId, Integer jobTypeId, Boolean photo, Integer languageId,
                              CallbackWrapped<FilterJobSeekerResponse> callback)
    {
        FilterJobSeekersRequest request = new FilterJobSeekersRequest();
        request.SearchQuery = searchQuery;
        request.MinEducationLevelId = minEduLevelId;
        request.CityId = cityId;
        request.CountryId = countryId;
        request.FieldOfWorkId = fieldOfWorkId;
        request.JobTypeId = jobTypeId;
        request.ResumeLanguageId = languageId;
        request.WithPhoto = photo;
        apis.FilterJobSeekers(authorization,new FilterJobSeekersRequest()).enqueue(convertCallback(callback));
    }


    public void getResume(Integer id, CallbackWrapped<ResumeDetails> callback)
    {
        apis.Resume(id,authorization).enqueue(convertCallback(callback));
    }

    public void getEducationLevel(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListEducationDegreeLevels().enqueue(convertCallback(callback));
    }

    public void getCountries(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListNationalities().enqueue(convertCallback(callback));
    }

    public void getFieldsOfWork(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListFieldsOfWork().enqueue(convertCallback(callback));
    }

    public void getJobTypes(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListJobTypes().enqueue(convertCallback(callback));
    }

    public void getCvLanguage(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListLanguageNames().enqueue(convertCallback(callback));
    }

    public void getApplicationsList(Integer page, CallbackWrapped<JobApplicationsListResponse> callback)
    {
        apis.JobApplicationsList(page,authorization).enqueue(convertCallback(callback));
    }

    public void getApplicationDetails(Integer id, CallbackWrapped<JobApplicationDetails> callback)
    {
        apis.JobApplications(id,authorization).enqueue(convertCallback(callback));
    }

    public void getVacancies(Integer page, CallbackWrapped<MyVacanciesResponse> callback)
    {
        apis.MyVacancies(authorization,page).enqueue(convertCallback(callback));
    }

    public void getVacancyDetails(Integer id, CallbackWrapped<VacancyDetails> callback)
    {
        apis.Vacancy(id,authorization).enqueue(convertCallback(callback));
    }





}
