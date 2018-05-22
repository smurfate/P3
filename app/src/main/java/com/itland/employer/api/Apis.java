package com.itland.employer.api;

import com.itland.employer.requests.ChangeEmailRequest;
import com.itland.employer.requests.ChangeGsmRequest;
import com.itland.employer.requests.ChangePasswordRequest;
import com.itland.employer.requests.EditActiveVacancyRequest;
import com.itland.employer.requests.EditCompanyProfileRequest;
import com.itland.employer.requests.EditExpiredVacancyRequest;
import com.itland.employer.requests.EditInactiveVacancyRequest;
import com.itland.employer.requests.FilterJobSeekersRequest;
import com.itland.employer.requests.ForgotPasswordRequest;
import com.itland.employer.requests.PostVacancyRequest;
import com.itland.employer.requests.RegisterRequest;
import com.itland.employer.requests.VerifyAccountRequest;
import com.itland.employer.requests.VerifyChangeEmailRequest;
import com.itland.employer.requests.VerifyChangeGsmRequest;
import com.itland.employer.requests.VerifyForgotPasswordRequest;
import com.itland.employer.responses.CitiesListResponse;
import com.itland.employer.responses.CountyCodeResponse;
import com.itland.employer.responses.CountyListResponse;
import com.itland.employer.responses.FilterJobSeekerResponse;
import com.itland.employer.responses.GeneralResponse;
import com.itland.employer.responses.HomeResponse;
import com.itland.employer.responses.IndicesListResponse;
import com.itland.employer.responses.JobApplicationsListResponse;
import com.itland.employer.responses.MyVacanciesResponse;
import com.itland.employer.responses.RegisterResponse;
import com.itland.employer.responses.TokenResponse;
import com.itland.employer.entities.CompanyProfile;
import com.itland.employer.entities.JobApplicationDetails;
import com.itland.employer.entities.ResumeDetails;
import com.itland.employer.entities.VacancyDetails;

import retrofit2.Call;
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
    Call<TokenResponse> token(@Header("Language") String language,@Field("grant_type") String grantType, @Field("username") String username, @Field("password") String password, @Field("scope") String scope);

    @GET("/IRecruitment.Api/api/EmpApplications/JobApplications")
    Call<JobApplicationsListResponse> JobApplicationsList(@Header("Language") String language,@Query("page") Integer page, @Header("Authorization") String authorization);

    @GET("/IRecruitment.Api/api/EmpApplications/Get/{id}")
    Call<JobApplicationDetails> JobApplications(@Header("Language") String language,@Path("id") Integer id, @Header("Authorization") String authorization);

    @GET("/IRecruitment.Api/api/EmpCompanies/ViewProfile")
    Call<CompanyProfile> ViewProfile(@Header("Language") String language,@Header("Authorization") String authorization);

    @GET("/IRecruitment.Api/api/Account/UserInfo")
    Call<GeneralResponse> UserInfo(@Header("Language") String language,@Header("Authorization") String authorization);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpCompanies/EditProfile")
    Call<GeneralResponse> EditProfile(@Header("Language") String language,@Header("Authorization") String authorization, @Body EditCompanyProfileRequest request);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpResumes/FilterJobSeekers")
    Call<FilterJobSeekerResponse> FilterJobSeekers(@Header("Language") String language,@Header("Authorization") String authorization, @Body FilterJobSeekersRequest request);

    @GET("/IRecruitment.Api/api/EmpResumes/Get/{id}")
    Call<ResumeDetails> Resume(@Header("Language") String language,@Path("id") Integer id, @Header("Authorization") String authorization);

    @GET("/IRecruitment.Api/api/EmpVacancies/MyVacancies")
    Call<MyVacanciesResponse> MyVacancies(@Header("Language") String language,@Header("Authorization") String authorization,@Query("page") Integer page);

    @GET("/IRecruitment.Api/api/EmpVacancies/Get/{id}")
    Call<VacancyDetails> Vacancy(@Header("Language") String language,@Path("id") Integer id, @Header("Authorization") String authorization);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpVacancies/PostJobVacancy")
    Call<GeneralResponse> PostJobVacancy(@Header("Language") String language,@Header("Authorization") String authorization, @Body PostVacancyRequest request);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpVacancies/EditActiveJobVacancy")
    Call<GeneralResponse> EditActiveJobVacancy(@Header("Language") String language,@Header("Authorization") String authorization, @Body EditActiveVacancyRequest request);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpVacancies/EditInActiveJobVacancy")
    Call<GeneralResponse> EditInActiveJobVacancy(@Header("Language") String language,@Header("Authorization") String authorization, @Body EditInactiveVacancyRequest request);

    @Headers("Content-Type: application/json")
    @POST("/IRecruitment.Api/api/EmpVacancies/EditReAddExpiredJobVacancy")
    Call<GeneralResponse> EditExpiredJobVacancy(@Header("Language") String language,@Header("Authorization") String authorization, @Body EditExpiredVacancyRequest request);

    @GET("/IRecruitment.Api/api/Cities/List")
    Call<CitiesListResponse> CitiesList(@Header("Language") String language,@Query("page") Integer page,@Query("pageLength") Integer pageLength);

    @GET("/IRecruitment.Api/api/Countries/List")
    Call<CountyListResponse> CountriesList(@Header("Language") String language, @Query("page") Integer page, @Query("pageLength") Integer pageLength);

    @GET("/IRecruitment.Api/api/Cities/ListCountryCities")
    Call<CitiesListResponse> ListCountryCities(@Header("Language") String language,@Query("page") Integer page,@Query("pageLength") Integer pageLength,@Query("countryId") Integer countryId);

    @GET("/IRecruitment.Api/api/Indices/ListJobTypes")
    Call<IndicesListResponse> ListJobTypes(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListFieldsOfWork")
    Call<IndicesListResponse> ListFieldsOfWork(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListNationalities")
    Call<IndicesListResponse> ListNationalities(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListSkillLevels")
    Call<IndicesListResponse> ListSkillLevels(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListLanguageLevels")
    Call<IndicesListResponse> ListLanguageLevels(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListCareerLevels")
    Call<IndicesListResponse> ListCareerLevels(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListRequiredYearsOfExperience")
    Call<IndicesListResponse> ListRequiredYearsOfExperience(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListNoticePeriods")
    Call<IndicesListResponse> ListNoticePeriods(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListLanguageNames")
    Call<IndicesListResponse> ListLanguageNames(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListMilitaryServices")
    Call<IndicesListResponse> ListMilitaryServices(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListDrivingLicenceTypes")
    Call<IndicesListResponse> ListDrivingLicenceTypes(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListProfilePrivacies")
    Call<IndicesListResponse> ListProfilePrivacies(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListCurriencies")
    Call<IndicesListResponse> ListCurriencies(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListEducationDegreeLevels")
    Call<IndicesListResponse> ListEducationDegreeLevels(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListEducationDegreeMajors")
    Call<IndicesListResponse> ListEducationDegreeMajors(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListTitles")
    Call<IndicesListResponse> ListTitles(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListSalaries")
    Call<IndicesListResponse> ListSalaries(@Header("Language") String language);

    @GET("/IRecruitment.Api/api/Indices/ListCountryCodes")
    Call<CountyCodeResponse> ListCountryCodes(@Header("Language") String language);

    @POST("/IRecruitment.Api/api/Account/Register")
    Call<RegisterResponse> Register(@Header("Language") String language,@Body RegisterRequest request);

    @POST("/IRecruitment.Api/api/Account/ChangeGsm")
    Call<GeneralResponse> ChangeGsm(@Header("Language") String language,@Header("Authorization") String authorization, @Body ChangeGsmRequest request);

    @POST("/IRecruitment.Api/api/Account/ResendCodeChangeGsm")
    Call<GeneralResponse> ResendCodeChangeGsm(@Header("Language") String language,@Header("Authorization") String authorization, @Body ChangeGsmRequest request);

    @POST("/IRecruitment.Api/api/Account/ChangeEmail")
    Call<GeneralResponse> ChangeEmail(@Header("Language") String language,@Header("Authorization") String authorization, @Body ChangeEmailRequest request);

    @POST("/IRecruitment.Api/api/Account/ResendCodeChangeEmail")
    Call<GeneralResponse> ResendCodeChangeEmail(@Header("Language") String language,@Header("Authorization") String authorization, @Body ChangeEmailRequest request);

    @POST("/IRecruitment.Api/api/Account/VerifyChangeEmail")
    Call<GeneralResponse> VerifyChangeEmail(@Header("Language") String language,@Header("Authorization") String authorization, @Body VerifyChangeEmailRequest request);

    @POST("/IRecruitment.Api/api/Account/VerifyChangeGsm")
    Call<GeneralResponse> VerifyChangeGsm(@Header("Language") String language,@Header("Authorization") String authorization, @Body VerifyChangeGsmRequest request);

    @POST("/IRecruitment.Api/api/Account/ForgotPassword")
    Call<GeneralResponse> ForegotPassword(@Header("Language") String language,@Body ForgotPasswordRequest request);

    @POST("/IRecruitment.Api/api/Account/ConfirmForgotPassword")
    Call<GeneralResponse> ConfirmForgotPassword(@Header("Language") String language,@Body VerifyForgotPasswordRequest request);

    @POST("/IRecruitment.Api/api/Account/ResendCodeForgotPassword")
    Call<GeneralResponse> ResendCodeForgotPassword(@Header("Language") String language,@Body ForgotPasswordRequest request);

    @POST("/IRecruitment.Api/api/Account/VerifyAccount")
    Call<GeneralResponse> VerifyAccount(@Header("Language") String language,@Body VerifyAccountRequest request);

    @POST("/IRecruitment.Api/api/Account/ResendCodeRegister")
    Call<GeneralResponse> ResendCodeRegister(@Header("Language") String language,@Body VerifyAccountRequest request);

    @POST("/IRecruitment.Api/api/Account/ChangePassword")
    Call<GeneralResponse> ChangePassword(@Header("Language") String language,@Header("Authorization") String authorization, @Body ChangePasswordRequest request);

    @POST("/IRecruitment.Api/api/Account/Logout")
    Call<GeneralResponse> Logout(@Header("Language") String language,@Header("Authorization") String authorization);

    @GET("/IRecruitment.Api/api/EmpHome/Home")
    Call<HomeResponse> Home(@Header("Language") String language,@Header("Authorization") String authorization);



}
