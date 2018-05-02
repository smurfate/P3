package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplicationDetailsFragment extends AbstractFragment {

    @Bind(R.id.toolbar) Toolbar toolbar;

    public ApplicationDetailsFragment() {
        // Required empty public constructor
    }

    public static ApplicationDetailsFragment newInstance() {
        ApplicationDetailsFragment fragment = new ApplicationDetailsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_details, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity.showActionBar(false);
        toolbar.setTitle("Sami somthing");
        toolbar.setSubtitle("developer");



    }

    @Override
    public void onPause() {
        activity.showActionBar(true);
        super.onPause();
    }
}
