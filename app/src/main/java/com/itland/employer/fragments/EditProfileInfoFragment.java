package com.itland.employer.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractActivity;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.api.CallbackWrapped;
import com.itland.employer.api.ErrorMessage;
import com.itland.employer.api.FileUploader;
import com.itland.employer.entities.CountyCode;
import com.itland.employer.entities.ProfileInfo;
import com.itland.employer.responses.CountyCodeResponse;
import com.itland.employer.responses.GeneralResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfileInfoFragment extends AbstractFragment {

    @Bind(R.id.txtName) EditText txtName;
    @Bind(R.id.imgLogo) CircleImageView imgLogo;
    @Bind(R.id.imgUpload) ImageView imgUpload;
    @Bind(R.id.txtFirstName) EditText txtFirstName;
    @Bind(R.id.txtLastName) EditText txtLastName;
    @Bind(R.id.txtEmail) EditText txtEmail;
    @Bind(R.id.spnCountyCode) Spinner spnCountyCode;
    @Bind(R.id.txtPhone) EditText txtPhone;

    private ProfileInfo info;
    private String logoUrl;
    private TextView txtSave;

    private HashMap<String,CountyCode> name2code = new HashMap<>();

    public EditProfileInfoFragment() {
        // Required empty public constructor
    }

    public static EditProfileInfoFragment newInstance(ProfileInfo info) {
        EditProfileInfoFragment fragment = new EditProfileInfoFragment();
        fragment.info = info;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private boolean validateInputs()
    {
        return required(txtName)&&
                required(txtFirstName)&&
                required(txtLastName)&&
                required(txtEmail)&&
                required(txtPhone)&&
                required(spnCountyCode);
    }

    private String getName(CountyCode code)
    {
        return code.CountryCode+"(+"+code.DialCode+")";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtSave= activity.actionText(true);

        imgUpload.setVisibility(View.GONE);

        txtName.setText(info.Username);
        txtFirstName.setText(info.FirstName);
        txtLastName.setText(info.LastName);
        txtEmail.setText(info.Email);
        txtPhone.setText(info.PhoneNumber);

        activity.apiCalls.getCountyCodes(new CallbackWrapped<CountyCodeResponse>() {
            @Override
            public void onResponse(CountyCodeResponse response) {
                List<String> indiceName = new ArrayList<>();
                name2code.clear();

                for(CountyCode indice : response.Items)
                {
                    name2code.put(getName(indice),indice);
                    indiceName.add(getName(indice));

                }
                if(isSafe())
                {
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_spinner,indiceName);
                    spnCountyCode.setAdapter(adapter);

                    for (int i=0;i<adapter.getCount();i++)
                    {
                        if(adapter.getItem(i).toString().contains(info.GsmCountryCode))
                            spnCountyCode.setSelection(i);
                    }
                }

            }

            @Override
            public void onFailure(ErrorMessage errorMessage) {

            }
        });


        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.pickImage(new AbstractActivity.OnImagePicked() {
                    @Override
                    public void onImagePicked(Bitmap bitmap) {
                        imgLogo.setImageBitmap(bitmap);
                        imgLogo.setTag(bitmap);
                        imgUpload.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgLogo.getTag()==null) return;
                txtSave.setVisibility(View.GONE);
                Bitmap bm = (Bitmap) imgLogo.getTag();
                activity.showProgressIndicator(true);
                FileUploader.upload(bm, "", new FileUploader.OnImageUploadedListener() {
                    @Override
                    public void onImageUploaded(String imageUrl) {
                        txtSave.setVisibility(View.VISIBLE);
                        activity.showProgressIndicator(false);
                        if(!imageUrl.equals("Error"))
                        {
                            logoUrl = imageUrl;
                            imgUpload.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });




        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateInputs()) return;

                String username = txtName.getText().toString();
                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();
                String phone = txtPhone.getText().toString();
                String email = txtEmail.getText().toString();
                String code = name2code.get(spnCountyCode.getSelectedItem().toString()).DialCode;

                apiCalls.editProfileInfo(info.Id, firstName, lastName, email, code, phone, logoUrl, username, new CallbackWrapped<GeneralResponse>() {
                    @Override
                    public void onResponse(GeneralResponse response) {
                        toast(response);
                        navigator.goBackTo(MoreFragment.class);
                    }

                    @Override
                    public void onFailure(ErrorMessage errorMessage) {

                    }
                });

            }
        });
    }
}
