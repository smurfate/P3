package com.itland.employer.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.itland.employer.R;
import com.itland.employer.fragments.VacancyPageFragment;
import com.itland.employer.util.FragmentNavigator;

/**
 * Created by Saad on 5/27/2018.
 */

public class VacancyPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private FragmentManager fragmentManager;

    public VacancyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        this.fragmentManager = fm;

    }


    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return VacancyPageFragment.newInstance(VacanciesAdapter.Type.active);
            case 1:
                return VacancyPageFragment.newInstance(VacanciesAdapter.Type.inactive);
            case 2:
                return VacancyPageFragment.newInstance(VacanciesAdapter.Type.expired);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return context.getString(R.string.active);
            case 1:
                return context.getString(R.string.inactive);
            case 2:
                return context.getString(R.string.expired);
        }

        return super.getPageTitle(position);
    }

    public void clear()
    {
        for (Fragment f: fragmentManager.getFragments())
        {
            if(f.getClass().getSimpleName().equals(VacancyPageFragment.class.getSimpleName()))
            {
                fragmentManager.beginTransaction().remove(f).commit();
            }
        }

    }

}
