package com.itland.irecruitment.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itland.irecruitment.R;

import butterknife.ButterKnife;

public class VacancyDetailsFragment extends Fragment {


    public VacancyDetailsFragment() {
        // Required empty public constructor
    }

    public static VacancyDetailsFragment newInstance() {
        VacancyDetailsFragment fragment = new VacancyDetailsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vacancy_details, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
