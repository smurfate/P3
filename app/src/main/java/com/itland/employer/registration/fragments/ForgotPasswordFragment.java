package com.itland.employer.registration.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractResistrationFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.responses.GeneralResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgotPasswordFragment extends AbstractResistrationFragment {

    @Bind(R.id.txtGsm) EditText txtGsm;
    @Bind(R.id.txtCode) EditText txtCode;
    @Bind(R.id.txtPassword) EditText txtPassword;
    @Bind(R.id.txtConfirmPassword) EditText txtConfirmPassword;
    @Bind(R.id.txtResend) TextView txtResend;
    @Bind(R.id.btnVerify) Button btnVerify;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    public static ForgotPasswordFragment newInstance() {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    private boolean validateInputs()
    {
        return required(txtGsm)&&
                required(txtCode)&&
                required(txtPassword)&&
                required(txtConfirmPassword);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity.apiCalls.forgotPassword("", "", new CallbackWrapped<GeneralResponse>() {
            @Override
            public void onResponse(GeneralResponse response) {
                toast(response);
            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });



        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateInputs()) return;

                if(txtPassword.getText().equals(txtConfirmPassword.getText()))
                {
                    txtConfirmPassword.setError("Password does not match");
                    txtConfirmPassword.requestFocus();
                    return;
                }

                String code = txtCode.getText().toString();
                String password = txtPassword.getText().toString();
                String gsm = txtGsm.getText().toString();

                activity.apiCalls.verifyForgotPassword("", "", code, password, new CallbackWrapped<GeneralResponse>() {
                    @Override
                    public void onResponse(GeneralResponse response) {
                        toast(response);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });

            }
        });

        txtResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.apiCalls.resendCodeForgotPassword("", "", new CallbackWrapped<GeneralResponse>() {
                    @Override
                    public void onResponse(GeneralResponse response) {
                        toast(response);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });
    }
}
