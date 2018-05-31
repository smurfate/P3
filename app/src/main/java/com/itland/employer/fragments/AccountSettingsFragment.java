package com.itland.employer.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.entities.ProfileInfo;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountSettingsFragment extends AbstractFragment {

    @Bind(R.id.txtName) TextView txtName;
    @Bind(R.id.imgLogo) CircleImageView imgLogo;
    @Bind(R.id.txtEmail) TextView txtEmail;
    @Bind(R.id.txtUsername) TextView txtUsername;
    @Bind(R.id.txtPhone) TextView txtPhone;
    @Bind(R.id.lnrChangeEmail) LinearLayout lnrChangeEmail;
    @Bind(R.id.lnrChangeGsm) LinearLayout lnrChangeGsm;
    @Bind(R.id.imgEditEmail) ImageView imgEditEmail;
    @Bind(R.id.imgEditGsm) ImageView imgEditGsm;
    @Bind(R.id.txtChange) TextView txtChange;
    @Bind(R.id.btnEdit) ImageButton btnEdit;
    private ProfileInfo info;

    public AccountSettingsFragment() {
        // Required empty public constructor
    }

    public static AccountSettingsFragment newInstance(ProfileInfo info) {
        AccountSettingsFragment fragment = new AccountSettingsFragment();
        fragment.info = info;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(info.Username);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtEmail.setText(info.Email);
        txtName.setText(info.Username);
        txtUsername.setText(info.Username);
        txtPhone.setText("+" + info.GsmCountryCode + info.PhoneNumber);

        if (!isNullOrEmpty(info.ImageURL)) {
            Picasso.with(activity).load(info.ImageURL).error(R.mipmap.profile).into(imgLogo);
        }

        lnrChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(ChangeEmailFragment.newInstance());
            }
        });

        lnrChangeGsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(ChangeGsmFragment.newInstance());
            }
        });

        txtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(ChangePasswordFragment.newInstance());
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.gotoSubSection(EditProfileInfoFragment.newInstance(info));
            }
        });
    }
}
