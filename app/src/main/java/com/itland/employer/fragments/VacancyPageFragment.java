package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.adapters.VacanciesAdapter;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.VacancyDetails;
import com.itland.employer.responses.MyVacanciesResponse;
import com.yalantis.phoenix.PullToRefreshView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class VacancyPageFragment extends AbstractFragment {

    @Bind(R.id.list) ListView listView;
    @Bind(R.id.txtNoResult) TextView txtNoresults;
    @Bind(R.id.pull_to_refresh) PullToRefreshView pullToRefreshView;

    private VacanciesAdapter adapter;
    private VacanciesAdapter.Type type;

    private int index = 0;
    private boolean isDone = false; //got all list items


    public VacancyPageFragment() {
        // Required empty public constructor
    }

    public static VacancyPageFragment newInstance(VacanciesAdapter.Type type) {
        VacancyPageFragment fragment = new VacancyPageFragment();
        fragment.type = type;
        return fragment;
    }

    private void loadMore()
    {
        if(!isDone && adapter!=null)
        {
            if(listView.getLastVisiblePosition()==adapter.getCount()-1)
            {
                index +=1;
                apiCalls.getVacancies(index, new CallbackWrapped<MyVacanciesResponse>() {
                    @Override
                    public void onResponse(MyVacanciesResponse response) {

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vacancy_page, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    private void getData()
    {
        pullToRefreshView.setRefreshing(true);
        txtNoresults.setVisibility(View.GONE);

        apiCalls.getVacancies(index, new CallbackWrapped<MyVacanciesResponse>() {
            @Override
            public void onResponse(MyVacanciesResponse response) {
                pullToRefreshView.setRefreshing(false);
                adapter = new VacanciesAdapter(response.Items, type);
                listView.setAdapter(adapter);

                if(adapter.getCount()==0){
                    txtNoresults.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {
                pullToRefreshView.setRefreshing(false);
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        getData();

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                loadMore();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                apiCalls.getVacancyDetails((int) id, new CallbackWrapped<VacancyDetails>() {
                    @Override
                    public void onResponse(VacancyDetails response) {
                        navigator.gotoSubSection(VacancyDetailsFragment.newInstance(response));
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });


    }
}
