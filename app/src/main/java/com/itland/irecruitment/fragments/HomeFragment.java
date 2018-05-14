package com.itland.irecruitment.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.Responses.CitiesListResponse;
import com.itland.irecruitment.Responses.TokenResponse;
import com.itland.irecruitment.abstracts.AbstractFragment;
import com.itland.irecruitment.api.ApiCalls;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends AbstractFragment {


//    @Bind(R.id.fab) FloatingActionButton fab;
//    @Bind(R.id.imgProfile) CircleImageView imgProfile;
//    @Bind(R.id.toolbar) Toolbar toolbar;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getString(R.string.home));
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        activity.showActionBar(true);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        activity.showActionBar(false);
//    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                navigator.gotoSection(ResumeFragment.newInstance());
//                ApiCalls calls = new ApiCalls();
////                calls.apis.CitiesList(0).enqueue(new CallbackWrapped<CitiesListResponse>() {
////                    @Override
////                    public void onResponse(Call<CitiesListResponse> call, Response<CitiesListResponse> response) {
////                        CitiesListResponse response1 = response.body();
////                        toast("Gooooood");
////                    }
////
////                    @Override
////                    public void onFailure(Call<CitiesListResponse> call, Throwable throwable) {
////                        toast("failier");
////                    }
////                });
//                calls.apis.token("password","client@example.com","Admin@123456","JobSeeker").enqueue(new CallbackWrapped<TokenResponse>() {
//                    @Override
//                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
//                        TokenResponse tokenResponse = response.body();
//                        toast(tokenResponse.access_token);
//                    }
//
//                    @Override
//                    public void onFailure(Call<TokenResponse> call, Throwable throwable) {
//
//                    }
//                });
//
//            }
//        });
//
//        imgProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigator.gotoSubSection(ProfileFragment.newInstance());
//            }
//        });
//        toolbar.setTitle("Title is everything");
//        toolbar.setTitleTextColor(Color.WHITE);
    }
}
