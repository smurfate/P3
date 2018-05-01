package com.itland.irecruitment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itland.irecruitment.abstracts.AbstractActivity;
import com.itland.irecruitment.fragments.ApplicationsFragment;
import com.itland.irecruitment.fragments.HomeFragment;
import com.itland.irecruitment.fragments.MoreFragment;
import com.itland.irecruitment.fragments.ResumeFragment;
import com.itland.irecruitment.fragments.VacanciesFragment;
import com.itland.irecruitment.util.FragmentNavigator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AbstractActivity {


    @Bind(R.id.toolbar) public Toolbar toolbar;
    @Bind(R.id.pb_loading) ProgressBar pbLoading;

    @Bind(R.id.tvTitle) TextView tvTitle;
    @Bind(R.id.imgProfile) ImageView imgProfile;
    @Bind(R.id.searchView) SearchView searchView;
    @Bind(R.id.imgFilter) ImageView imgFilter;

    public FragmentNavigator navigator;
    public ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

//        toolbar.getOverflowIcon().setColorFilter(getResources().getColor(R.color.bbsf_background), PorterDuff.Mode.SRC_ATOP);
//        toolbar.setPopupTheme(R.style.OverflowOptionsMenu);

        navigator = new FragmentNavigator(this, HomeFragment.newInstance(),R.id.frmContent);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                    actionbarSearch(false);
                    navigator.gotoSection(HomeFragment.newInstance());
                    return true;
                case R.id.navigation_resumes:
                    actionbarSearch(true);
                    navigator.gotoSection(ResumeFragment.newInstance());
                    return true;
                case R.id.navigation_applications:
                    actionbarSearch(false);
                    navigator.gotoSection(ApplicationsFragment.newInstance());
                    return true;
                case R.id.navigation_vacancies:
                    actionbarSearch(false);
                    navigator.gotoSection(VacanciesFragment.newInstance());
                    return true;
                case R.id.navigation_more:
                    actionbarSearch(false);
                    navigator.gotoSection(MoreFragment.newInstance());
                    return true;
            }
            return false;
        }

    };

    public void showProgressIndicator(boolean indeterminate) {

        if(pbLoading != null)
        {
            if (indeterminate) {
                pbLoading.setVisibility(View.VISIBLE);
            }else {
                pbLoading.setVisibility(View.GONE);
            }
        }
    }

    public void setTitle(String title)
    {
        tvTitle.setText(title);
    }

    public void actionbarSearch(boolean search)
    {
        if(search)
        {
            imgFilter.setVisibility(View.VISIBLE);
            searchView.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.GONE);
        }
        else
        {
            imgFilter.setVisibility(View.GONE);
            searchView.setVisibility(View.GONE);
            tvTitle.setVisibility(View.VISIBLE);
        }
    }

    public SearchView getSearchView()
    {
        return searchView;
    }

    public ImageView getFilterBtn() { return imgFilter; }


}
