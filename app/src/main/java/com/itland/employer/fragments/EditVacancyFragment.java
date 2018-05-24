package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.responses.GeneralResponse;
import com.itland.employer.responses.IndicesListResponse;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.Indice;
import com.itland.employer.entities.VacancyDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditVacancyFragment extends AbstractFragment {

    @Bind(R.id.spnMinEduDegree) Spinner spnMinEduDegree;
    @Bind(R.id.spnFieldOfWork) Spinner spnFieldOfWork;
    @Bind(R.id.spnSalary) Spinner spnSalary;
    @Bind(R.id.switchHide) SwitchCompat switchHideCompanyName;
    @Bind(R.id.spnCvLanguage) Spinner spnCvLanguage;
    @Bind(R.id.switchPhoto) SwitchCompat switchPhoto;
    @Bind(R.id.switchIsActive) SwitchCompat switchIsActive;

    private HashMap<String,Indice> name2edu = new HashMap<>();
    private HashMap<String,Indice> name2field = new HashMap<>();
    private HashMap<String,Indice> name2salary = new HashMap<>();
    private HashMap<String,Indice> name2cvLang = new HashMap<>();

    private VacancyDetails vacancy;

    public EditVacancyFragment() {
        // Required empty public constructor
    }

    public static EditVacancyFragment newInstance(VacancyDetails vacancy) {
        EditVacancyFragment fragment = new EditVacancyFragment();
        fragment.vacancy = vacancy;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_vacancy, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getString(R.string.edit_vacancy));
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.actionText(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.actionText(true);
    }

    private boolean validateInput()
    {
        return required(spnMinEduDegree)&&
                required(spnFieldOfWork)&&
                required(spnSalary)&&
                required(spnCvLanguage);
    }

    private void initSpinners()
    {
//        apiCalls.getListCurrencies(new CallbackWrapped<IndicesListResponse>() {
//            @Override
//            public void onResponse(IndicesListResponse response) {
//                List<String> indiceName = new ArrayList<>();
//                name2currency.clear();
//
//                for(Indice indice : response.Items)
//                {
//                    name2currency.put(indice.Value,indice);
//                    indiceName.add(indice.Value);
//
//                }
//                if(isSafe())
//                {
//                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
//                    spnCurrency.setAdapter(adapter);
//
//                    for (int i=0;i<adapter.getCount();i++)
//                    {
//                        if(adapter.getItem(i).toString().equals(vacancy.Currency)) spnCurrency.setSelection(i);
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(ErrorMessage errorMessage) {
//
//            }
//        });

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

                    for (int i=0;i<adapter.getCount();i++)
                    {
                        if(adapter.getItem(i).toString().equals(vacancy.MinRequiredEducationLevel)) spnMinEduDegree.setSelection(i);
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

                    for (int i=0;i<adapter.getCount();i++)
                    {
                        if(adapter.getItem(i).toString().equals(vacancy.FieldOfWork)) spnFieldOfWork.setSelection(i);
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

                    for (int i=0;i<adapter.getCount();i++)
                    {
                        if(adapter.getItem(i).toString().equals(vacancy.Salary)) spnSalary.setSelection(i);
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

                    for (int i=0;i<adapter.getCount();i++)
                    {
                        if(adapter.getItem(i).toString().equals(vacancy.RequestedCVLanguage)) spnCvLanguage.setSelection(i);
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

                if(!validateInput()) return;

                Integer eduId = name2edu.get(spnMinEduDegree.getSelectedItem().toString()).Id;
                Integer cvLangId = name2cvLang.get(spnCvLanguage.getSelectedItem().toString()).Id;
                Integer salaryId = name2salary.get(spnSalary.getSelectedItem().toString()).Id;
                Integer fieldId = name2field.get(spnFieldOfWork.getSelectedItem().toString()).Id;


                apiCalls.editActiveVacancy(vacancy.Id,eduId, cvLangId, salaryId, 0, fieldId, switchPhoto.isChecked(), switchHideCompanyName.isChecked(), switchIsActive.isChecked(), new CallbackWrapped<GeneralResponse>() {
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
        });

    }
}
