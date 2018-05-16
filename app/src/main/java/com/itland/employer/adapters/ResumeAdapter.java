package com.itland.employer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itland.employer.R;
import com.itland.employer.entities.Resume;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Saad on 5/1/2018.
 */

public class ResumeAdapter extends BaseAdapter {

    private List<Resume> resumeList;

    public ResumeAdapter(List<Resume> resumeList) {
        this.resumeList = resumeList;
    }

    @Override
    public int getCount() {
        return resumeList.size();
    }

    @Override
    public Resume getItem(int position) {
        return resumeList.get(position);
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resume, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        Resume resume = getItem(position);

        viewHolder.tvTitle.setText(resume.SeekerFullName);
        viewHolder.tvJob.setText(resume.JobTitle);


        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.imgEnLogo) ImageView imgProfile;
        @Bind(R.id.tvName) TextView tvTitle;
        @Bind(R.id.tvJob) TextView tvJob;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
