package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.itland.irecruitment.R;
import com.itland.irecruitment.Responses.MyVacanciesResponse;
import com.itland.irecruitment.abstracts.AbstractFragment;
import com.itland.irecruitment.adapters.VacanciesAdapter;
import com.itland.irecruitment.api.CallbackWrapped;
import com.itland.irecruitment.api.ErrorMessage;
import com.itland.irecruitment.entities.Vacancy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VacanciesFragment extends AbstractFragment {

    @Bind(R.id.tabHost) TabHost tabHost;
    @Bind(R.id.tablayout) TabLayout tabLayout;
    @Bind(R.id.lstActive) ListView lstActive;
    @Bind(R.id.lstInActive) ListView lstInActive;
    @Bind(R.id.lstExpired) ListView lstExpired;

    @Bind(R.id.fab) FloatingActionButton fab;

    private VacanciesAdapter adapter;
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
        activeTab.setText("Active");
        inactiveTab.setText("In-Active");
        expiredTab.setText("Expired");
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
                navigator.gotoSubSection(PostJobFragment.newInstance());
            }
        });


        apiCalls.getVacancies(0, new CallbackWrapped<MyVacanciesResponse>() {
            @Override
            public void onResponse(MyVacanciesResponse response) {
                adapter = new VacanciesAdapter(response.Items);
                lstActive.setAdapter(adapter);
            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });


        lstActive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigator.gotoSubSection(EditVacancyFragment.newInstance());
            }
        });
    }
}
