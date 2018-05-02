package com.itland.irecruitment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.entities.Application;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Saad on 5/2/2018.
 */

public class ApplicationsAdapter extends BaseAdapter {

    private List<Application> applications;

    public ApplicationsAdapter(List<Application> applications) {
        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @Override
    public Application getItem(int position) {
        return applications.get(position);
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_application, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        Application application = getItem(position);

        viewHolder.txtVacancy.setText("Job Vacancy title");
        viewHolder.txtCity.setText("Damascus, Syria");
        viewHolder.txtDays.setText("14 days ago");
        viewHolder.txtRead.setText("Unread");
        viewHolder.txtName.setText("Sami Samara");


        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.txtVacancyTitle) TextView txtVacancy;
        @Bind(R.id.txtDays) TextView txtDays;
        @Bind(R.id.txtCity) TextView txtCity;
        @Bind(R.id.txtRead) TextView txtRead;
        @Bind(R.id.txtName) TextView txtName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
