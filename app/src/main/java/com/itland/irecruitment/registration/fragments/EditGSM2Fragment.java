package com.itland.irecruitment.registration.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itland.irecruitment.R;

import butterknife.ButterKnife;

public class EditGSM2Fragment extends Fragment {


    public EditGSM2Fragment() {
        // Required empty public constructor
    }

    public static EditGSM2Fragment newInstance(String param1, String param2) {
        EditGSM2Fragment fragment = new EditGSM2Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_gsm2, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
