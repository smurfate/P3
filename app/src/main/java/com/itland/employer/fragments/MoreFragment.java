package com.itland.employer.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractActivity;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.ProfileInfo;
import com.itland.employer.registration.RegistrationActivity;
import com.itland.employer.responses.AboutUsResponse;
import com.itland.employer.responses.HomeResponse;
import com.itland.employer.util.PrefUtil;
import com.itland.employer.util.SharedPreferencesKeys;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MoreFragment extends AbstractFragment {


    @Bind(R.id.switchLanguage) SwitchCompat switchLanguage;
    @Bind(R.id.txtSignout) TextView txtSignOut;
    @Bind(R.id.txtAbout) TextView txtAbout;
    @Bind(R.id.txtCompanyName) TextView txtCompanyName;
    @Bind(R.id.txtAccount) TextView txtAccount;
    @Bind(R.id.imgLogo) CircleImageView imgLogo;

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

        apiCalls.getHomeData(new CallbackWrapped<HomeResponse>() {
            @Override
            public void onResponse(HomeResponse response) {

                Picasso.with(activity).load(response.ImageUrl).error(R.mipmap.profile).into(imgLogo);
                txtCompanyName.setText(response.CompanyName);
            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });


        if(activity.getLocale().equals(AbstractActivity.Lang.ar)) {
            switchLanguage.setText(R.string.arabic);
            switchLanguage.setChecked(true);
        } else {
            switchLanguage.setText(R.string.english);
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


        txtSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apiCalls.signOut(new CallbackWrapped<Void>() {
                    @Override
                    public void onResponse(Void response) {

                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {
                        PrefUtil.setStringPreference(SharedPreferencesKeys.token,"");
                        Intent intent = new Intent(activity, RegistrationActivity.class);
                        startActivity(intent);
                        activity.finish();

                    }
                });

            }
        });


        txtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.getProfileInfo(new CallbackWrapped<ProfileInfo>() {
                    @Override
                    public void onResponse(ProfileInfo response) {
                        navigator.gotoSubSection(AccountSettingsFragment.newInstance(response));
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });

        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCalls.getAboutUs(new CallbackWrapped<AboutUsResponse>() {
                    @Override
                    public void onResponse(AboutUsResponse response) {
                        navigator.gotoSubSection(AboutFragment.newInstance(response));
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });
            }
        });


    }
}
