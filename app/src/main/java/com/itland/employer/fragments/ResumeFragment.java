package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.entities.Country;
import com.itland.employer.responses.CitiesListResponse;
import com.itland.employer.responses.CountyListResponse;
import com.itland.employer.responses.FilterJobSeekerResponse;
import com.itland.employer.responses.IndicesListResponse;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.adapters.AutocompleteAdapter;
import com.itland.employer.adapters.ResumeAdapter;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.City;
import com.itland.employer.entities.Indice;
import com.itland.employer.entities.ResumeDetails;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResumeFragment extends AbstractFragment {


    @Bind(R.id.gridView) GridView gridView;
    @Bind(R.id.drawer_layout) public DrawerLayout drawer;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.txtChoose) TextView txtChoose;


    @Bind(R.id.lnrChoose) LinearLayout lnrChoose;
    @Bind(R.id.lnrFilter) LinearLayout lnrFilter;

    @Bind(R.id.txtDoneFilter) TextView txtDoneFilter;
    @Bind(R.id.txtDoneChoose) TextView txtDoneChoose;

    @Bind(R.id.txtCity) TextView txtCity;
    @Bind(R.id.txtCounty) TextView txtCounty;
    @Bind(R.id.txtEdu) TextView txtEdu;
    @Bind(R.id.txtJobType) TextView txtJobType;
    @Bind(R.id.txtFieldOfWork) TextView txtFieldOfWork;
    @Bind(R.id.txtLanguage) TextView txtLanguage;
    @Bind(R.id.txtReset) TextView txtReset;
    @Bind(R.id.switchPhoto) SwitchCompat switchPhoto;

    @Bind(R.id.radioGroupChoose) RadioGroup radioGroup;

    @Bind(R.id.imgBack) ImageView imgBack;

    private enum ChooseType { city,edu,county,fieldOfWork,jobType, cvLanguage }
    private ChooseType chooseType;

    private SearchView searchView;
    private ResumeAdapter adapter;
    private AutocompleteAdapter autocompleteAdapter;



    private City city;
    private Indice eduLevel;
    private Indice county;
    private Indice fieldOfWork;
    private Indice jobType;
    private Indice photo;
    private Indice cvLanguage;
    private String searchQuery;


    public ResumeFragment() {
        // Required empty public constructor
    }

    public static ResumeFragment newInstance() {
        ResumeFragment fragment = new ResumeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resume, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getString(R.string.resumes));
    }

    private void showFilter()
    {
        lnrFilter.setVisibility(View.VISIBLE);
        lnrChoose.setVisibility(View.GONE);
        if(city!=null) txtCity.setText(city.Name);
        if(eduLevel!=null) txtEdu.setText(eduLevel.Value);
        if(county!=null) txtCounty.setText(county.Value);
        if(fieldOfWork!=null) txtFieldOfWork.setText(fieldOfWork.Value);
        if(jobType!=null) txtJobType.setText(jobType.Value);
        if(cvLanguage!=null) txtLanguage.setText(cvLanguage.Value);

    }

    private boolean isFilter()
    {
        return lnrFilter.getVisibility()==View.VISIBLE;
    }

    private void showChoose()
    {
        lnrFilter.setVisibility(View.GONE);
        lnrChoose.setVisibility(View.VISIBLE);
    }

    private void showChoose(CitiesListResponse response)
    {
        lnrFilter.setVisibility(View.GONE);
        lnrChoose.setVisibility(View.VISIBLE);
        chooseType = ChooseType.city;
        txtChoose.setText(R.string.choose_city);

        radioGroup.removeAllViews();
        for (City city:response.getActiveCities())
        {
            RadioButton radioButton = new RadioButton(activity);
            radioButton.setText(city.Name);
            radioButton.setTag(city);
            radioGroup.addView(radioButton);
        }
    }

    private void showChoose(CountyListResponse response)
    {
        lnrFilter.setVisibility(View.GONE);
        lnrChoose.setVisibility(View.VISIBLE);
        chooseType = ChooseType.city;
        txtChoose.setText(R.string.choose_county);

        radioGroup.removeAllViews();
        for (Country city:response.getActiveCounties())
        {
            RadioButton radioButton = new RadioButton(activity);
            radioButton.setText(city.Name);
            radioButton.setTag(city);
            radioGroup.addView(radioButton);
        }
    }

    private void showChoose(IndicesListResponse response,ChooseType type)
    {
        lnrFilter.setVisibility(View.GONE);
        lnrChoose.setVisibility(View.VISIBLE);
        chooseType = type;

        switch (chooseType)
        {
            case city:
                txtChoose.setText(getString(R.string.choose_city));
                break;
            case edu:
                txtChoose.setText(R.string.choose_edu);
                break;
            case county:
                txtChoose.setText(R.string.choose_county);
                break;
            case fieldOfWork:
                txtChoose.setText(R.string.choose_field_of_work);
                break;
            case jobType:
                txtChoose.setText(R.string.choose_job_type);
                break;
            case cvLanguage:
                txtChoose.setText(R.string.choose_cv_language);
                break;
        }


        radioGroup.removeAllViews();

        RadioButton rb = new RadioButton(activity);
        rb.setText(getString(R.string.any));
        rb.setTag(null);
        radioGroup.addView(rb);


        for (Indice indice:response.Items)
        {
            RadioButton radioButton = new RadioButton(activity);
            radioButton.setText(indice.Value);
            radioButton.setTag(indice);
            radioGroup.addView(radioButton);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        activity.actionSearch(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.actionIcon(true).setImageResource(R.drawable.ic_filter);
        activity.actionSearch(true);
    }

    private void filterResumes()
    {
        Integer cityId = city!=null?city.Id:null;
        Integer eduId = eduLevel!=null?eduLevel.Id:null;
        Integer countyId = county!=null?county.Id:null;
        Integer fieldId = fieldOfWork!=null?fieldOfWork.Id:null;
        Integer jobId = jobType!=null?jobType.Id:null;
        Integer cvId = cvLanguage!=null?cvLanguage.Id:null;

        apiCalls.filterResumes(searchQuery, cityId, eduId, countyId, fieldId, jobId, switchPhoto.isChecked(),
                cvId, new CallbackWrapped<FilterJobSeekerResponse>() {
            @Override
            public void onResponse(FilterJobSeekerResponse response) {

                    adapter = new ResumeAdapter(response.Items);
                    gridView.setAdapter(adapter);

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiCalls.getResumesList(new CallbackWrapped<FilterJobSeekerResponse>() {
            @Override
            public void onResponse(FilterJobSeekerResponse response) {
                adapter = new ResumeAdapter(response.Items);
                gridView.setAdapter(adapter);
            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {
                toast(errorMessage);

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                apiCalls.getResume((int) id, new CallbackWrapped<ResumeDetails>() {
                    @Override
                    public void onResponse(ResumeDetails response) {
                        navigator.gotoSubSection(ResumeDetailsFragment.newInstance(response));
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });


        searchView = activity.actionSearch(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterResumes();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuery = newText;
                return false;
            }
        });

        activity.actionIcon(true).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilter();
                drawer.openDrawer(Gravity.END);
            }
        });

        txtDoneFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    drawer.closeDrawer(Gravity.END);

                filterResumes();

            }
        });

        txtDoneChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                if(radioButton != null){
                    switch (chooseType)
                    {
                        case city:
                            city = (City) radioButton.getTag();
                            if(city != null) txtCity.setText(city.Name); else txtCity.setText(getString(R.string.any));
                            break;
                        case edu:
                            eduLevel = (Indice) radioButton.getTag();
                            if(eduLevel != null) txtEdu.setText(eduLevel.Value); else txtEdu.setText(getString(R.string.any));
                            break;
                        case county:
                            county = (Indice) radioButton.getTag();
                            if(county != null) txtCounty.setText(county.Value); else txtCounty.setText(getString(R.string.any));
                            break;
                        case fieldOfWork:
                            fieldOfWork = (Indice) radioButton.getTag();
                            if(fieldOfWork != null) txtFieldOfWork.setText(fieldOfWork.Value); else txtFieldOfWork.setText(getString(R.string.any));
                            break;
                        case jobType:
                            jobType = (Indice) radioButton.getTag();
                            if(jobType != null) txtJobType.setText(jobType.Value); else txtJobType.setText(getString(R.string.any));
                            break;
                        case cvLanguage:
                            cvLanguage = (Indice) radioButton.getTag();
                            if(cvLanguage != null) txtLanguage.setText(cvLanguage.Value); else txtLanguage.setText(getString(R.string.any));
                            break;
                    }

                }

                showFilter();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilter();
            }
        });


        txtCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.citiesList(new CallbackWrapped<CitiesListResponse>() {
                    @Override
                    public void onResponse(CitiesListResponse response) {
                        showChoose(response);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
                showChoose();
            }
        });

        txtEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.getEducationLevel(new CallbackWrapped<IndicesListResponse>() {
                    @Override
                    public void onResponse(IndicesListResponse response) {
                        showChoose(response,ChooseType.edu);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });

        txtCounty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.getCountries(new CallbackWrapped<CountyListResponse>() {
                    @Override
                    public void onResponse(CountyListResponse response) {
                        showChoose(response);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });

        txtFieldOfWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.getFieldsOfWork(new CallbackWrapped<IndicesListResponse>() {
                    @Override
                    public void onResponse(IndicesListResponse response) {
                        showChoose(response,ChooseType.fieldOfWork);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });

        txtJobType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.getJobTypes(new CallbackWrapped<IndicesListResponse>() {
                    @Override
                    public void onResponse(IndicesListResponse response) {
                        showChoose(response,ChooseType.jobType);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });

        txtLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.getCvLanguage(new CallbackWrapped<IndicesListResponse>() {
                    @Override
                    public void onResponse(IndicesListResponse response) {
                        showChoose(response,ChooseType.cvLanguage);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });

        txtReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCity.setText(R.string.any);
                txtEdu.setText(R.string.any);
                txtCounty.setText(R.string.any);
                txtFieldOfWork.setText(R.string.any);
                txtJobType.setText(R.string.any);
                txtLanguage.setText(R.string.any);
                switchPhoto.setChecked(false);

                city = null;
                eduLevel = null;
                county = null;
                fieldOfWork = null;
                jobType = null;
                cvLanguage = null;

            }
        });
    }

}
