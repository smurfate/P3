package com.itland.employer.api;

import com.itland.employer.MainActivity;
import com.itland.employer.Requests.EditActiveVacancyRequest;
import com.itland.employer.Requests.EditCompanyProfileRequest;
import com.itland.employer.Requests.EditExpiredVacancyRequest;
import com.itland.employer.Requests.EditInactiveVacancyRequest;
import com.itland.employer.Requests.FilterJobSeekersRequest;
import com.itland.employer.Requests.PostVacancyRequest;
import com.itland.employer.Responses.CitiesListResponse;
import com.itland.employer.Responses.FilterJobSeekerResponse;
import com.itland.employer.Responses.GeneralResponse;
import com.itland.employer.Responses.IndicesListResponse;
import com.itland.employer.Responses.JobApplicationsListResponse;
import com.itland.employer.Responses.MyVacanciesResponse;
import com.itland.employer.Responses.TokenResponse;
import com.itland.employer.entities.CompanyProfile;
import com.itland.employer.entities.JobApplicationDetails;
import com.itland.employer.entities.ResumeDetails;
import com.itland.employer.entities.VacancyDetails;
import com.itland.employer.util.PrefUtil;
import com.itland.employer.util.SharedPreferencesKeys;

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

    public void getContactTitlesList(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListTitles().enqueue(convertCallback(callback));
    }

    public void getCvLanguage(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListLanguageNames().enqueue(convertCallback(callback));
    }

    public void getListMilitaryServices(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListMilitaryServices().enqueue(convertCallback(callback));
    }

    public void getListSalary(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListSalaries().enqueue(convertCallback(callback));
    }

    public void getListCurrencies(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListCurriencies().enqueue(convertCallback(callback));
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

    public void viewProfile(CallbackWrapped<CompanyProfile> callback)
    {
        apis.ViewProfile(authorization).enqueue(convertCallback(callback));
    }

    public void editProfile(String nameEn, String nameAr,String aboutAr,String aboutEn,String addressAr,
                            String addressEn,Integer cityId, Integer industryId, String commercialRegister,
                            Integer pBox, Integer contactTitleId,String contactFirstName, String contactLastName,
                            String contactPosition, CallbackWrapped<GeneralResponse> callback)
    {
        EditCompanyProfileRequest request = new EditCompanyProfileRequest();
        request.ArAbout = aboutAr;
        request.EnAbout = aboutEn;
        request.ArAddress = addressAr;
        request.EnAddress = addressEn;
        request.ArName = nameAr;
        request.EnName = nameEn;
        request.CityId = cityId;
        request.IndustryId = industryId;
        request.CommercialRegister = commercialRegister;
        request.POBox = pBox;
        request.PersonalDetailsTitleId = contactTitleId;
        request.PersonalDetailsFirstName = contactFirstName;
        request.PersonalDetailsLastName = contactLastName;
        request.PersonalDetailsposition = contactPosition;



        apis.EditProfile(authorization,request).enqueue(convertCallback(callback));
    }


    public void postJobVacancy(String positionTitle, String jobDescription, String preRequisites,
                               String keySkills, String benefits,String aboutCompany, Integer countyId,
                               Integer cityId, Integer requiredYearsExperience, Integer minEduDegree,
                               Integer fieldOfWork, Integer jobTitle, Integer militaryService, Integer salary,
                               Integer requiredEmployeeNumber, Boolean photo,Integer cvLanguage,
                               Boolean hidCompanyName,CallbackWrapped<GeneralResponse> callback)
    {
        PostVacancyRequest request = new PostVacancyRequest();

        request.Title = positionTitle;
        request.JobDescription = jobDescription;
        request.PreRequisites = preRequisites;
        request.Benefits = benefits;
        request.About = aboutCompany;
        request.CityId = cityId;
        request.RequiredYearsOfExperienceId = requiredYearsExperience;
        request.MinEducationDegreeId = minEduDegree;
        request.FieldOfWorkId = fieldOfWork;
        request.JobTypeId = jobTitle;
        request.MilitaryServiceId = militaryService;
        request.SalaryId = salary;
        request.RequiredEmployeesCount = requiredEmployeeNumber;
        request.PhotoIsRequired = photo;
        request.RequiredCVLanguageId = cvLanguage;
        request.IsNameHidden = hidCompanyName;


        apis.PostJobVacancy(authorization,request).enqueue(convertCallback(callback));
    }

    public void editActiveVacancy(Integer eduId,Integer cvLang, Integer salaryId, Integer currencyId,
                            Integer fieldOfWorkId, Boolean photo, Boolean hideCompanyName,
                            Boolean isActive,CallbackWrapped<GeneralResponse> callback)
    {
        EditActiveVacancyRequest request = new EditActiveVacancyRequest();
        request.MinEducationDegreeId = eduId;
        request.FieldOfWorkId = fieldOfWorkId;
        request.IsActive = isActive;
        request.IsNameHidden = hideCompanyName;
        request.PhotoIsRequired = photo;
        request.RequiredCVLanguageId = cvLang;


        apis.EditActiveJobVacancy(authorization,request).enqueue(convertCallback(callback));
    }






}
