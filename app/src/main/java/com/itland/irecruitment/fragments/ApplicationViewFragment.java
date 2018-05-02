package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.abstracts.AbstractFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplicationViewFragment extends AbstractFragment {

    @Bind(R.id.txtMore) TextView txtMore;

    public ApplicationViewFragment() {
        // Required empty public constructor
    }

    public static ApplicationViewFragment newInstance() {
        ApplicationViewFragment fragment = new ApplicationViewFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_view, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(ApplicationDetailsFragment.newInstance());
            }
        });

    }
}
