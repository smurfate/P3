package com.itland.employer.fragments;


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
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.responses.GeneralResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChangeEmailFragment extends AbstractFragment {


    @Bind(R.id.txtEmail) EditText txtEmail;
    @Bind(R.id.txtPassword) EditText txtPassword;
    @Bind(R.id.btnSave) Button btnSave;

    public ChangeEmailFragment() {
        // Required empty public constructor
    }

    public static ChangeEmailFragment newInstance() {
        ChangeEmailFragment fragment = new ChangeEmailFragment();
        return fragment;
    }

    private boolean validateInputs()
    {
        return required(txtEmail)&&
                required(txtPassword);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_email, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateInputs()) return;

                String password = txtPassword.getText().toString();
                String email = txtEmail.getText().toString();
                apiCalls.changeEmail(email, password, new CallbackWrapped<GeneralResponse>() {
                    @Override
                    public void onResponse(GeneralResponse response) {
                        toast(response);
                        navigator.gotoSubSection(VerifyChangeEmailFragment.newInstance());
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });
    }
}
