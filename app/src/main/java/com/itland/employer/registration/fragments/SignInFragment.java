package com.itland.employer.registration.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itland.employer.MainActivity;
import com.itland.employer.R;
import com.itland.employer.responses.TokenResponse;
import com.itland.employer.abstracts.AbstractResistrationFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.util.PrefUtil;
import com.itland.employer.util.SharedPreferencesKeys;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInFragment extends AbstractResistrationFragment {

    @Bind(R.id.btnSignIn) ImageView btnSignIn;
    @Bind(R.id.btnSignUp) Button btnSignUp;
    @Bind(R.id.txtUserName) EditText txtUserName;
    @Bind(R.id.txtPassword) EditText txtPassword;

    @Bind(R.id.txtForgotPassword) TextView txtForgotPassword;
    @Bind(R.id.txtVerifyAccount) TextView txtVerifyAccount;


    @Bind(R.id.pb_loading) ProgressBar pbLoading;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    private boolean validateInputs()
    {
        return required(txtUserName)&&
                required(txtPassword);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        btnSignIn.requestFocus();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateInputs()) return;

                String userName = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();

                pbLoading.setVisibility(View.VISIBLE);
                activity.apiCalls.signIn(userName, password, new CallbackWrapped<TokenResponse>() {
                    @Override
                    public void onResponse(TokenResponse response) {
                        pbLoading.setVisibility(View.INVISIBLE);

                        if(response==null)
                        {
                            toast(getString(R.string.error_invalid_name_password));
                            txtUserName.setText("");
                            txtPassword.setText("");

                        }
                        else
                        {
                            PrefUtil.setStringPreference(SharedPreferencesKeys.token,"bearer "+response.access_token);
                            Intent intent = new Intent(activity, MainActivity.class);
                            startActivity(intent);
                            activity.finish();

                        }

                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {
                        pbLoading.setVisibility(View.INVISIBLE);

                    }
                });


            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(SignUpFragment.newInstance());
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(ForgotPasswordFragment.newInstance());
            }
        });

        txtVerifyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(VerifyFragment.newInstance());
            }
        });
    }
}
