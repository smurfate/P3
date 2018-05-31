package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.entities.Country;
import com.itland.employer.responses.CitiesListResponse;
import com.itland.employer.responses.CountyListResponse;
import com.itland.employer.responses.GeneralResponse;
import com.itland.employer.responses.IndicesListResponse;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.City;
import com.itland.employer.entities.Indice;
import com.itland.employer.entities.VacancyDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostVacancyFragment extends AbstractFragment {

    @Bind(R.id.txtPositionTitle) EditText txtPositionTitle;
    @Bind(R.id.txtJobDescription) EditText txtJobDescription;
    @Bind(R.id.txtPreRequirement) EditText txtPreRequirements;
    @Bind(R.id.txtSkillsNeeded) EditText txtSkillsNeeded;
    @Bind(R.id.txtBenefits) EditText txtBenefits;
    @Bind(R.id.txtAboutCompany) EditText txtAboutCompany;
    @Bind(R.id.txtExperience) EditText txtExperience;
    @Bind(R.id.txtRequiredNumber) EditText txtNumber;


//    @Bind(R.id.spnCounty) Spinner spnCounty;
    @Bind(R.id.spnCity) Spinner spnCity;
    @Bind(R.id.spnMinEduDegree) Spinner spnMinEduDegree;
    @Bind(R.id.spnFieldOfWork) Spinner spnFieldOfWork;
    @Bind(R.id.spnJobTitle) Spinner spnJobTitle;
    @Bind(R.id.spnMilitaryService) Spinner spnMilitaryService;
    @Bind(R.id.spnSalary) Spinner spnSalary;
    @Bind(R.id.switchHide) SwitchCompat switchHideCompanyName;
    @Bind(R.id.spnCvLanguage) Spinner spnCvLanguage;
    @Bind(R.id.switchPhoto) SwitchCompat switchPhoto;

    private HashMap<String,Country> name2county = new HashMap<>();
    private HashMap<String,City> name2city = new HashMap<>();
    private HashMap<String,Indice> name2edu = new HashMap<>();
    private HashMap<String,Indice> name2field = new HashMap<>();
    private HashMap<String,Indice> name2title = new HashMap<>();
    private HashMap<String,Indice> name2military = new HashMap<>();
    private HashMap<String,Indice> name2salary = new HashMap<>();
    private HashMap<String,Indice> name2cvLang = new HashMap<>();

    private VacancyDetails details;

    public PostVacancyFragment() {
        // Required empty public constructor
    }

    public static PostVacancyFragment newInstance() {
        PostVacancyFragment fragment = new PostVacancyFragment();
        return fragment;
    }

    public static PostVacancyFragment newInstance(VacancyDetails details) {
        PostVacancyFragment fragment = new PostVacancyFragment();
        fragment.details = details;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_vacanty, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    private boolean isEdit()
    {
        return details != null;
    }

    @Override
    public void onStart() {
        super.onStart();
        activity.actionText(true);
        if(isEdit()) setTitle(getString(R.string.edit_vacancy));
        else setTitle(getString(R.string.post_vacancy));
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.actionText(false);
    }


    private boolean validateInputs()
    {
        return required(txtPositionTitle)&&
                required(txtExperience)&&
                required(txtJobDescription)&&
                required(txtPreRequirements)&&
                required(txtSkillsNeeded)&&
                required(txtBenefits)&&
                required(txtNumber)&&
//                required(spnCounty)&&
                required(spnCity)&&
                required(spnMinEduDegree)&&
                required(spnFieldOfWork)&&
                required(spnJobTitle)&&
                required(spnMilitaryService)&&
                required(spnSalary)&&
                required(spnCvLanguage)&&
                required(txtAboutCompany);
    }

    private void initSpinners()
    {
//        apiCalls.getCountries(new CallbackWrapped<CountyListResponse>() {
//            @Override
//            public void onResponse(CountyListResponse response) {
//                List<String> indiceName = new ArrayList<>();
//                name2county.clear();
//
//                for(Country indice : response.Items)
//                {
//                    name2county.put(indice.Name,indice);
//                    indiceName.add(indice.Name);
//
//                }
//                if(isSafe())
//                {
//                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
//                    spnCounty.setAdapter(adapter);
//
//                    if(isEdit())
//                    {
//                        for (int i=0;i<adapter.getCount();i++)
//                        {
//                            if(adapter.getItem(i).toString().equals(details.WorkCountry)) spnCounty.setSelection(i);
//                        }
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(ErrorMessage errorMessage) {
//
//            }
//        });

        apiCalls.citiesList(new CallbackWrapped<CitiesListResponse>() {
            @Override
            public void onResponse(CitiesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2city.clear();

                for(City indice : response.Items)
                {
                    name2city.put(indice.Name,indice);
                    indiceName.add(indice.Name);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnCity.setAdapter(adapter);

                    if(isEdit())
                    {
                        for (int i=0;i<adapter.getCount();i++)
                        {
                            if(adapter.getItem(i).toString().equals(details.WorkCity)) spnCity.setSelection(i);
                        }
                    }

                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        apiCalls.getEducationLevel(new CallbackWrapped<IndicesListResponse>() {
            @Override
            public void onResponse(IndicesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2edu.clear();

                for(Indice indice : response.Items)
                {
                    name2edu.put(indice.Value,indice);
                    indiceName.add(indice.Value);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnMinEduDegree.setAdapter(adapter);

                    if(isEdit())
                    {
                        for (int i=0;i<adapter.getCount();i++)
                        {
                            if(adapter.getItem(i).toString().equals(details.MinRequiredEducationLevel)) spnMinEduDegree.setSelection(i);
                        }
                    }

                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        apiCalls.getFieldsOfWork(new CallbackWrapped<IndicesListResponse>() {
            @Override
            public void onResponse(IndicesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2field.clear();

                for(Indice indice : response.Items)
                {
                    name2field.put(indice.Value,indice);
                    indiceName.add(indice.Value);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnFieldOfWork.setAdapter(adapter);

                    if(isEdit())
                    {
                        for (int i=0;i<adapter.getCount();i++)
                        {
                            if(adapter.getItem(i).toString().equals(details.FieldOfWork)) spnFieldOfWork.setSelection(i);
                        }
                    }

                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        apiCalls.getJobTypes(new CallbackWrapped<IndicesListResponse>() {
            @Override
            public void onResponse(IndicesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2title.clear();

                for(Indice indice : response.Items)
                {
                    name2title.put(indice.Value,indice);
                    indiceName.add(indice.Value);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnJobTitle.setAdapter(adapter);

                    if(isEdit())
                    {
                        for (int i=0;i<adapter.getCount();i++)
                        {
                            if(adapter.getItem(i).toString().equals(details.Title)) spnJobTitle.setSelection(i);
                        }
                    }

                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        apiCalls.getListMilitaryServices(new CallbackWrapped<IndicesListResponse>() {
            @Override
            public void onResponse(IndicesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2military.clear();

                for(Indice indice : response.Items)
                {
                    name2military.put(indice.Value,indice);
                    indiceName.add(indice.Value);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnMilitaryService.setAdapter(adapter);

                    if(isEdit())
                    {
                        for (int i=0;i<adapter.getCount();i++)
                        {
                            if(adapter.getItem(i).toString().equals(details.MilitaryService)) spnMilitaryService.setSelection(i);
                        }
                    }

                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        apiCalls.getListSalary(new CallbackWrapped<IndicesListResponse>() {
            @Override
            public void onResponse(IndicesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2salary.clear();

                for(Indice indice : response.Items)
                {
                    name2salary.put(indice.Value,indice);
                    indiceName.add(indice.Value);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnSalary.setAdapter(adapter);

                    if(isEdit())
                    {
                        for (int i=0;i<adapter.getCount();i++)
                        {
                            if(adapter.getItem(i).toString().equals(details.Salary.toString())) spnSalary.setSelection(i);
                        }
                    }

                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        apiCalls.getCvLanguage(new CallbackWrapped<IndicesListResponse>() {
            @Override
            public void onResponse(IndicesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2cvLang.clear();

                for(Indice indice : response.Items)
                {
                    name2cvLang.put(indice.Value,indice);
                    indiceName.add(indice.Value);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnCvLanguage.setAdapter(adapter);

                    if(isEdit())
                    {
                        for (int i=0;i<adapter.getCount();i++)
                        {
                            if(adapter.getItem(i).toString().equals(details.RequestedCVLanguage)) spnCvLanguage.setSelection(i);
                        }
                    }

                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initSpinners();




        TextView txtSave = activity.actionText(true);
        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateInputs()) return;

                String positionTitle = txtPositionTitle.getText().toString();
                String jobDescription = txtJobDescription.getText().toString();
                String preRequisits = txtPreRequirements.getText().toString();
                String skillsNeeded = txtSkillsNeeded.getText().toString();
                String benefits = txtBenefits.getText().toString();
                String about = txtAboutCompany.getText().toString();
                Integer experience = Integer.parseInt(txtExperience.getText().toString());

//                Integer countyId = name2county.get(spnCounty.getSelectedItem().toString()).Id;
                Integer cityId = name2city.get(spnCity.getSelectedItem().toString()).Id;
                Integer eduId = name2edu.get(spnMinEduDegree.getSelectedItem().toString()).Id;
                Integer fieldId = name2field.get(spnFieldOfWork.getSelectedItem().toString()).Id;
                Integer titleId = name2title.get(spnJobTitle.getSelectedItem().toString()).Id;
                Integer militaryId = name2military.get(spnMilitaryService.getSelectedItem().toString()).Id;
                Integer salaryId = name2salary.get(spnSalary.getSelectedItem().toString()).Id;
                Integer employeeNumber = Integer.parseInt(txtNumber.getText().toString());
                Integer cvLangId = name2cvLang.get(spnCvLanguage.getSelectedItem().toString()).Id;


                if(!isEdit())
                {
                    apiCalls.postJobVacancy(positionTitle, jobDescription, preRequisits, skillsNeeded,
                            benefits, about, 0, cityId, experience, eduId, fieldId, titleId, militaryId,
                            salaryId, employeeNumber, switchPhoto.isChecked(), cvLangId,
                            switchHideCompanyName.isChecked(), new CallbackWrapped<VacancyDetails>() {
                                @Override
                                public void onResponse(VacancyDetails response) {
                                    toast(response);
                                    navigator.goBack();
                                    navigator.gotoSubSection(VacancyDetailsFragment.newInstance(response));
                                }

                                @Override
                                public void onFailure(ErrorMessage errorMessage) {

                                }
                            });

                }
                else
                {
                    if(details.isExpired())
                    {
                        apiCalls.editExpiredVacancy(positionTitle, jobDescription, preRequisits, skillsNeeded,
                                benefits, about, 0, cityId, experience, eduId, fieldId, titleId, militaryId,
                                salaryId, employeeNumber, switchPhoto.isChecked(), cvLangId,
                                switchHideCompanyName.isChecked(), new CallbackWrapped<GeneralResponse>() {
                                    @Override
                                    public void onResponse(GeneralResponse response) {
                                        toast(response);
                                        navigator.goBackTo(VacanciesFragment.class);

                                    }

                                    @Override
                                    public void onFailure(ErrorMessage errorMessage) {

                                    }
                                });

                    }else if(details.isInactive())
                    {
                        apiCalls.editInActiveVacancy(positionTitle, jobDescription, preRequisits, skillsNeeded,
                                benefits, about, 0, cityId, experience, eduId, fieldId, titleId, militaryId,
                                salaryId, employeeNumber, switchPhoto.isChecked(), cvLangId,
                                switchHideCompanyName.isChecked(), new CallbackWrapped<GeneralResponse>() {
                                    @Override
                                    public void onResponse(GeneralResponse response) {
                                        toast(response);
                                        navigator.goBackTo(VacanciesFragment.class);

                                    }

                                    @Override
                                    public void onFailure(ErrorMessage errorMessage) {

                                    }
                                });

                    }
                }

            }
        });
    }
}
