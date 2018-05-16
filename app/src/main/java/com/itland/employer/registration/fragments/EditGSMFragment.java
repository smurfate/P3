package com.itland.employer.registration.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itland.employer.R;

import butterknife.ButterKnife;

public class EditGSMFragment extends Fragment {


    public EditGSMFragment() {
        // Required empty public constructor
    }

    public static EditGSMFragment newInstance() {
        EditGSMFragment fragment = new EditGSMFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_gsm, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
