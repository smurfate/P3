package com.itland.employer.registration.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.abstracts.AbstractResistrationFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.responses.GeneralResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VerifyFragment extends AbstractResistrationFragment {

    @Bind(R.id.txtCode) EditText txtCode;
    @Bind(R.id.txtResend) TextView txtResend;
    @Bind(R.id.btnVerify) Button btnVerify;
    @Bind(R.id.txtUserName) EditText txtUsername;

    private String username;

    public VerifyFragment() {
        // Required empty public constructor
    }

    public static VerifyFragment newInstance() {
        VerifyFragment fragment = new VerifyFragment();
        return fragment;
    }

    public static VerifyFragment newInstance(String username) {
        VerifyFragment fragment = new VerifyFragment();
        fragment.username = username;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    private boolean validateInputs()
    {
        return required(txtUsername)&&
                required(txtCode);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(username!=null)
        {
            txtUsername.setText(username);
            txtUsername.setEnabled(false);
        }

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateInputs()) return;

                String code = txtCode.getText().toString();
                String username = txtUsername.getText().toString();

                activity.apiCalls.verifyAccount(code, username, new CallbackWrapped<GeneralResponse>() {
                    @Override
                    public void onResponse(GeneralResponse response) {
                        toast(response);
                        navigator.goBackTo(SignInFragment.class);
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

                if(!required(txtUsername)) return;

                String username = txtUsername.getText().toString();

                activity.apiCalls.resendCodeRegister(username, new CallbackWrapped<GeneralResponse>() {
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
