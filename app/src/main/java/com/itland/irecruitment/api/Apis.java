package com.itland.irecruitment.api;

import com.itland.irecruitment.Requests.EditActiveVacancyRequest;
import com.itland.irecruitment.Requests.EditCompanyProfileRequest;
import com.itland.irecruitment.Requests.EditExpiredVacancyRequest;
import com.itland.irecruitment.Requests.EditInactiveVacancyRequest;
import com.itland.irecruitment.Requests.FilterJobSeekersRequest;
import com.itland.irecruitment.Requests.PostVacancyRequest;
import com.itland.irecruitment.Responses.CitiesListResponse;
import com.itland.irecruitment.Responses.FilterJobSeekerResponse;
import com.itland.irecruitment.Responses.GeneralResponse;
import com.itland.irecruitment.Responses.IndicesListResponse;
import com.itland.irecruitment.Responses.JobApplicationsListResponse;
import com.itland.irecruitment.Responses.MyVacanciesResponse;
import com.itland.irecruitment.Responses.TokenResponse;
import com.itland.irecruitment.entities.CompanyProfile;
import com.itland.irecruitment.entities.JobApplication;
import com.itland.irecruitment.entities.JobApplicationDetails;
import com.itland.irecruitment.entities.Resume;
import com.itland.irecruitment.entities.ResumeDetails;
import com.itland.irecruitment.entities.Vacancy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Saad on 5/11/2018.
 */

public interface Apis {



    @FormUrlEncoded
    @POST("/IRecruitment.Api/token")
    Call<TokenResponse> token(@Field("grant_type") String grantType, @Field("username") String username, @Field("password") String password, @Field("scope") String scope);

    @GET("/IRecruitment.Api/api/EmpApplications/JobApplications")
    Call<JobApplicationsListResponse> JobApplicationsList(@Query("page") Integer page, @Header("Authorization") String authorization);

    @GET("/IRecruitment.Api/api/EmpApplications/Get/{id}")
    Call<JobApplicationDetails> JobApplications(@Path("id") Integer id, @Header("Authorization") String authorization);

    @GET("/IRecruitment.Api/api/EmpCompanies/ViewProfile")
    Call<CompanyProfile> ViewProfile(@Header("Authorization") String authorization);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpCompanies/EditProfile")
    Call<GeneralResponse> EditProfile(@Header("Authorization") String authorization, @Body EditCompanyProfileRequest request);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpResumes/FilterJobSeekers")
    Call<FilterJobSeekerResponse> FilterJobSeekers(@Header("Authorization") String authorization, @Body FilterJobSeekersRequest request);

    @GET("/IRecruitment.Api/api/EmpResumes/Get/{id}")
    Call<ResumeDetails> Resume(@Path("id") Integer id, @Header("Authorization") String authorization);

    @GET("/IRecruitment.Api/api/EmpVacancies/MyVacancies")
    Call<MyVacanciesResponse> MyVacancies(@Header("Authorization") String authorization,@Query("page") Integer page);

    @GET("/IRecruitment.Api/api/EmpVacancies/Get/{id}")
    Call<Vacancy> Vacancy(@Path("id") Integer id, @Header("Authorization") String authorization);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpVacancies/PostJobVacancy")
    Call<GeneralResponse> PostJobVacancy(@Header("Authorization") String authorization, @Body PostVacancyRequest request);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpVacancies/EditActiveJobVacancy")
    Call<GeneralResponse> EditActiveJobVacancy(@Header("Authorization") String authorization, @Body EditActiveVacancyRequest request);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpVacancies/EditInActiveJobVacancy")
    Call<GeneralResponse> EditInActiveJobVacancy(@Header("Authorization") String authorization, @Body EditInactiveVacancyRequest request);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpVacancies/EditReAddExpiredJobVacancy")
    Call<GeneralResponse> EditExpiredJobVacancy(@Header("Authorization") String authorization, @Body EditExpiredVacancyRequest request);

    @GET("/IRecruitment.Api/api/Cities/List")
    Call<CitiesListResponse> CitiesList(@Query("page") Integer page);

    @GET("/IRecruitment.Api/api/Indices/ListJobTypes")
    Call<IndicesListResponse> ListJobTypes();

    @GET("/IRecruitment.Api/api/Indices/ListFieldsOfWork")
    Call<IndicesListResponse> ListFieldsOfWork();

    @GET("/IRecruitment.Api/api/Indices/ListNationalities")
    Call<IndicesListResponse> ListNationalities();

    @GET("/IRecruitment.Api/api/Indices/ListSkillLevels")
    Call<IndicesListResponse> ListSkillLevels();

    @GET("/IRecruitment.Api/api/Indices/ListLanguageLevels")
    Call<IndicesListResponse> ListLanguageLevels();

    @GET("/IRecruitment.Api/api/Indices/ListCareerLevels")
    Call<IndicesListResponse> ListCareerLevels();

    @GET("/IRecruitment.Api/api/Indices/ListRequiredYearsOfExperience")
    Call<IndicesListResponse> ListRequiredYearsOfExperience();

    @GET("/IRecruitment.Api/api/Indices/ListNoticePeriods")
    Call<IndicesListResponse> ListNoticePeriods();

    @GET("/IRecruitment.Api/api/Indices/ListLanguageNames")
    Call<IndicesListResponse> ListLanguageNames();

    @GET("/IRecruitment.Api/api/Indices/ListMilitaryServices")
    Call<IndicesListResponse> ListMilitaryServices();

    @GET("/IRecruitment.Api/api/Indices/ListDrivingLicenceTypes")
    Call<IndicesListResponse> ListDrivingLicenceTypes();

    @GET("/IRecruitment.Api/api/Indices/ListProfilePrivacies")
    Call<IndicesListResponse> ListProfilePrivacies();

    @GET("/IRecruitment.Api/api/Indices/ListCurriencies")
    Call<IndicesListResponse> ListCurriencies();

    @GET("/IRecruitment.Api/api/Indices/ListEducationDegreeLevels")
    Call<IndicesListResponse> ListEducationDegreeLevels();

    @GET("/IRecruitment.Api/api/Indices/ListEducationDegreeMajors")
    Call<IndicesListResponse> ListEducationDegreeMajors();

    @GET("/IRecruitment.Api/api/Indices/ListTitles")
    Call<IndicesListResponse> ListTitles();

    @GET("/IRecruitment.Api/api/Indices/ListSalaries")
    Call<IndicesListResponse> ListSalaries();

    @GET("/IRecruitment.Api/api/Indices/ListCountryCodes")
    Call<IndicesListResponse> ListCountryCodes();




}
