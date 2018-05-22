package com.itland.employer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itland.employer.abstracts.AbstractActivity;
import com.itland.employer.api.ApiCalls;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.entities.Message;
import com.itland.employer.registration.RegistrationActivity;
import com.itland.employer.responses.GeneralResponse;

public class SplashActivity extends AbstractActivity {

    private Handler handler;
    private ApiCalls apiCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        apiCalls = new ApiCalls() {
            @Override
            public void showProgress(boolean show) {

            }

            @Override
            public void toastError(Message message) {

            }
        };

        handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                apiCalls.getUserInfo(new CallbackWrapped<GeneralResponse>() {
                    @Override
                    public void onResponse(GeneralResponse response) {
                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();

                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {
                        Intent intent = new Intent(SplashActivity.this,RegistrationActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();

                    }
                });
            }
        },5000);
    }
}
