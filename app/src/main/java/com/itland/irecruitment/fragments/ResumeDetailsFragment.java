package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractFragment;
import com.itland.irecruitment.entities.ResumeDetails;

import butterknife.ButterKnife;

public class ResumeDetailsFragment extends AbstractFragment {

    private ResumeDetails details;

    public ResumeDetailsFragment() {
        // Required empty public constructor
    }

    public static ResumeDetailsFragment newInstance(ResumeDetails details) {
        ResumeDetailsFragment fragment = new ResumeDetailsFragment();
        fragment.details = details;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resume_details, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
