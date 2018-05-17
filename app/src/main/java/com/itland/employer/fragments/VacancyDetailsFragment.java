package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.entities.VacancyDetails;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VacancyDetailsFragment extends AbstractFragment {

    private VacancyDetails details;

    @Bind(R.id.txtTitle) TextView txtTitle;
    @Bind(R.id.txtCompanyName) TextView txtCompanyName;
    @Bind(R.id.txtCompanyIndustry) TextView txtCompanyIndustry;
    @Bind(R.id.txtActiveStatus) TextView txtActiveStatus;
    @Bind(R.id.txtPostedSince) TextView txtPostedSince;
    @Bind(R.id.txtExpiredSince) TextView txtExpiredSince;
    @Bind(R.id.txtApplications) TextView txtApplications;
    @Bind(R.id.txtViews) TextView txtViews;
    @Bind(R.id.lnrExpired) LinearLayout lnrExpired;
    @Bind(R.id.lnrViews) LinearLayout lnrViews;
    @Bind(R.id.line) View line;

    @Bind(R.id.txtLocation) TextView txtLocation;
    @Bind(R.id.txtRequiredExperience) TextView txtRequiredExperience;
    @Bind(R.id.txtSkills) TextView txtSkills;
    @Bind(R.id.txtCompanyIndustry2) TextView txtCompanyIndustry2;
    @Bind(R.id.txtType) TextView txtType;
    @Bind(R.id.txtJobDescription) TextView txtJobDescription;


    public VacancyDetailsFragment() {
        // Required empty public constructor
    }

    public static VacancyDetailsFragment newInstance(VacancyDetails details) {
        VacancyDetailsFragment fragment = new VacancyDetailsFragment();
        fragment.details = details;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vacancy_details, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle("Vacancy details");
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.actionIcon(true).setImageResource(R.drawable.ic_edit);

    }

    @Override
    public void onPause() {
        super.onPause();
        activity.actionIcon(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtTitle.setText(details.Title);
        txtCompanyName.setText(details.CompanyName);
        txtCompanyIndustry.setText(details.CompanyIndustry);
        txtCompanyIndustry2.setText(details.CompanyIndustry);
        txtViews.setText(details.ViewsCount.toString());
        txtApplications.setText(details.ApplicationsCount.toString());
        txtActiveStatus.setText(details.Status);

        txtLocation.setText(details.WorkCity+", "+details.WorkCountry);
        txtRequiredExperience.setText(details.ExperienceNeededYears.toString());
        txtSkills.setText(details.SkillsNeeded);
        txtType.setText(details.Type);
        txtJobDescription.setText(details.JobDescription);

        if(details.isActive())
        {
            lnrViews.setVisibility(View.VISIBLE);
            lnrExpired.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            txtExpiredSince.setText(details.ExpiresIn.toString());
        }

        if(details.isInactive())
        {
            lnrViews.setVisibility(View.GONE);
            lnrExpired.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }

        if(details.isExpired())
        {
            lnrViews.setVisibility(View.VISIBLE);
            lnrExpired.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            txtExpiredSince.setText(details.ExpiredSince.toString());
        }

        activity.actionIcon(true).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(details.isActive()) navigator.gotoSubSection(EditVacancyFragment.newInstance(details));
                if(details.isInactive() || details.isExpired()) navigator.gotoSubSection(PostVacancyFragment.newInstance(details));


            }
        });


    }
}
