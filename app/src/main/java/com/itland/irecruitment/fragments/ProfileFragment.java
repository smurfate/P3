package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileFragment extends AbstractFragment {


    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton fab;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity.showActionBar(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle("S4U");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(EditProfileFragment.newInstance());
            }
        });
    }

    @Override
    public void onPause() {
        activity.showActionBar(true);
        super.onPause();
    }


}
