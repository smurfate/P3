package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.CountyCode;
import com.itland.employer.entities.Indice;
import com.itland.employer.responses.CountyCodeResponse;
import com.itland.employer.responses.GeneralResponse;
import com.itland.employer.responses.IndicesListResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VerifyChangeGsmFragment extends AbstractFragment {

    @Bind(R.id.spnCountyCode) Spinner spnCountyCode;
    @Bind(R.id.txtGsm) EditText txtGsm;
    @Bind(R.id.txtCode) EditText txtCode;
    @Bind(R.id.txtResend) TextView txtResend;
    @Bind(R.id.btnSave) Button btnSave;

    private String password;

    private HashMap<String,CountyCode> name2code = new HashMap<>();

    public VerifyChangeGsmFragment() {
        // Required empty public constructor
    }

    public static VerifyChangeGsmFragment newInstance(String password) {
        VerifyChangeGsmFragment fragment = new VerifyChangeGsmFragment();
        fragment.password = password;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_change_gsm, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    private boolean validateInput()
    {
        return required(txtGsm)&&
                required(txtCode);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiCalls.getCountyCodes(new CallbackWrapped<CountyCodeResponse>() {
            @Override
            public void onResponse(CountyCodeResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2code.clear();

                for(CountyCode indice : response.Items)
                {
                    name2code.put(indice.CountryCode,indice);
                    indiceName.add(indice.CountryCode);

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnCountyCode.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateInput()) return;

                String countyCode = name2code.get(spnCountyCode.getSelectedItem().toString()).DialCode;
                String gsm = txtGsm.getText().toString();
                String code = txtCode.getText().toString();

                apiCalls.verifyChangeGsm(countyCode, gsm, code, new CallbackWrapped<GeneralResponse>() {
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
                if(!required(txtGsm)) return;

                String countyCode = name2code.get(spnCountyCode.getSelectedItem().toString()).DialCode;
                String gsm = txtGsm.getText().toString();

                apiCalls.resendCodeChangeGsm(countyCode, gsm, password, new CallbackWrapped<GeneralResponse>() {
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
