package com.itland.employer.fragments;


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
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.responses.GeneralResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChangePasswordFragment extends AbstractFragment {

    @Bind(R.id.txtOldPassword) EditText txtOldPassword;
    @Bind(R.id.txtNewPassword) EditText txtNewPassword;
    @Bind(R.id.txtConfirmPassword) EditText txtConfirmPassword;
    @Bind(R.id.btnReset) Button btnReset;
    @Bind(R.id.btnCancel) Button btnCancel;



    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    public static ChangePasswordFragment newInstance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    private boolean validateInputs()
    {
        return required(txtOldPassword)&&
                required(txtNewPassword)&&
                required(txtConfirmPassword);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateInputs()) return;

                String oldPassword = txtOldPassword.getText().toString();
                String newPassword = txtNewPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();


                if(!confirmPassword.equals(newPassword))
                {
                    txtConfirmPassword.setError(getString(R.string.error_password_not_matched));
                    txtConfirmPassword.requestFocus();
                    return;
                }


                apiCalls.changePassword(oldPassword, newPassword, new CallbackWrapped<GeneralResponse>() {
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
