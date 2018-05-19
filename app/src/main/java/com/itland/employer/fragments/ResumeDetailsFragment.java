package com.itland.employer.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.abstracts.AbstractFragment;
import com.itland.employer.entities.EducationDegree;
import com.itland.employer.entities.Language;
import com.itland.employer.entities.Reference;
import com.itland.employer.entities.ResumeDetails;
import com.itland.employer.entities.Skill;
import com.itland.employer.entities.WorkExperience;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResumeDetailsFragment extends AbstractFragment {

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Bind(R.id.txtPhone) TextView txtPhone;
    @Bind(R.id.txtEmail) TextView txtEmail;
    @Bind(R.id.txtAddress) TextView txtAddress;
    @Bind(R.id.txtLandline) TextView txtLandLine;
    @Bind(R.id.txtSite) TextView txtSite;
    @Bind(R.id.txtMelitary) TextView txtMilitary;
    @Bind(R.id.txtDrivingLicense) TextView txtDrivingLicense;
    @Bind(R.id.txtDependancyNumber) TextView txtDependacyNumber;
    @Bind(R.id.txtFullAddress) TextView txtFullAddress;
    @Bind(R.id.txtGSMCode) TextView txtGSMCode;
    @Bind(R.id.txtObjective) TextView txtObjective;
    @Bind(R.id.txtJobType) TextView txtJobType;
    @Bind(R.id.txtJobCompany) TextView txtJopCategory;
    @Bind(R.id.txtCareerLevel) TextView txtCareerLevel;
    @Bind(R.id.txtWorkCounty) TextView txtWorkCountry;
    @Bind(R.id.txtMotherLanguage) TextView txtMotherLanguage;
    @Bind(R.id.txtWorkCity) TextView txtWorkCity;
    @Bind(R.id.txtWorkPeriod) TextView txtWorkPeriod;
    @Bind(R.id.txtDesiredSalary) TextView txtDesiredSalary;
    @Bind(R.id.txtLastMonthSalary) TextView txtLastMonthSalary;
    @Bind(R.id.lnrExperience) LinearLayout lnrExperience;
    @Bind(R.id.lnrReference) LinearLayout lnrReference;
    @Bind(R.id.lnrSkills) LinearLayout lnrSkill;
    @Bind(R.id.lnrLanguage) LinearLayout lnrLanguage;

    private ResumeDetails details;

    public ResumeDetailsFragment() {
        // Required empty public constructor
    }

    public static ResumeDetailsFragment newInstance(ResumeDetails details) {
        ResumeDetailsFragment fragment = new ResumeDetailsFragment();
        fragment.details = details;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resume_details, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        toolbar.setTitle(details.Name);
        setTitle(details.JobTarget.Title);

        txtPhone.setText(details.PersonalDetails.MobilePhone);
        txtEmail.setText(details.PersonalDetails.Email);
        txtAddress.setText(details.PersonalDetails.City+", "+details.PersonalDetails.Country);
        txtLandLine.setText(details.PersonalDetails.PhoneNumber);
        txtSite.setText(details.PersonalDetails.UploadedResumeUrl);
        txtMilitary.setText(details.PersonalDetails.MilitaryServiceStatus);
        txtDrivingLicense.setText(details.PersonalDetails.DrivingLicenceType);
        txtDependacyNumber.setText(details.PersonalDetails.NumberOfDependencies.toString());
        txtFullAddress.setText(details.PersonalDetails.Address);
        txtGSMCode.setText(details.PersonalDetails.MobilePhone);
        txtObjective.setText(details.JobTarget.CareerObjectives);
        txtJobType.setText(details.JobTarget.JobType);
        txtJopCategory.setText(details.JobTarget.Category);
        txtCareerLevel.setText(details.JobTarget.CareerLevel);
        txtWorkCountry.setText(details.JobTarget.City);
        txtWorkPeriod.setText(details.JobTarget.NoticePeriod);
        txtDesiredSalary.setText(details.JobTarget.DesiredNetSalary + " " + details.JobTarget.DesiredNetSalaryCurrency);
        txtLastMonthSalary.setText(details.JobTarget.LastMonthSalary + " " + details.JobTarget.LastMonthSalaryCurrency);
        txtMotherLanguage.setText(details.PersonalDetails.MotherLanguage);
        txtWorkCity.setText(details.JobTarget.City);


        for(WorkExperience experience : details.WorkExperiences)
        {
            WorkExperienceViewHolder holder = new WorkExperienceViewHolder(activity);
            holder.txtCompanyName.setText(experience.CompanyName);
            holder.txtCompanyType.setText(experience.FieldOfWork);
            String endDate = (experience.EndDate!=null)?experience.EndDate:getString(R.string.present);
            holder.txtSince.setText(experience.StartDate + "-" + endDate);
            holder.txtYearsOfExperience.setText(experience.YearsOfExperience.toString());
            lnrExperience.addView(holder.getView());
        }

        for(Reference reference : details.References)
        {
            ReferenceViewHolder holder = new ReferenceViewHolder(activity);
            holder.txtFullName.setText(reference.FullName);
            holder.txtCompanyName.setText(reference.CompanyName);
            holder.txtPhone.setText(reference.Phone);
            holder.txtEmail.setText(reference.Email);
            holder.txtJobTitle.setText(reference.JobTitle);
            lnrReference.addView(holder.getView());
        }

        for (Skill skill : details.Skills)
        {
            SkillViewHolder holder = new SkillViewHolder(activity);
            holder.txtName.setText(skill.Name);
            holder.txtLevel.setText(skill.Level);
            holder.txtYearsOfExperience.setText(skill.YearsOfExperiences.toString());
            holder.txtLastUsedYear.setText("Last used:" + skill.LastUsedYear.toString());
            lnrSkill.addView(holder.getView());
        }

        for (Language language : details.Languages)
        {
            LanguageViewHolder holder = new LanguageViewHolder(activity);
            holder.txtLanguageName.setText(language.LanguageName);
            holder.txtReading.setText(language.ReadingLevel);
            holder.txtWriting.setText(language.WritingLevel);
            holder.txtSpeaking.setText(language.SpeakingLevel);
            lnrLanguage.addView(holder.getView());
        }

        for (EducationDegree degree : details.EducationDegrees)
        {
            EducationDegreeViewHolder holder = new EducationDegreeViewHolder(activity);
            holder.txtName.setText(degree.Name);
            holder.txtDescription.setText(degree.Description);
            holder.txtGraduationYear.setText(degree.GraduationYear.toString());
            holder.txtInstitution.setText(degree.EducationInstitution);
        }

    }

    static class WorkExperienceViewHolder {

        @Bind(R.id.txtCompanyName) TextView txtCompanyName;
        @Bind(R.id.txtCompanyType) TextView txtCompanyType;
        @Bind(R.id.txtSince) TextView txtSince;
        @Bind(R.id.txtYearsOfExperience) TextView txtYearsOfExperience;

        private View view;

        private WorkExperienceViewHolder(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.item_work_experience, null, false);
            ButterKnife.bind(this, view);
        }

        public View getView()
        {
            return view;
        }

    }

    static class ReferenceViewHolder {

        @Bind(R.id.txtFullName) TextView txtFullName;
        @Bind(R.id.txtCompanyName) TextView txtCompanyName;
        @Bind(R.id.txtPhone) TextView txtPhone;
        @Bind(R.id.txtJobTitle) TextView txtJobTitle;
        @Bind(R.id.txtEmail) TextView txtEmail;

        private View view;

        private ReferenceViewHolder(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.item_reference, null, false);
            ButterKnife.bind(this, view);
        }
        public View getView()
        {
            return view;
        }


    }

    static class SkillViewHolder {

        @Bind(R.id.txtName) TextView txtName;
        @Bind(R.id.txtLevel) TextView txtLevel;
        @Bind(R.id.txtLastUsedYear) TextView txtLastUsedYear;
        @Bind(R.id.txtYarsOfExperience) TextView txtYearsOfExperience;

        private View view;

        private SkillViewHolder(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.item_skill, null, false);
            ButterKnife.bind(this, view);
        }
        public View getView()
        {
            return view;
        }


    }

    static class LanguageViewHolder {

        @Bind(R.id.txtLanguageName) TextView txtLanguageName;
        @Bind(R.id.txtReading) TextView txtReading;
        @Bind(R.id.txtWriting) TextView txtWriting;
        @Bind(R.id.txtSpeaking) TextView txtSpeaking;

        private View view;

        private LanguageViewHolder(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.item_language, null, false);
            ButterKnife.bind(this, view);
        }
        public View getView()
        {
            return view;
        }


    }

    static class EducationDegreeViewHolder {

        @Bind(R.id.txtGraduationYear) TextView txtGraduationYear;
        @Bind(R.id.txtName) TextView txtName;
        @Bind(R.id.txtInstitution) TextView txtInstitution;
        @Bind(R.id.txtDescription) TextView txtDescription;

        private View view;

        private EducationDegreeViewHolder(Context context) {
            view = LayoutInflater.from(context).inflate(R.layout.item_education_degree, null, false);
            ButterKnife.bind(this, view);
        }
        public View getView()
        {
            return view;
        }


    }

}
