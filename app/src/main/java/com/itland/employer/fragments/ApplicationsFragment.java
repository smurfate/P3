package com.itland.employer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.responses.JobApplicationsListResponse;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.adapters.ApplicationsAdapter;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.JobApplicationDetails;
import com.yalantis.phoenix.PullToRefreshView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplicationsFragment extends AbstractFragment {

    @Bind(R.id.lstApplications) ListView lstApplications;
    @Bind(R.id.txtNoResult) TextView txtNoResult;
    @Bind(R.id.pull_to_refresh) PullToRefreshView swipeLayout;


    private boolean isDone = false;
    private int index = 0;
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

    private void loadMore()
    {
        if(!isDone && adapter!=null)
        {
            if(lstApplications.getLastVisiblePosition()==adapter.getCount()-1)
            {
                index += 1;

                apiCalls.getApplicationsList(index, new CallbackWrapped<JobApplicationsListResponse>() {
                    @Override
                    public void onResponse(JobApplicationsListResponse response) {
                        if(response.Items.size()==0) {
                            isDone=true;
                            index = 0;
                        }

                        adapter.loadMore(response.Items);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });


            }

        }

    }



    private void getData()
    {
        swipeLayout.setRefreshing(true);
        txtNoResult.setVisibility(View.GONE);
        apiCalls.getApplicationsList(0, new CallbackWrapped<JobApplicationsListResponse>() {
            @Override
            public void onResponse(JobApplicationsListResponse response) {
                swipeLayout.setRefreshing(false);
                adapter = new ApplicationsAdapter(response.Items);
                lstApplications.setAdapter(adapter);

                if(adapter.getCount()==0){
                    txtNoResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {
                swipeLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeLayout.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });


        getData();

        lstApplications.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                loadMore();
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
