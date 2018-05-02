package com.itland.irecruitment.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractFragment;
import com.itland.irecruitment.adapters.ApplicationsAdapter;
import com.itland.irecruitment.entities.Application;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplicationsFragment extends AbstractFragment {

    @Bind(R.id.lstApplications) ListView lstApplications;

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

        List<Application> applications = new ArrayList<>(10);
        for(int i=0;i<10;i++) applications.add(new Application());
        ApplicationsAdapter adapter = new ApplicationsAdapter(applications);
        lstApplications.setAdapter(adapter);

        lstApplications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigator.gotoSubSection(ApplicationViewFragment.newInstance());
            }
        });

    }
}
