package com.itland.irecruitment.registration;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractActivity;
import com.itland.irecruitment.api.ApiCalls;
import com.itland.irecruitment.api.Apis;
import com.itland.irecruitment.registration.fragments.SignInFragment;
import com.itland.irecruitment.util.FragmentNavigator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistrationActivity extends AbstractActivity {

    public FragmentNavigator navigator;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton fab;

    private ApiCalls apiCalls;
    public Apis apis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        apiCalls = new ApiCalls();
        apis = apiCalls.apis;

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

    }

}
