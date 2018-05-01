package com.itland.irecruitment.util;

import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import com.itland.irecruitment.MainActivity;


/**
 * Created by Saad on 6/24/2016.
 */

public class FragmentNavigator {

    private Fragment currentSection;
    private Fragment mainSection;
    private int container;
    private MainActivity activity;

    private FragmentManager fragmentManager;
    public boolean isBack = false;


    public FragmentNavigator(MainActivity activity,Fragment mainSection,int container) {
        this.activity = activity;
        this.mainSection = mainSection;
        this.container = container;
        this.fragmentManager = activity.getSupportFragmentManager();
        gotoMainSection();
        addStackListener();
    }

    private void addStackListener()
    {
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getCurrentFragment() == currentSection) {
                    displayHamburger();
                }
            }
        });
    }

    public void gotoSection(Fragment section)
    {
        try
        {
            displayHamburger();
            activity.hideSoftwareKeyboard();

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

    public Fragment getFragment(String name)
    {
        return fragmentManager.findFragmentByTag(name);
    }


    public void gotoSubSection(Fragment subSection)
    {
        activity.hideSoftwareKeyboard();
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

    public boolean isCurrentFragment(Class fragmentClass)
    {
        return getCurrentFragment().getClass().getSimpleName().equals(fragmentClass.getSimpleName());
    }

    public Fragment getCurrentSection(){return currentSection;}


}
