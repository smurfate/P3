package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractActivity;
import com.itland.employer.abstracts.AbstractFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoreFragment extends AbstractFragment {


    @Bind(R.id.switchLanguage) SwitchCompat switchLanguage;

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
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(activity.getLocale().equals(AbstractActivity.Lang.ar)) {
            switchLanguage.setText("Arabic");
            switchLanguage.setChecked(true);
        } else {
            switchLanguage.setText("English");
            switchLanguage.setChecked(false);
        }

        switchLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AbstractActivity.Lang lang;
                if(activity.getLocale().equals(AbstractActivity.Lang.ar)) lang = AbstractActivity.Lang.en; else lang= AbstractActivity.Lang.ar;
                activity.setLocale(lang,true);
            }
        });


    }
}
