package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.responses.HomeResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends AbstractFragment {


    @Bind(R.id.btnSearch) ImageButton btnSearch;

    @Bind(R.id.txtCompanyName) TextView txtCompanyName;
    @Bind(R.id.txtApplications) TextView txtApplications;
    @Bind(R.id.txtActiveVacancy) TextView txtActiveVacancy;
    @Bind(R.id.txtExpiredVacancy) TextView txtExpiredVacancy;
    @Bind(R.id.txtViews) TextView txtViews;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getString(R.string.home));
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiCalls.getHomeData(new CallbackWrapped<HomeResponse>() {
            @Override
            public void onResponse(HomeResponse response) {

                txtCompanyName.setText(response.CompanyName);
                txtApplications.setText(getString(R.string.n_applications,response.ApplicationsCount));
                txtActiveVacancy.setText(getString(R.string.n_active_vacancies,response.ActiveVacancies));
                txtExpiredVacancy.setText(getString(R.string.n_expired_vacancies,response.ExpiredVacancies));
                txtViews.setText(getString(R.string.n_views_this_week,response.ViewsCount));

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSection(ResumeFragment.newInstance());
            }
        });
    }
}
