package com.itland.irecruitment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itland.irecruitment.R;
import com.itland.irecruitment.entities.Resume;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Saad on 5/15/2018.
 */

public class AutocompleteAdapter extends BaseAdapter {

    private List<Resume> resumeList;

    public AutocompleteAdapter(List<Resume> resumeList) {
        this.resumeList = resumeList;
    }

    @Override
    public int getCount() {
        return resumeList.size();
    }

    @Override
    public String getItem(int position) {
        return resumeList.get(position).JobTitle;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autocomplete, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        Resume resume = resumeList.get(position);
        viewHolder.txtVacancy.setText(resume.JobTitle);
        viewHolder.txtCity.setText(resume.SeekerFullName);

        return null;
    }

    static class ViewHolder {

        @Bind(R.id.txtVacancyName) TextView txtVacancy;
        @Bind(R.id.imgProfile) ImageView imgProfile;
        @Bind(R.id.txtCity) TextView txtCity;

        private ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
