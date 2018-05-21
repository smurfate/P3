package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.itland.employer.R;
import com.itland.employer.responses.MyVacanciesResponse;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.adapters.VacanciesAdapter;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.VacancyDetails;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VacanciesFragment extends AbstractFragment {

    @Bind(R.id.tabHost) TabHost tabHost;
    @Bind(R.id.tablayout) TabLayout tabLayout;
    @Bind(R.id.lstActive) ListView lstActive;
    @Bind(R.id.lstInActive) ListView lstInActive;
    @Bind(R.id.lstExpired) ListView lstExpired;

    @Bind(R.id.fab) FloatingActionButton fab;

    private VacanciesAdapter activeVacanciesAdapter;
    private VacanciesAdapter inactiveVacanciesAdapter;
    private VacanciesAdapter expiredVacanciesAdapter;

    private int index = 0;
    private boolean isDone = false; //got all list items


    public VacanciesFragment() {
        // Required empty public constructor
    }

    public static VacanciesFragment newInstance() {
        VacanciesFragment fragment = new VacanciesFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vacancies, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getString(R.string.vacancies));
    }

    private void loadMore()
    {
        if(!isDone && activeVacanciesAdapter!=null)
        {
            if(lstActive.getLastVisiblePosition()==activeVacanciesAdapter.getCount()-1)
            {
                index +=1;
                apiCalls.getVacancies(index, new CallbackWrapped<MyVacanciesResponse>() {
                    @Override
                    public void onResponse(MyVacanciesResponse response) {

                        if(response.Items.size()==0) isDone=true;

                        activeVacanciesAdapter.loadMore(response.Items);
                        inactiveVacanciesAdapter.loadMore(response.Items);
                        expiredVacanciesAdapter.loadMore(response.Items);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }

        }

    }

    private void setupTabs()
    {
        tabHost.setup();
        TabHost.TabSpec active_tab = tabHost.newTabSpec("activeTab");
        active_tab.setIndicator("activeTab").setContent(R.id.active_layout);
        TabHost.TabSpec inactive_tab = tabHost.newTabSpec("inactiveTab");
        inactive_tab.setIndicator("inactiveTab").setContent(R.id.inactive_layout);
        TabHost.TabSpec expired_tab = tabHost.newTabSpec("expiredTab");
        expired_tab.setIndicator("expiredTab").setContent(R.id.expired_layout);
        TabWidget tabWidget = tabHost.getTabWidget();
        tabWidget.setBackgroundColor(0xff009688);
        tabHost.addTab(active_tab);
        tabHost.addTab(inactive_tab);
        tabHost.addTab(expired_tab);


        TabLayout.Tab activeTab = tabLayout.newTab();
        TabLayout.Tab inactiveTab = tabLayout.newTab();
        TabLayout.Tab expiredTab = tabLayout.newTab();
        activeTab.setText(R.string.active);
        inactiveTab.setText(R.string.inactive);
        expiredTab.setText(R.string.expired);
        tabLayout.addTab(activeTab);
        tabLayout.addTab(inactiveTab);
        tabLayout.addTab(expiredTab);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabHost.setCurrentTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupTabs();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(PostVacancyFragment.newInstance());
            }
        });


        apiCalls.getVacancies(index, new CallbackWrapped<MyVacanciesResponse>() {
            @Override
            public void onResponse(MyVacanciesResponse response) {
                activeVacanciesAdapter = new VacanciesAdapter(response.Items, VacanciesAdapter.Type.active);
                inactiveVacanciesAdapter = new VacanciesAdapter(response.Items, VacanciesAdapter.Type.inactive);
                expiredVacanciesAdapter = new VacanciesAdapter(response.Items, VacanciesAdapter.Type.expired);

                lstActive.setAdapter(activeVacanciesAdapter);
                lstInActive.setAdapter(inactiveVacanciesAdapter);
                lstExpired.setAdapter(expiredVacanciesAdapter);
            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });


        lstActive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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


        lstActive.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                loadMore();
            }
        });


        lstInActive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        lstExpired.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
