package com.itland.employer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.itland.employer.R;
import com.itland.employer.Responses.JobApplicationsListResponse;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.adapters.ApplicationsAdapter;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.JobApplicationDetails;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplicationsFragment extends AbstractFragment {

    @Bind(R.id.lstApplications) ListView lstApplications;

    private ApplicationsAdapter adapter;

    public ApplicationsFragment() {
        // Required empty public constructor
    }

    public static ApplicationsFragment newInstance() {
        ApplicationsFragment fragment = new ApplicationsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_applications, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getString(R.string.applications));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        apiCalls.getApplicationsList(0, new CallbackWrapped<JobApplicationsListResponse>() {
            @Override
            public void onResponse(JobApplicationsListResponse response) {
                adapter = new ApplicationsAdapter(response.Items);
                lstApplications.setAdapter(adapter);
            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });


        lstApplications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                apiCalls.getApplicationDetails((int) id, new CallbackWrapped<JobApplicationDetails>() {
                    @Override
                    public void onResponse(JobApplicationDetails response) {
                        navigator.gotoSubSection(ApplicationDetailsFragment.newInstance(response));
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });

    }
}
