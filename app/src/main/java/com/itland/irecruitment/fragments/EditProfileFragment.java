package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.Responses.CitiesListResponse;
import com.itland.irecruitment.Responses.IndicesListResponse;
import com.itland.irecruitment.abstracts.AbstractFragment;
import com.itland.irecruitment.api.CallbackWrapped;
import com.itland.irecruitment.api.ErrorMessage;
import com.itland.irecruitment.entities.City;
import com.itland.irecruitment.entities.Indice;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditProfileFragment extends AbstractFragment {

    @Bind(R.id.txtNameEn) TextView txtNameEn;
    @Bind(R.id.txtNameAr) TextView txtNameAr;
    @Bind(R.id.txtAddressEn) TextView txtAddressEn;
    @Bind(R.id.txtAddressAr) TextView txtAddressAr;
    @Bind(R.id.txtAboutEn) TextView txtAboutEn;
    @Bind(R.id.txtAboutAr) TextView txtAboutAr;
    @Bind(R.id.txtCompanyRegister) TextView txtCommercialRegister;
    @Bind(R.id.txtPBox) TextView txtPBox;
    @Bind(R.id.txtEmail) TextView txtEmail;
    @Bind(R.id.txtPhone) TextView txtPhone;
    @Bind(R.id.txtGsm) TextView txtGsm;
    @Bind(R.id.txtContactName) TextView txtContactName;
    @Bind(R.id.txtContactPosition) TextView txtContactPosition;
    @Bind(R.id.txtContactEmail) TextView txtContactEmail;
    @Bind(R.id.txtContactGSM) TextView txtContactGSM;
    @Bind(R.id.spnCounty) Spinner spnCounty;
    @Bind(R.id.spnCity) Spinner spnCity;
    @Bind(R.id.spnIndustry) Spinner spnIndustry;
    @Bind(R.id.spnContactTitle) Spinner spnContactTitle;
    @Bind(R.id.spnVisibility) Spinner spnVisibility;

    HashMap<String,Indice> name2county = new HashMap<>();
    HashMap<String,City> name2city = new HashMap<>();
    HashMap<String,Indice> name2Industry = new HashMap<>();
    HashMap<String,Indice> name2ContactTitle = new HashMap<>();
    HashMap<String,Indice> name2Visibility = new HashMap<>();

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle("Edit Profile");
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.showSave(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.showSave(false);
    }

    private boolean required(TextView tv)
    {
        if(isNullOrEmpty(tv.getText().toString())) {
            tv.setError(getString(R.string.error_required));
            return false;
        }
        return true;
    }

    private void validateInput()
    {
        required(txtAboutAr);
        required(txtAboutEn);
        required(txtAddressAr);
        required(txtAddressEn);
        required(txtCommercialRegister);
        required(txtContactEmail);
        required(txtContactGSM);
        required(txtContactName);
        required(txtContactPosition);
        required(txtEmail);
        required(txtGsm);
        required(txtNameAr);
        required(txtNameEn);
        required(txtPBox);
        required(txtPhone);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtSave = activity.showSave(true);
        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
                toast("Saved");
            }
        });


        apiCalls.citiesList(new CallbackWrapped<CitiesListResponse>() {
            @Override
            public void onResponse(CitiesListResponse response) {

                List<String> indiceName = new ArrayList<>();
                name2city.clear();

                for(City city : response.Items)
                {
                    if(city.IsActive)
                    {
                        name2city.put(city.Name,city);
                        indiceName.add(city.Name);
                    }
                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnCity.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        apiCalls.getCountries(new CallbackWrapped<IndicesListResponse>() {
            @Override
            public void onResponse(IndicesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2county.clear();

                for(Indice indice : response.Items)
                {
                    name2county.put(indice.Value,indice);
                    indiceName.add(indice.Value);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnCounty.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });


        apiCalls.getContactTitlesList(new CallbackWrapped<IndicesListResponse>() {
            @Override
            public void onResponse(IndicesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2ContactTitle.clear();

                for(Indice indice : response.Items)
                {
                    name2ContactTitle.put(indice.Value,indice);
                    indiceName.add(indice.Value);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnContactTitle.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });





    }
}
