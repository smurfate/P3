package com.itland.employer.api;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.itland.employer.MainActivity;
import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractEntity;
import com.itland.employer.entities.Message;
import com.itland.employer.entities.ProfileInfo;
import com.itland.employer.fragments.ReloadFragment;
import com.itland.employer.registration.RegistrationActivity;
import com.itland.employer.requests.ChangeEmailRequest;
import com.itland.employer.requests.ChangeGsmRequest;
import com.itland.employer.requests.ChangePasswordRequest;
import com.itland.employer.requests.EditActiveVacancyRequest;
import com.itland.employer.requests.EditCompanyProfileRequest;
import com.itland.employer.requests.EditExpiredVacancyRequest;
import com.itland.employer.requests.EditInactiveVacancyRequest;
import com.itland.employer.requests.EditProfileInfoRequest;
import com.itland.employer.requests.FilterJobSeekersRequest;
import com.itland.employer.requests.ForgotPasswordRequest;
import com.itland.employer.requests.PostVacancyRequest;
import com.itland.employer.requests.RegisterRequest;
import com.itland.employer.requests.VerifyAccountRequest;
import com.itland.employer.requests.VerifyChangeEmailRequest;
import com.itland.employer.requests.VerifyChangeGsmRequest;
import com.itland.employer.requests.VerifyForgotPasswordRequest;
import com.itland.employer.responses.AboutUsResponse;
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
import com.itland.employer.util.FragmentNavigator;
import com.itland.employer.util.PrefUtil;
import com.itland.employer.util.SharedPreferencesKeys;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Saad on 5/11/2018.
 */

public abstract class ApiCalls {


    private String TAG = getClass().getSimpleName();
    private Retrofit retrofit ;
    private Apis apis;
    private String authorization;
    private String language;
    private String scope;
    private FragmentNavigator navigator;



    public ApiCalls(FragmentNavigator navigator) {
        LoggingInterceptor interceptor = new LoggingInterceptor();

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://82.137.221.168")
                .client(client)
                .build();

        apis = retrofit.create(Apis.class);

        authorization = PrefUtil.getStringPreference(SharedPreferencesKeys.token);
        language = PrefUtil.getStringPreference(SharedPreferencesKeys.language).equals("")?"en":PrefUtil.getStringPreference(SharedPreferencesKeys.language);
        scope = "Employer";

        this.navigator = navigator;

    }


    private<T> Callback<T> convertCallback(final CallbackWrapped<T> callback)
    {
        showProgress(true);
        Callback<T> cb = new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                showProgress(false);

                if(response.errorBody() != null)
                {
                    if(navigator!=null) toastError(navigator.getActivity().getString(R.string.error_server_issue));
                    callback.onFailure(ErrorMessage.ERROR_MESSAGE);
                    return;
                }

                if(response.body() != null)
                {
                    T body = response.body();

                    try{
                        if(body.getClass().getSimpleName().equals("TokenResponse"))
                        {
                            callback.onResponse(body);//the only response that doesn't extend AbstractEntity
                        }
                        else if(!((AbstractEntity)body).IsOk)
                        {
                            toastError(((AbstractEntity)body).Message.Content);
                            callback.onFailure(ErrorMessage.NOT_OK);
                        }
                        else
                        {
                            callback.onResponse(body);
                        }
                    } catch (Exception ex)
                    {
                        callback.onFailure(ErrorMessage.NOT_OK);
                    }

                }
                else
                {
                    Log.d(TAG, "onResponse: Empty body");
                    callback.onFailure(ErrorMessage.EMPTY_BODY);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {

                if(navigator != null)
                {
                    navigator.gotoSubSection(ReloadFragment.newInstance(navigator));
                }
                callback.onFailure(ErrorMessage.NO_INTERNET);
                Log.d(TAG, "onFailure: "+throwable.getMessage());
                showProgress(false);

            }
        };
        return cb;
    }

    public String uploadFile(Bitmap bitmap, String upLoadServerUri) {
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String attachmentName = "file";
        String attachmentFileName = "bitmap.bmp";
        String crlf = "\r\n";

        try {
            HttpURLConnection httpUrlConnection = null;
            URL url = new URL(upLoadServerUri);
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream request = new DataOutputStream(
                    httpUrlConnection.getOutputStream());
            request.writeBytes(twoHyphens + boundary + crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName + "\";filename=\"" +
                    attachmentFileName + "\"" + lineEnd);
            request.writeBytes("Content-Type: image/png" + lineEnd);
            request.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);
            request.writeBytes(lineEnd);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] pixels = bos.toByteArray();
            request.write(pixels);
            request.writeBytes(lineEnd);
            request.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            InputStream responseStream = new
                    BufferedInputStream(httpUrlConnection.getInputStream());
            BufferedReader responseStreamReader =
                    new BufferedReader(new InputStreamReader(responseStream));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            responseStreamReader.close();
            String response = stringBuilder.toString();
            if(!response.contains("File")) {
                String imageURL = response.substring(0, response.length() - 1);
                return imageURL;
            }
            request.flush();
            request.close();
            responseStream.close();
            httpUrlConnection.disconnect();
            return response;
        } catch (Exception ex) {
            Log.v("File", ex.getMessage());
            ex.printStackTrace();

        } finally {

        }
        return "Error";
    }

    public abstract void showProgress(boolean show);
    public abstract void toastError(String message);

    public void citiesList(CallbackWrapped<CitiesListResponse> callback)
    {
        apis.CitiesList(language,0,10).enqueue(convertCallback(callback));
    }

    public void signIn(String userName, String password,CallbackWrapped<TokenResponse> callback)
    {
        apis.token(language,"password",userName,password,scope).enqueue(convertCallback(callback));
    }

    public void getResumesList(CallbackWrapped<FilterJobSeekerResponse> callback)
    {
        apis.FilterJobSeekers(language,authorization,new FilterJobSeekersRequest()).enqueue(convertCallback(callback));
    }

    public void filterResumes(Integer page,String searchQuery,Integer cityId, Integer minEduLevelId,Integer countryId,
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
        request.Page = page;
        request.ResumeLanguageId = languageId;
        request.WithPhoto = photo;
        apis.FilterJobSeekers(language,authorization,request).enqueue(convertCallback(callback));
    }


    public void getResume(Integer id, CallbackWrapped<ResumeDetails> callback)
    {
        apis.Resume(language,id,authorization).enqueue(convertCallback(callback));
    }

    public void getEducationLevel(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListEducationDegreeLevels(language).enqueue(convertCallback(callback));
    }

    public void getCountries(CallbackWrapped<CountyListResponse> callback)
    {
        apis.CountriesList(language,0,10).enqueue(convertCallback(callback));
    }

    public void getFieldsOfWork(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListFieldsOfWork(language).enqueue(convertCallback(callback));
    }

    public void getJobTypes(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListJobTypes(language).enqueue(convertCallback(callback));
    }

    public void getContactTitlesList(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListTitles(language).enqueue(convertCallback(callback));
    }

    public void getCvLanguage(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListLanguageNames(language).enqueue(convertCallback(callback));
    }

    public void getListMilitaryServices(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListMilitaryServices(language).enqueue(convertCallback(callback));
    }

    public void getListSalary(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListSalaries(language).enqueue(convertCallback(callback));
    }

    public void getListCurrencies(CallbackWrapped<IndicesListResponse> callback)
    {
        apis.ListCurriencies(language).enqueue(convertCallback(callback));
    }

    public void getApplicationsList(Integer page, CallbackWrapped<JobApplicationsListResponse> callback)
    {
        apis.JobApplicationsList(language,page,authorization).enqueue(convertCallback(callback));
    }

    public void getApplicationDetails(Integer id, CallbackWrapped<JobApplicationDetails> callback)
    {
        apis.JobApplications(language,id,authorization).enqueue(convertCallback(callback));
    }

    public void getVacancies(Integer page, CallbackWrapped<MyVacanciesResponse> callback)
    {
        apis.MyVacancies(language,authorization,page).enqueue(convertCallback(callback));
    }

    public void getVacancyDetails(Integer id, CallbackWrapped<VacancyDetails> callback)
    {
        apis.Vacancy(language,id,authorization).enqueue(convertCallback(callback));
    }

    public void viewProfile(CallbackWrapped<CompanyProfile> callback)
    {
        apis.ViewProfile(language,authorization).enqueue(convertCallback(callback));
    }

    public void editProfile(Integer id, String nameEn, String nameAr,String aboutAr,String aboutEn,String addressAr,
                            String addressEn,Integer cityId, Integer industryId, String commercialRegister,
                            Integer pBox, Integer contactTitleId,String contactFirstName, String contactLastName,
                            String contactPosition,String phone,String email, String contactEmail,String contactGsm,
                            String arLogo,String enLogo, CallbackWrapped<GeneralResponse> callback)
    {
        EditCompanyProfileRequest request = new EditCompanyProfileRequest();
        request.Id = id;
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
        request.Phone = phone;
        request.Email = email;
        request.PersonalDetailsEmail = contactEmail;
        request.PersonalDetailsGsm = contactGsm;
        request.PersonalDetailsTitleId = contactTitleId;
        request.PersonalDetailsFirstName = contactFirstName;
        request.PersonalDetailsLastName = contactLastName;
        request.PersonalDetailsPosition = contactPosition;

        request.ArLogoUrl = arLogo;
        request.EnLogoUrl = enLogo;



        apis.EditProfile(language,authorization,request).enqueue(convertCallback(callback));
    }


    public void postJobVacancy(String positionTitle, String jobDescription, String preRequisites,
                               String keySkills, String benefits,String aboutCompany, Integer countyId,
                               Integer cityId, Integer requiredYearsExperience, Integer minEduDegree,
                               Integer fieldOfWork, Integer jobTitle, Integer militaryService, Integer salary,
                               Integer requiredEmployeeNumber, Boolean photo,Integer cvLanguage,
                               Boolean hidCompanyName,CallbackWrapped<VacancyDetails> callback)
    {
        PostVacancyRequest request = new PostVacancyRequest();

        request.Title = positionTitle;
        request.JobDescription = jobDescription;
        request.PreRequisites = preRequisites;
        request.Benefits = benefits;
        request.About = aboutCompany;
        request.SkillsNeeded = keySkills;
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


        apis.PostJobVacancy(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void editActiveVacancy(Integer vacancyId,Integer eduId,Integer cvLang, Integer salaryId, Integer currencyId,
                            Integer fieldOfWorkId, Boolean photo, Boolean hideCompanyName,
                            Boolean isActive,CallbackWrapped<GeneralResponse> callback)
    {
        EditActiveVacancyRequest request = new EditActiveVacancyRequest();
        request.Id = vacancyId;
        request.MinEducationDegreeId = eduId;
        request.FieldOfWorkId = fieldOfWorkId;
        request.IsActive = isActive;
        request.IsNameHidden = hideCompanyName;
        request.PhotoIsRequired = photo;
        request.RequiredCVLanguageId = cvLang;


        apis.EditActiveJobVacancy(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void editInActiveVacancy(String positionTitle, String jobDescription, String preRequisites,
                               String keySkills, String benefits,String aboutCompany, Integer countyId,
                               Integer cityId, Integer requiredYearsExperience, Integer minEduDegree,
                               Integer fieldOfWork, Integer jobTitle, Integer militaryService, Integer salary,
                               Integer requiredEmployeeNumber, Boolean photo,Integer cvLanguage,
                               Boolean hidCompanyName,CallbackWrapped<GeneralResponse> callback)
    {
        EditInactiveVacancyRequest request = new EditInactiveVacancyRequest();

        request.SkillsNeeded = keySkills;
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


        apis.EditInActiveJobVacancy(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void editExpiredVacancy(String positionTitle, String jobDescription, String preRequisites,
                               String keySkills, String benefits,String aboutCompany, Integer countyId,
                               Integer cityId, Integer requiredYearsExperience, Integer minEduDegree,
                               Integer fieldOfWork, Integer jobTitle, Integer militaryService, Integer salary,
                               Integer requiredEmployeeNumber, Boolean photo,Integer cvLanguage,
                               Boolean hidCompanyName,CallbackWrapped<GeneralResponse> callback)
    {
        EditExpiredVacancyRequest request = new EditExpiredVacancyRequest();

        request.SkillsNeeded = keySkills;
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


        apis.EditExpiredJobVacancy(language,authorization,request).enqueue(convertCallback(callback));
    }


    public void getCountyCodes(CallbackWrapped<CountyCodeResponse> callback)
    {
        apis.ListCountryCodes(language).enqueue(convertCallback(callback));
    }

    public void signUp(String countyCode, String gsm, String email, String userName, String password, String firstName, String lastName,CallbackWrapped<RegisterResponse> callback)
    {
        RegisterRequest request = new RegisterRequest();
        request.GsmCountryCode = countyCode;
        request.Gsm = gsm;
        request.Email = email;
        request.Password = password;
        request.ConfirmPassword = password;
        request.FirstName = firstName;
        request.LastName = lastName;
        request.Username = userName;
        request.Scope = scope;


        apis.Register(language,request).enqueue(convertCallback(callback));
    }

    public void changeEmail(String newEmail, String password,CallbackWrapped<GeneralResponse> callback)
    {
        ChangeEmailRequest request = new ChangeEmailRequest();
        request.NewEmail = newEmail;
        request.Password = password;
        apis.ChangeEmail(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void resendCodeChangeEmail(String newEmail, String password,CallbackWrapped<GeneralResponse> callback)
    {
        ChangeEmailRequest request = new ChangeEmailRequest();
        request.NewEmail = newEmail;
        request.Password = password;
        apis.ResendCodeChangeEmail(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void verifyChangeEmail(String email, String code,CallbackWrapped<GeneralResponse> callback)
    {
        VerifyChangeEmailRequest request = new VerifyChangeEmailRequest();
        request.Email = email;
        request.Code = code;
        apis.VerifyChangeEmail(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void changeGsm(String gsmCountyCode,String newGsm, String password,CallbackWrapped<GeneralResponse> callback)
    {
        ChangeGsmRequest request = new ChangeGsmRequest();
        request.NewGsm = newGsm;
        request.NewGsmCountryCode = gsmCountyCode;
        request.Password = password;
        apis.ChangeGsm(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void resendCodeChangeGsm(String gsmCountyCode,String newGsm, String password,CallbackWrapped<GeneralResponse> callback)
    {
        ChangeGsmRequest request = new ChangeGsmRequest();
        request.NewGsm = newGsm;
        request.NewGsmCountryCode = gsmCountyCode;
        request.Password = password;
        apis.ResendCodeChangeGsm(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void verifyChangeGsm(String countyCode,String gsm, String code,CallbackWrapped<GeneralResponse> callback)
    {
        VerifyChangeGsmRequest request = new VerifyChangeGsmRequest();
        request.Gsm = gsm;
        request.Code = code;
        request.GsmCountryCode = countyCode;
        apis.VerifyChangeGsm(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void forgotPassword(String userName, CallbackWrapped<GeneralResponse> callback)
    {
        ForgotPasswordRequest request = new ForgotPasswordRequest();
        request.UserName = userName;
        request.Scope = scope;

        apis.ForegotPassword(language,request).enqueue(convertCallback(callback));

    }

    public void resendCodeForgotPassword(String userName,CallbackWrapped<GeneralResponse> callback)
    {
        ForgotPasswordRequest request = new ForgotPasswordRequest();
        request.UserName = userName;
        request.Scope = scope;

        apis.ResendCodeForgotPassword(language,request).enqueue(convertCallback(callback));

    }

    public void verifyForgotPassword(String userName,String code, String password,CallbackWrapped<GeneralResponse> callback)
    {
        VerifyForgotPasswordRequest request = new VerifyForgotPasswordRequest();
        request.UserName = userName;
        request.Scope = scope;
        request.Code = code;
        request.Password = password;
        request.ConfirmPassword = password;

        apis.ConfirmForgotPassword(language,request).enqueue(convertCallback(callback));

    }

    public void verifyAccount(String code, String userName,CallbackWrapped<GeneralResponse> callback)
    {
        VerifyAccountRequest request = new VerifyAccountRequest();
        request.Code = code;
        request.Username = userName;

        apis.VerifyAccount(language,request).enqueue(convertCallback(callback));
    }
    public void resendCodeRegister(String name,CallbackWrapped<GeneralResponse> callback)
    {
        VerifyAccountRequest request = new VerifyAccountRequest();
        request.Username = name;

        apis.ResendCodeRegister(language,request).enqueue(convertCallback(callback));
    }

    public void changePassword(String oldPassword, String newPassword, CallbackWrapped<GeneralResponse> callback)
    {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.OldPassword = oldPassword;
        request.NewPassword = newPassword;
        request.ConfirmPassword = newPassword;

        apis.ChangePassword(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void signOut(CallbackWrapped<Void> callback)
    {
        apis.Logout(language,authorization).enqueue(convertCallback(callback));
    }

    public void getHomeData(CallbackWrapped<HomeResponse> callback)
    {
        apis.Home(language,authorization).enqueue(convertCallback(callback));
    }

    public void getUserInfo(CallbackWrapped<GeneralResponse> callback)
    {

        apis.UserInfo(language,authorization).enqueue(convertCallback(callback));
    }

    public void getProfileInfo(CallbackWrapped<ProfileInfo> callback)
    {
        apis.ProfileInfo(language,authorization).enqueue(convertCallback(callback));
    }

    public void editProfileInfo(String id,String firstName, String lastName, String email,
                                String countyCode, String phone, String imgUrl, String username,
                                CallbackWrapped<GeneralResponse> callback)
    {
        EditProfileInfoRequest request = new EditProfileInfoRequest();
        request.Id = id;
        request.Email = email;
        request.FirstName = firstName;
        request.LastName = lastName;
        request.GsmCountryCode = countyCode;
        request.PhoneNumber = phone;
        request.Username = username;
        request.ImageURL = imgUrl;
        apis.ChangeProfileInfo(language,authorization,request).enqueue(convertCallback(callback));
    }

    public void getAboutUs(CallbackWrapped<AboutUsResponse> callback)
    {
        apis.AboutUs(language,authorization).enqueue(convertCallback(callback));
    }



}
