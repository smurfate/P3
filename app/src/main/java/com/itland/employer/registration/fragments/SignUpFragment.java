package com.itland.employer.registration.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpFragment extends AbstractFragment {


    @Bind(R.id.spnCountyCode) Spinner spnCountryCode;
    @Bind(R.id.txtGsmNumber) TextView txtGsm;
    @Bind(R.id.txtEmail) TextView txtEmail;
    @Bind(R.id.txtUserName) TextView txtUserName;
    @Bind(R.id.txtPassword) TextView txtPassword;
    @Bind(R.id.txtConfirmPassword) TextView txtConfirmPassword;
    @Bind(R.id.txtFirstName) TextView txtFirstName;
    @Bind(R.id.txtLastName) TextView txtLastName;
    @Bind(R.id.chkAgree) CheckBox chkAgree;
    @Bind(R.id.btnSignup) Button btnSignup;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
