package com.itland.irecruitment.abstracts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.itland.irecruitment.MainActivity;
import com.itland.irecruitment.R;
import com.itland.irecruitment.api.ApiCalls;
import com.itland.irecruitment.api.ErrorMessage;
import com.itland.irecruitment.util.FragmentNavigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saad on 5/1/2018.
 */

public abstract class AbstractFragment extends Fragment {

    public MainActivity activity;
    public FragmentNavigator navigator;
    public ApiCalls apiCalls;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) getActivity();
        apiCalls = activity.apiCalls;
        navigator = activity.navigator;
        setHasOptionsMenu(true) ;

    }

    public boolean isSafe()
    {
        return getView() != null && isAdded();
    }


    public boolean isNullOrEmpty(String str)
    {
        return str == null || str.equals("");
    }

    public void toast(String message)
    {
        if(isSafe()) Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

    public void toast(ErrorMessage message)
    {
        if(isSafe()) Toast.makeText(getActivity(),message.toString(),Toast.LENGTH_LONG).show();
    }

    public void log(String log)
    {
        Log.d("Debug",getClass().getSimpleName()+": "+log);
    }

    public void snack(String message)
    {
        if(isSafe()) Snackbar.make(getActivity().findViewById(R.id.frmContent), message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
    }

    public <T> List<T> array2List(T[] array)
    {
        List<T> list = new ArrayList<>(array.length);
        for (int i=0;i<array.length;i++)
            list.add(i,array[i]);
        return list;
    }


    public void hideSoftwareKeyboard()
    {
        View view = getActivity().getCurrentFocus();
        if (view != null && isSafe()) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setTitle(String title)
    {
        activity.setTitle(title);
    }

    public void progress(boolean show)
    {
        activity.showProgressIndicator(show);
    }

}
