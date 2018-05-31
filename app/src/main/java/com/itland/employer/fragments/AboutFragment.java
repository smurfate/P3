package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.responses.AboutUsResponse;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AboutFragment extends Fragment {


    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.txtContent)
    TextView txtContent;
    private AboutUsResponse about;

    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(AboutUsResponse about) {
        AboutFragment fragment = new AboutFragment();
        fragment.about = about;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtTitle.setText(about.Title);
        txtContent.setText(about.Content);
    }
}
