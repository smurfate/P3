package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.entities.CompanyProfile;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileFragment extends AbstractFragment {


    @Bind(R.id.txtCompanyName) TextView txtCompanyName;
    @Bind(R.id.txtCompanyName2) TextView txtCompanyName2;
    @Bind(R.id.txtCityCounty) TextView txtCityCounty;
    @Bind(R.id.txtAddress) TextView txtAddress;
    @Bind(R.id.txtIndustry) TextView txtCompanyIndustry;
    @Bind(R.id.txtAbout) TextView txtAbout;
    @Bind(R.id.txtCommercialRegister) TextView txtCommercialRegister;
    @Bind(R.id.txtPBox) TextView txtPBox;
    @Bind(R.id.txtEmail) TextView txtEmail;
    @Bind(R.id.txtPhone) TextView txtPhone;
    @Bind(R.id.txtContactName) TextView txtContactName;
    @Bind(R.id.txtContactEmail) TextView txtContactEmail;
    @Bind(R.id.txtContactGSM) TextView txtContactGSM;
    @Bind(R.id.txtContactPosition) TextView txtContactPosition;

    @Bind(R.id.txtCompanyNameAr) TextView txtCompanyNameAr;
    @Bind(R.id.txtCityCountyAr) TextView txtCityCountyAr;
    @Bind(R.id.txtAddressAr) TextView txtAddressAr;
    @Bind(R.id.txtIndustryAr) TextView txtCompanyIndustryAr;
    @Bind(R.id.txtAboutAr) TextView txtAboutAr;
    @Bind(R.id.txtContactNameAr) TextView txtContactNameAr;
    @Bind(R.id.txtContactPositionAr) TextView txtContactPositionAr;

    @Bind(R.id.btnEdit) ImageButton btnEdit;
    @Bind(R.id.imgEditEmail) ImageView imgEditEmail;
    @Bind(R.id.imgEditGsm) ImageView imgEditGsm;

    private CompanyProfile profile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(CompanyProfile profile) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.profile = profile;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtCompanyName.setText(profile.EnName);
        txtCompanyName2.setText(profile.ArName);
        txtCityCounty.setText(profile.City +", "+profile.Country);
        txtAddress.setText(profile.EnAddress);
        txtCompanyIndustry.setText(profile.Industry);
        txtAbout.setText(profile.EnAbout);
        txtCommercialRegister.setText(profile.CommercialRegister);
        txtPBox.setText(profile.POBox.toString());
        txtEmail.setText(profile.Email);
        txtPhone.setText(profile.Phone);
        txtContactName.setText(profile.PersonalDetailsFullName);
        txtContactPosition.setText(profile.PersonalDetailsposition);
        txtContactEmail.setText(profile.PersonalDetailsEmail);
        txtContactGSM.setText(profile.PersonalDetailsGsm);

        txtCompanyNameAr.setText(profile.ArName);
        txtCityCountyAr.setText(profile.City +", "+profile.Country);
        txtAddressAr.setText(profile.ArAddress);
        txtCompanyIndustryAr.setText(profile.Industry);
        txtAboutAr.setText(profile.ArAbout);
        txtContactNameAr.setText(profile.PersonalDetailsFullName);
        txtContactPositionAr.setText(profile.PersonalDetailsposition);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(EditProfileFragment.newInstance());
            }
        });

        imgEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(ChangeEmailFragment.newInstance());
            }
        });

        imgEditGsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(ChangeGsmFragment.newInstance());
            }
        });
    }

}
