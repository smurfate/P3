package com.itland.employer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.itland.employer.abstracts.AbstractActivity;
import com.itland.employer.api.ApiCalls;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.CompanyProfile;
import com.itland.employer.fragments.ApplicationsFragment;
import com.itland.employer.fragments.HomeFragment;
import com.itland.employer.fragments.MoreFragment;
import com.itland.employer.fragments.ProfileFragment;
import com.itland.employer.fragments.ResumeFragment;
import com.itland.employer.fragments.VacanciesFragment;
import com.itland.employer.util.BottomNavigationViewHelper;
import com.itland.employer.util.FragmentNavigator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AbstractActivity {


    @Bind(R.id.toolbar) public Toolbar toolbar;
    @Bind(R.id.frmOverlay) FrameLayout frmOverlay;
    @Bind(R.id.navigation) public BottomNavigationView navigation;
    
    @Bind(R.id.space) Space space;
    @Bind(R.id.actionTitle) TextView actionTitle;
    @Bind(R.id.actionText) TextView actionText;
    @Bind(R.id.actionIcon) ImageView actionIcon;
    @Bind(R.id.imgProfile2) public ImageView actionLogo;
    
    @Bind(R.id.searchView) SearchView searchView;

    public FragmentNavigator navigator;
    public ActionBar actionBar;

    public ApiCalls apiCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLocale(getLocale(),false);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        apiCalls = new ApiCalls(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

//        toolbar.getOverflowIcon().setColorFilter(getResources().getColor(R.color.bbsf_background), PorterDuff.Mode.SRC_ATOP);
//        toolbar.setPopupTheme(R.style.OverflowOptionsMenu);


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.removeShiftMode(navigation);

        navigator = new FragmentNavigator(this, HomeFragment.newInstance(),navigation,mOnNavigationItemSelectedListener,R.id.frmContent);


        actionLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.viewProfile(new CallbackWrapped<CompanyProfile>() {
                    @Override
                    public void onResponse(CompanyProfile response) {
                        navigator.gotoSubSection(ProfileFragment.newInstance(response));

                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });

            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        setLocale(getLocale(), false);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    navigator.gotoMainSection();
                    return true;
                case R.id.navigation_resumes:

                    navigator.gotoSection(ResumeFragment.newInstance());
                    return true;
                case R.id.navigation_applications:
                    navigator.gotoSection(ApplicationsFragment.newInstance());
                    return true;
                case R.id.navigation_vacancies:
                    navigator.gotoSection(VacanciesFragment.newInstance());
                    return true;
                case R.id.navigation_more:
                    navigator.gotoSection(MoreFragment.newInstance());
                    return true;
            }
            return false;
        }

    };



    public void showProgressIndicator(boolean indeterminate) {

        if (indeterminate) {
            frmOverlay.setVisibility(View.VISIBLE);
        }else {
            frmOverlay.setVisibility(View.GONE);
        }
    }

    public void setTitle(String title)
    {
        actionTitle.setText(title);
    }

    public SearchView actionSearch(boolean show)
    {
        if(show)
        {
            actionIcon.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.VISIBLE);
            actionTitle.setVisibility(View.GONE);
            space.setVisibility(View.GONE);
        }
        else
        {
            actionIcon.setVisibility(View.GONE);
            searchView.setVisibility(View.GONE);
            space.setVisibility(View.VISIBLE);
            actionTitle.setVisibility(View.VISIBLE);
        }
        return searchView;
    }

    public void showActionBar(boolean show)
    {
        if(show) toolbar.setVisibility(View.VISIBLE);
        else toolbar.setVisibility(View.GONE);
    }


    public ImageView actionIcon(boolean show)
    {
        if(show) {
            actionIcon.setVisibility(View.VISIBLE);
            space.setVisibility(View.VISIBLE);
        }
        else {
            actionIcon.setVisibility(View.GONE);
            space.setVisibility(View.VISIBLE);
        }

        return actionIcon;
    }

    public TextView actionText(boolean show)
    {
        if(show) {
            actionText.setVisibility(View.VISIBLE);
            space.setVisibility(View.VISIBLE);
        }
        else {
            actionText.setVisibility(View.GONE);
            space.setVisibility(View.VISIBLE);
        }
        return actionText;
    }

}
