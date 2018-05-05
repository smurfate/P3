package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplicationDetailsFragment extends AbstractFragment {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.txtTitle) TextView txtTitle;

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
        txtTitle.setText("Hello world");



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
}
