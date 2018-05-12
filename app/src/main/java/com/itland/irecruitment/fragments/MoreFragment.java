package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
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

public class MoreFragment extends AbstractFragment {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public MoreFragment() {
        // Required empty public constructor
    }

    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getString(R.string.more));
        toolbar.setTitle("More and more...");
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.showActionBar(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.showActionBar(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
