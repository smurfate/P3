package com.itland.employer.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractActivity;
import com.itland.employer.api.FileUploader;
import com.itland.employer.entities.CompanyProfile;
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
import com.itland.employer.responses.ProfileInfoResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends AbstractFragment {

    @Bind(R.id.txtNameEn) EditText txtNameEn;
    @Bind(R.id.txtNameAr) EditText txtNameAr;
    @Bind(R.id.txtAddressEn) EditText txtAddressEn;
    @Bind(R.id.txtAddressAr) EditText txtAddressAr;
    @Bind(R.id.txtAboutEn) EditText txtAboutEn;
    @Bind(R.id.txtAboutAr) EditText txtAboutAr;
    @Bind(R.id.txtCompanyRegister) EditText txtCommercialRegister;
    @Bind(R.id.txtPBox) EditText txtPBox;
    @Bind(R.id.txtEmail) EditText txtEmail;
    @Bind(R.id.txtPhone) EditText txtPhone;
    @Bind(R.id.txtGsm) EditText txtGsm;
    @Bind(R.id.txtContactFirstName) EditText txtContactFirstName;
    @Bind(R.id.txtContactLastName) EditText txtContactLastName;
    @Bind(R.id.txtContactPosition) EditText txtContactPosition;
    @Bind(R.id.txtContactEmail) EditText txtContactEmail;
    @Bind(R.id.txtContactGSM) EditText txtContactGSM;
    @Bind(R.id.spnCounty) Spinner spnCounty;
    @Bind(R.id.spnCity) Spinner spnCity;
    @Bind(R.id.spnIndustry) Spinner spnIndustry;
    @Bind(R.id.spnContactTitle) Spinner spnContactTitle;
    @Bind(R.id.switchVisible) SwitchCompat switchCompat;
    @Bind(R.id.imgLogoAr) CircleImageView imgLogoAr;
    @Bind(R.id.imgLogoEn) CircleImageView imgLogoEn;

    HashMap<String,Country> name2county = new HashMap<>();
    HashMap<String,City> name2city = new HashMap<>();
    HashMap<String,Indice> name2Industry = new HashMap<>();
    HashMap<String,Indice> name2ContactTitle = new HashMap<>();

    private CompanyProfile profile;
    private int PICK_IMAGE = 22;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance(CompanyProfile profile) {
        EditProfileFragment fragment = new EditProfileFragment();
        fragment.profile = profile;
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
        setTitle(getString(R.string.edit_profile));
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.actionText(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.actionText(false);
    }


    private boolean validateInput()
    {
        return required(txtAboutAr)&&
        required(txtAboutEn)&&
        required(txtAddressAr)&&
        required(txtAddressEn)&&
        required(txtCommercialRegister)&&
        required(txtContactEmail)&&
        required(txtContactGSM)&&
        required(txtContactFirstName)&&
        required(txtContactLastName)&&
        required(txtContactPosition)&&
        required(txtEmail)&&
        required(txtGsm)&&
        required(txtNameAr)&&
        required(txtNameEn)&&
        required(txtPBox)&&
        required(spnCity)&&
        required(spnCounty)&&
        required(spnContactTitle)&&
        required(spnIndustry)&&
        required(txtPhone);
    }

    private void initiateSpinners()
    {
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

                    for (int i=0;i<adapter.getCount();i++)
                    {
                        if(adapter.getItem(i).toString().equals(profile.City)) spnCity.setSelection(i);
                    }

                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        apiCalls.getCountries(new CallbackWrapped<CountyListResponse>() {
            @Override
            public void onResponse(CountyListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2county.clear();

                for(Country indice : response.Items)
                {
                    name2county.put(indice.Name,indice);
                    indiceName.add(indice.Name);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnCounty.setAdapter(adapter);

                    for (int i=0;i<adapter.getCount();i++)
                    {
                        if(adapter.getItem(i).toString().equals(profile.Country)) spnCounty.setSelection(i);
                    }

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

        apiCalls.getFieldsOfWork(new CallbackWrapped<IndicesListResponse>() {
            @Override
            public void onResponse(IndicesListResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2Industry.clear();

                for(Indice indice : response.Items)
                {
                    name2Industry.put(indice.Value,indice);
                    indiceName.add(indice.Value);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnIndustry.setAdapter(adapter);

                    for (int i=0;i<adapter.getCount();i++)
                    {
                        if(adapter.getItem(i).toString().equals(profile.Industry)) spnIndustry.setSelection(i);
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

        txtNameEn.setText(profile.EnName);
        txtNameAr.setText(profile.ArName);
        txtAddressEn.setText(profile.EnAddress);
        txtAddressAr.setText(profile.ArAddress);
        txtAboutEn.setText(profile.EnAbout);
        txtAboutAr.setText(profile.ArAbout);
        txtCommercialRegister.setText(profile.CommercialRegister);
        txtPBox.setText(profile.POBox.toString());
        txtEmail.setText(profile.Email);
        txtPhone.setText(profile.Phone);
        txtGsm.setText(profile.Phone);
        txtContactFirstName.setText(profile.PersonalDetailsFullName);
        txtContactLastName.setText(profile.PersonalDetailsFullName);
        txtContactPosition.setText(profile.PersonalDetailsposition);
        txtContactEmail.setText(profile.PersonalDetailsEmail);
        txtContactGSM.setText(profile.PersonalDetailsGsm);


        initiateSpinners();

        imgLogoAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.pickImage(new AbstractActivity.OnImagePicked() {
                    @Override
                    public void onImagePicked(Bitmap bitmap) {
                        imgLogoAr.setImageBitmap(bitmap);
                        imgLogoAr.setTag(bitmap);
                    }
                });
            }
        });

        imgLogoEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.pickImage(new AbstractActivity.OnImagePicked() {
                    @Override
                    public void onImagePicked(Bitmap bitmap) {
                        imgLogoEn.setImageBitmap(bitmap);
                        imgLogoEn.setTag(bitmap);
                    }
                });
            }
        });


        TextView txtSave = activity.actionText(true);
        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateInput())return;
                String aboutAr = txtAboutAr.getText().toString();
                String aboutEn = txtAboutEn.getText().toString();
                String addressAr = txtAddressAr.getText().toString();
                String addressEn = txtAddressEn.getText().toString();
                String nameAr = txtNameAr.getText().toString();
                String nameEn = txtNameEn.getText().toString();
                String commercialRegister = txtCommercialRegister.getText().toString();
                String contactFirstName = txtContactFirstName.getText().toString();
                String contactLastName = txtContactLastName.getText().toString();

                String contactEmail = txtContactEmail.getText().toString();
                String contactGsm = txtContactGSM.getText().toString();
                String contactPosition = txtContactPosition.getText().toString();
                String emial = txtEmail.getText().toString();//seperated
                String gsm = txtGsm.getText().toString();//seperated
                String pbox = txtPBox.getText().toString();
                String phone = txtPhone.getText().toString();

                Integer countyId = name2county.get(spnCounty.getSelectedItem().toString()).Id;
                Integer cityId = name2city.get(spnCity.getSelectedItem().toString()).Id;
                Integer industryId = name2Industry.get(spnIndustry.getSelectedItem().toString()).Id;
                Integer titleId = name2ContactTitle.get(spnContactTitle.getSelectedItem().toString()).Id;
                boolean visibility = switchCompat.isChecked();

                apiCalls.editProfile(profile.Id,nameEn, nameAr, aboutAr, aboutEn, addressAr, addressEn, cityId, industryId, commercialRegister, Integer.parseInt(pbox), titleId, contactFirstName, contactLastName, contactPosition, new CallbackWrapped<GeneralResponse>() {
                    @Override
                    public void onResponse(GeneralResponse response) {
                        toast(response);
                        navigator.goBackTo(ProfileFragment.class);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });

            }
        });

    }

}
