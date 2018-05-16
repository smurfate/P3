package com.itland.employer.registration.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itland.employer.R;

import butterknife.ButterKnife;

public class VerifyFragment extends Fragment {

    public VerifyFragment() {
        // Required empty public constructor
    }

    public static VerifyFragment newInstance() {
        VerifyFragment fragment = new VerifyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
