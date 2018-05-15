package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractFragment;
import com.itland.irecruitment.api.CallbackWrapped;
import com.itland.irecruitment.api.ErrorMessage;
import com.itland.irecruitment.entities.JobApplicationDetails;
import com.itland.irecruitment.entities.ResumeDetails;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplicationDetailsFragment extends AbstractFragment {


    @Bind(R.id.txtVacancyTitle) TextView txtVacancyTitle;
    @Bind(R.id.txtRead) TextView txtRead;
    @Bind(R.id.txtCity) TextView txtCity;
    @Bind(R.id.txtDays) TextView txtDays;
    @Bind(R.id.txtName) TextView txtName;
    @Bind(R.id.txtCoverLetter) TextView txtCoverLetter;
    @Bind(R.id.txtPhone) TextView txtPhone;
    @Bind(R.id.txtEmail) TextView txtEmail;
    @Bind(R.id.txtAddress) TextView txtAddrss;
    @Bind(R.id.txtSite) TextView txtSite;

    @Bind(R.id.txtMore) TextView txtMore;

    public JobApplicationDetails details;

    public ApplicationDetailsFragment() {
        // Required empty public constructor
    }

    public static ApplicationDetailsFragment newInstance(JobApplicationDetails jobApplicationDetails) {
        ApplicationDetailsFragment fragment = new ApplicationDetailsFragment();
        fragment.details = jobApplicationDetails;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_details, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtVacancyTitle.setText(details.VacancyTitle);
        txtCity.setText(details.City);
        txtDays.setText(details.AppliedSince+"");
        txtName.setText("");
        txtCoverLetter.setText(details.CoverLetter);
        txtPhone.setText("");
        txtEmail.setText("");

        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.getResume(Integer.parseInt(details.ResumeId), new CallbackWrapped<ResumeDetails>() {
                    @Override
                    public void onResponse(ResumeDetails response) {
                        navigator.gotoSubSection(ResumeDetailsFragment.newInstance(response));
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });


    }

}
