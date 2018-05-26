package com.itland.employer.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.itland.employer.MainActivity;
import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractActivity;
import com.itland.employer.api.ApiCalls;
import com.itland.employer.entities.Message;
import com.itland.employer.registration.fragments.SignInFragment;
import com.itland.employer.util.FragmentNavigator;
import com.itland.employer.util.PrefUtil;
import com.itland.employer.util.SharedPreferencesKeys;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistrationActivity extends AbstractActivity {

    public FragmentNavigator navigator;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.frmOverlay) public FrameLayout frmOverlay;

    public ApiCalls apiCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);

        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().hide();

        navigator = new FragmentNavigator(this, SignInFragment.newInstance(),null,null,R.id.frmContent);
        apiCalls = new ApiCalls(navigator) {
            @Override
            public void showProgress(boolean show) {
                showProgressIndicator(show);
            }

            @Override
            public void toastError(String message) {
                toast(message);
            }
        };



    }

    public void showProgressIndicator(boolean indeterminate) {

        if (indeterminate) {
            frmOverlay.setVisibility(View.VISIBLE);
        }else {
            frmOverlay.setVisibility(View.GONE);
        }
    }


}
