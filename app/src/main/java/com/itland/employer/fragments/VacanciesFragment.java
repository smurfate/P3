package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.itland.employer.R;
import com.itland.employer.adapters.VacancyPagerAdapter;
import com.itland.employer.responses.MyVacanciesResponse;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.adapters.VacanciesAdapter;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.VacancyDetails;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VacanciesFragment extends AbstractFragment {


    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.tabLayout) TabLayout tabLayout;

    private VacancyPagerAdapter adapter;
    private boolean expired = false;


    public VacanciesFragment() {
        // Required empty public constructor
    }

    public static VacanciesFragment newInstance() {
        VacanciesFragment fragment = new VacanciesFragment();
        return fragment;
    }

    public static VacanciesFragment newInstance(boolean expired) {
        VacanciesFragment fragment = new VacanciesFragment();
        fragment.expired = expired;
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

    @Override
    public void onPause() {
        super.onPause();
        adapter.clear();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new VacancyPagerAdapter(activity.getSupportFragmentManager(),activity);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        if(expired) viewPager.setCurrentItem(2);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(PostVacancyFragment.newInstance());
            }
        });


    }
}
