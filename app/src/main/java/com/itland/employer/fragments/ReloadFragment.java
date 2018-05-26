package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.util.FragmentNavigator;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ReloadFragment extends Fragment {


    @Bind(R.id.txtReload) TextView txtReload;
    @Bind(R.id.frmRelaod) FrameLayout frmReload;

    private FragmentNavigator navigator;

    public ReloadFragment() {
        // Required empty public constructor
    }

    public static ReloadFragment newInstance(FragmentNavigator navigator) {
        ReloadFragment fragment = new ReloadFragment();
        fragment.navigator = navigator;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reload, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        navigator.getActivity().getSupportActionBar().hide();
    }

    @Override
    public void onPause() {
        super.onPause();
        navigator.getActivity().getSupportActionBar().show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frmReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.goBack();
            }
        });
    }

}
