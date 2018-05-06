package com.itland.irecruitment.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.itland.irecruitment.MainActivity;
import com.itland.irecruitment.R;
import com.itland.irecruitment.entities.Resume;
import com.itland.irecruitment.fragments.ApplicationsFragment;
import com.itland.irecruitment.fragments.MoreFragment;
import com.itland.irecruitment.fragments.ResumeFragment;
import com.itland.irecruitment.fragments.VacanciesFragment;


/**
 * Created by Saad on 6/24/2016.
 */

public class FragmentNavigator {

    private Fragment currentSection;
    private Fragment mainSection;
    private int container;
    private AppCompatActivity activity;
    private BottomNavigationView navigationView;

    private FragmentManager fragmentManager;
    public boolean isBack = false;


    public FragmentNavigator(AppCompatActivity activity, Fragment mainSection, BottomNavigationView navigationView, int container) {
        this.activity = activity;
        this.mainSection = mainSection;
        this.container = container;
        this.fragmentManager = activity.getSupportFragmentManager();
        this.navigationView = navigationView;
        gotoMainSection();
        addStackListener();
    }

    private void addStackListener()
    {
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

                Fragment fragment = getCurrentFragment();
                if (fragment == currentSection) {
                    displayHamburger();
                }



                if(fragment!=null && navigationView != null)
                {
                    if(fragment == mainSection )
                    {
                        navigationView.setSelectedItemId(R.id.navigation_home);
                    }

                    if(VacanciesFragment.class.isInstance(fragment))
                    {
                        navigationView.setSelectedItemId(R.id.navigation_vacancies);
                    }
                    if(ResumeFragment.class.isInstance(fragment))
                    {
                        navigationView.setSelectedItemId(R.id.navigation_resumes);
                    }
                    if(ApplicationsFragment.class.isInstance(fragment))
                    {
                        navigationView.setSelectedItemId(R.id.navigation_applications);
                    }
                    if(MoreFragment.class.isInstance(fragment))
                    {
                        navigationView.setSelectedItemId(R.id.navigation_more);
                    }

                }
            }
        });
    }

    public void gotoSection(Fragment section)
    {
        try
        {
            displayHamburger();
            hideSoftwareKeyboard();

            if(currentSection != mainSection) fragmentManager.popBackStack(currentSection.getClass().getSimpleName(),FragmentManager.POP_BACK_STACK_INCLUSIVE);

            fragmentManager.beginTransaction()
                    .replace(container,section,section.getClass().getSimpleName())
                    .addToBackStack(section.getClass().getSimpleName())
                    .commitAllowingStateLoss();

            currentSection = section;
        }
        catch (Exception e)
        {

        }

    }

    public void gotoMainSection()
    {
        try {
            currentSection = mainSection;
            displayHamburger();

            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            fragmentManager.beginTransaction()
                    .replace(container, mainSection,mainSection.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
        catch (Exception e)
        {

        }
    }

    public void goBackTo(String name)
    {
        try {
            fragmentManager.popBackStack(name,0);
        }
        catch (Exception e)
        {

        }
    }

    public void goBackTo(Class cls)
    {
        try {
            fragmentManager.popBackStack(cls.getSimpleName(),0);
        }
        catch (Exception e)
        {

        }
    }

    public void hideSoftwareKeyboard()
    {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public Fragment getFragment(String name)
    {
        return fragmentManager.findFragmentByTag(name);
    }


    public void gotoSubSection(Fragment subSection)
    {
        hideSoftwareKeyboard();
        displayBack();


        fragmentManager.beginTransaction()
                .replace(container,subSection,subSection.getClass().getSimpleName())
                .addToBackStack(subSection.getClass().getSimpleName())
                .commitAllowingStateLoss();

        subSection.setHasOptionsMenu(true);
    }

    public void switchSubSection(Fragment subSection)
    {
        goBack();
        gotoSubSection(subSection);
    }

    public void restartMainSection(Handler handler)
    {
        fragmentManager.beginTransaction()
                .remove(mainSection)
                .commitAllowingStateLoss();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoMainSection();
            }
        },100);
    }

    public void goBack(int n)
    {
        try {
            for(int i = 0 ; i < n ; i++)
            {
                fragmentManager.popBackStack();
            }
        }
        catch (Exception e)
        {

        }
    }

    public void goBack()
    {
        goBack(1);
    }

    public void displayBack()
    {
        isBack = true;

//        activity.toggle.setDrawerIndicatorEnabled(false);
//        activity.actionBar.setDisplayHomeAsUpEnabled(true);
//
//        activity.toolbar.getNavigationIcon().setColorFilter(activity.getResources().getColor(R.color.bbsf_background), PorterDuff.Mode.SRC_ATOP);
//
//        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN){
//            activity.actionBar.setHomeAsUpIndicator(R.drawable.ic_back_old);
//        } else {
//            activity.actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
//        }
//
//        activity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void displayHamburger()
    {
        isBack = false;
//        activity.actionBar.setDisplayHomeAsUpEnabled(false);
//        activity.toggle.setDrawerIndicatorEnabled(true);
//
//        activity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public void hideToggleButton()
    {
//        activity.toggle.setDrawerIndicatorEnabled(false);
//        activity.actionBar.setDisplayHomeAsUpEnabled(false);
    }


    public Fragment getCurrentFragment()
    {
//        Fragment fr;
//        if(fragmentManager.getBackStackEntryCount()>0)
//        {
//            String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
//            fr = fragmentManager.findFragmentByTag(fragmentTag);
//        }
//        else
//        {
//            fr=fragmentManager.findFragmentById(container);
//
//        }
//
//
//        if(fragmentManager.getFragments() != null && fragmentManager.getFragments().size() > fragmentManager.getBackStackEntryCount())
//        {
//            fr = fragmentManager.getFragments().get(fragmentManager.getBackStackEntryCount());
//        }
        return fragmentManager.getFragments() != null && fragmentManager.getFragments().size()>fragmentManager.getBackStackEntryCount()?fragmentManager.getFragments().get(fragmentManager.getBackStackEntryCount()):null;
//        return fragmentManager.findFragmentById(container);

//        return fragmentManager.findFragmentById(container);
    }

    public Fragment getBackstackFragment(Class cls)
    {
        for(Fragment f:fragmentManager.getFragments())
        {
            if(f.getClass().getSimpleName().equals(cls.getSimpleName()))
            {
                return f;
            }
        }
        return null;
    }

    public boolean isCurrentFragment(Class fragmentClass)
    {
        return getCurrentFragment().getClass().getSimpleName().equals(fragmentClass.getSimpleName());
    }

    public Fragment getCurrentSection(){return currentSection;}


}
