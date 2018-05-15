package com.itland.irecruitment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.entities.Vacancy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Saad on 5/2/2018.
 */

public class VacanciesAdapter extends BaseAdapter {

    private List<Vacancy> vacancies;
    private Type type;

    public enum Type { active, inactive, expired}

    public VacanciesAdapter(List<Vacancy> vacancies,Type type) {
        this.type = type;

        this.vacancies = new ArrayList<>();
        for(Vacancy v : vacancies)
        {
            if(type==Type.active && v.isActive()) this.vacancies.add(v);

            if(type==Type.inactive && v.isInactive()) this.vacancies.add(v);

            if(type==Type.expired && v.isExpired()) this.vacancies.add(v);

        }

    }

    @Override
    public int getCount() {
        return vacancies.size();
    }

    @Override
    public Vacancy getItem(int position) {
        return vacancies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).Id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vacancy, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        Vacancy vacancy = getItem(position);

        viewHolder.imgProfile.setVisibility(View.GONE);

        viewHolder.txtVacancyName.setText(vacancy.Title);
        viewHolder.txtCompany.setVisibility(View.INVISIBLE);
        viewHolder.txtCompanyType.setText(vacancy.WorkingCity+", "+vacancy.WorkingCountry);
        viewHolder.txtDaysBegining.setText(vacancy.PostedSince.toString());

        switch (type)
        {
            case active:
                viewHolder.lnrViews.setVisibility(View.VISIBLE);
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.txtViews.setText(vacancy.ViewsCount.toString());
                viewHolder.txtApplication.setText(vacancy.ApplicationsCount.toString());
                viewHolder.lnrExpired.setVisibility(View.VISIBLE);
                viewHolder.txtDaysExpiring.setText(vacancy.ExpiresIn.toString());

                break;
            case inactive:
                viewHolder.lnrViews.setVisibility(View.GONE);
                viewHolder.line.setVisibility(View.GONE);
                viewHolder.lnrExpired.setVisibility(View.GONE);

                break;
            case expired:
                viewHolder.lnrViews.setVisibility(View.VISIBLE);
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.txtViews.setText(vacancy.ViewsCount.toString());
                viewHolder.txtApplication.setText(vacancy.ApplicationsCount.toString());
                viewHolder.lnrExpired.setVisibility(View.VISIBLE);
                viewHolder.txtDaysExpiring.setText(vacancy.ExpiredSince.toString());

                break;
        }


        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.imgProfile) ImageView imgProfile;
        @Bind(R.id.txtVacancyName) TextView txtVacancyName;
        @Bind(R.id.txtCompany) TextView txtCompany;
        @Bind(R.id.txtCompanyType) TextView txtCompanyType;
        @Bind(R.id.txtActive) TextView txtActive;
        @Bind(R.id.txtDaysBegining) TextView txtDaysBegining;
        @Bind(R.id.txtDaysExpiring) TextView txtDaysExpiring;
        @Bind(R.id.txtViews) TextView txtViews;
        @Bind(R.id.txtApplication) TextView txtApplication;
        @Bind(R.id.lnrActive) LinearLayout lnrActive;
        @Bind(R.id.lnrViews) LinearLayout lnrViews;
        @Bind(R.id.lnrExpired) LinearLayout lnrExpired;
        @Bind(R.id.line) View line;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
