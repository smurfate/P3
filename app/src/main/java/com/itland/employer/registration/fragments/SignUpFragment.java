package com.itland.employer.registration.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractResistrationFragment;
import com.itland.employer.entities.CountyCode;
import com.itland.employer.responses.CountyCodeResponse;
import com.itland.employer.responses.IndicesListResponse;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.Indice;
import com.itland.employer.responses.RegisterResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpFragment extends AbstractResistrationFragment {


    @Bind(R.id.spnCountyCode) Spinner spnCountryCode;
    @Bind(R.id.txtGsmNumber) EditText txtGsm;
    @Bind(R.id.txtEmail) EditText txtEmail;
    @Bind(R.id.txtUserName) EditText txtUserName;
    @Bind(R.id.txtPassword) EditText txtPassword;
    @Bind(R.id.txtConfirmPassword) EditText txtConfirmPassword;
    @Bind(R.id.txtFirstName) EditText txtFirstName;
    @Bind(R.id.txtLastName) EditText txtLastName;
    @Bind(R.id.chkAgree) CheckBox chkAgree;
    @Bind(R.id.btnSignup) Button btnSignup;

    private HashMap<String,CountyCode> name2code = new HashMap<>();

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


    private boolean validateInput()
    {
        return required(txtGsm)&&
                required(spnCountryCode)&&
                required(txtEmail)&&
                required(txtUserName)&&
                required(txtPassword)&&
                required(txtConfirmPassword)&&
                required(txtFirstName)&&
                required(txtLastName);
    }

    private String getName(CountyCode code)
    {
        return code.CountryCode+"(+"+code.DialCode+")";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity.apiCalls.getCountyCodes(new CallbackWrapped<CountyCodeResponse>() {
            @Override
            public void onResponse(CountyCodeResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2code.clear();

                for(CountyCode indice : response.Items)
                {
                    name2code.put(getName(indice),indice);
                    indiceName.add(getName(indice));

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnCountryCode.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });




        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateInput())return;


                String code = name2code.get(spnCountryCode.getSelectedItem().toString()).DialCode;
                String gsm = txtGsm.getText().toString();
                String email = txtEmail.getText().toString();
                String userName = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();
                String confirm = txtConfirmPassword.getText().toString();
                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();

                if(!password.equals(confirm)) {
                    txtConfirmPassword.setError(getString(R.string.error_password_not_matched));
                    txtConfirmPassword.requestFocus();
                    return;
                }


                if(chkAgree.isChecked())
                {
                    activity.apiCalls.signUp(code, gsm, email, userName, password, firstName, lastName, new CallbackWrapped<RegisterResponse>() {
                        @Override
                        public void onResponse(RegisterResponse response) {
                            toast(response);
                            navigator.gotoSection(VerifyFragment.newInstance(response.Username));

                        }

                        @Override
                        public void onFailure(ErrorMessage errorMessage) {
                        }
                    });
                }

            }
        });

    }
}
