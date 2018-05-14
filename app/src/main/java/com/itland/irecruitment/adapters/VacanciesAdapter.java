package com.itland.irecruitment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.entities.Vacancy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Saad on 5/2/2018.
 */

public class VacanciesAdapter extends BaseAdapter {

    private List<Vacancy> vacancies;

    public VacanciesAdapter(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
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
        return position;
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
        viewHolder.txtViews.setText(vacancy.ViewsCount);


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

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
