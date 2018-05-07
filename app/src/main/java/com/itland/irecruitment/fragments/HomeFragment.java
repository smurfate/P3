package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractFragment;
import com.itland.irecruitment.entities.Resume;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends AbstractFragment {


    @Bind(R.id.toolbar_layout) CollapsingToolbarLayout toolbar_layout;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.imgProfile) CircleImageView imgProfile;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getString(R.string.home));
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.showActionBar(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.showActionBar(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar_layout.setTitle("hello");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSection(ResumeFragment.newInstance());

            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("Nice");
            }
        });

    }
}
