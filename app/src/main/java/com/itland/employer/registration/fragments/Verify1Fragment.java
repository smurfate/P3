package com.itland.employer.registration.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractResistrationFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.responses.GeneralResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Verify1Fragment extends AbstractResistrationFragment {

    @Bind(R.id.txtUserName) EditText txtUsername;
    @Bind(R.id.btnReset) Button btnReset;

    public Verify1Fragment() {
        // Required empty public constructor
    }

    public static Verify1Fragment newInstance() {
        Verify1Fragment fragment = new Verify1Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify1, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!required(txtUsername)) return;
                final String username = txtUsername.getText().toString();

                activity.apiCalls.resendCodeRegister(username, new CallbackWrapped<GeneralResponse>() {
                    @Override
                    public void onResponse(GeneralResponse response) {
                        toast(response);
                        navigator.gotoSubSection(VerifyFragment.newInstance(username));

                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });
    }
}
