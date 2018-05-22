package com.itland.employer.fragments;


import android.content.Intent;
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
import com.itland.employer.registration.RegistrationActivity;
import com.itland.employer.responses.GeneralResponse;
import com.itland.employer.responses.HomeResponse;
import com.itland.employer.util.PrefUtil;
import com.itland.employer.util.SharedPreferencesKeys;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends AbstractFragment {


    @Bind(R.id.btnSearch) ImageButton btnSearch;

    @Bind(R.id.txtCompanyName) TextView txtCompanyName;
    @Bind(R.id.txtApplications) TextView txtApplications;
    @Bind(R.id.txtActiveVacancy) TextView txtActiveVacancy;
    @Bind(R.id.txtExpiredVacancy) TextView txtExpiredVacancy;
    @Bind(R.id.txtViews) TextView txtViews;
    @Bind(R.id.imgProfile) CircleImageView imgProfile;

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
                Picasso.with(activity).load(response.ImageUrl).error(R.mipmap.profile).into(imgProfile);

                Picasso.with(activity).load(response.ImageUrl).error(R.mipmap.profile).into(activity.actionLogo);



            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });

        apiCalls.getUserInfo(new CallbackWrapped<GeneralResponse>() {
            @Override
            public void onResponse(GeneralResponse response) {

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {
                PrefUtil.setStringPreference(SharedPreferencesKeys.token,"");
                Intent intent = new Intent(activity, RegistrationActivity.class);
                startActivity(intent);
                activity.finish();

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
