package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractFragment;
import com.itland.irecruitment.adapters.ResumeAdapter;
import com.itland.irecruitment.entities.Resume;
import com.itland.irecruitment.util.FragmentNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResumeFragment extends AbstractFragment {


    @Bind(R.id.gridView) GridView gridView;
    @Bind(R.id.drawer_layout) public DrawerLayout drawer;
    @Bind(R.id.nav_view) NavigationView navigationView;


    @Bind(R.id.lnrChoose) LinearLayout lnrChoose;
    @Bind(R.id.lnrFilter) LinearLayout lnrFilter;
    @Bind(R.id.txtDone) TextView txtDone;



    public ResumeFragment() {
        // Required empty public constructor
    }

    public static ResumeFragment newInstance() {
        ResumeFragment fragment = new ResumeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resume, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getString(R.string.resumes));
    }

    private void showFilter()
    {
        lnrFilter.setVisibility(View.VISIBLE);
        lnrChoose.setVisibility(View.GONE);
    }

    private void showChoose()
    {
        lnrFilter.setVisibility(View.GONE);
        lnrChoose.setVisibility(View.VISIBLE);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity.actionbarSearch(true);

        List<Resume> lst = new ArrayList<>(10);
        for(int i=0;i<10;i++) lst.add(new Resume());
        ResumeAdapter adapter = new ResumeAdapter(lst);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigator.gotoSubSection(VacancyDetailsFragment.newInstance());
            }
        });


        SearchView searchView = activity.getSearchView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                toast(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        activity.getFilterBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.END);
            }
        });

        showFilter();
        txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoose();
            }
        });



    }

    @Override
    public void onPause() {
        activity.actionbarSearch(false);
        super.onPause();
    }
}
