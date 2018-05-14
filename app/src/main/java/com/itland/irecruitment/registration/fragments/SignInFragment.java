package com.itland.irecruitment.registration.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itland.irecruitment.MainActivity;
import com.itland.irecruitment.R;
import com.itland.irecruitment.Responses.TokenResponse;
import com.itland.irecruitment.abstracts.AbstractResistrationFragment;
import com.itland.irecruitment.api.CallbackWrapped;
import com.itland.irecruitment.api.ErrorMessage;
import com.itland.irecruitment.util.PrefUtil;
import com.itland.irecruitment.util.SharedPreferencesKeys;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInFragment extends AbstractResistrationFragment {

    @Bind(R.id.btnSignIn) ImageButton btnSignIn;
    @Bind(R.id.btnSignUp) Button btnSignUp;
    @Bind(R.id.txtUserName) TextView txtUserName;
    @Bind(R.id.txtPassword) TextView txtPassword;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        btnSignIn.requestFocus();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtUserName.getText().length()==0) txtUserName.setError(getString(R.string.error_required));
                if(txtUserName.getText().length()==0) txtUserName.setError(getString(R.string.error_required));

                String userName = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();

                if(isNullOrEmpty(userName)||isNullOrEmpty(password))
                {
                    return;
                }

                activity.apiCalls.signIn(userName, password, new CallbackWrapped<TokenResponse>() {
                    @Override
                    public void onResponse(TokenResponse response) {
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
    }
}
