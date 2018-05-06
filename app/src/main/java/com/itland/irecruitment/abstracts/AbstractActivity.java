package com.itland.irecruitment.abstracts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.itland.irecruitment.MainActivity;
import com.itland.irecruitment.R;
import com.itland.irecruitment.util.PrefUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Saad on 5/1/2018.
 */

public abstract class AbstractActivity extends AppCompatActivity {

    public enum Lang { ar,en }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrefUtil.createSharedPreference(this);
    }

    public boolean isNullOrEmpty(String str)
    {
        return str == null || str.equals("");
    }

    public void hideSoftwareKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void snack(String message)
    {
        Snackbar.make(findViewById(R.id.frmContent), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void toast(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    public void log(String log)
    {
        Log.d("Debug",getClass().getSimpleName()+": "+log);
    }


    public <T> List<T> array2List(T[] array)
    {
        List<T> list = new ArrayList<>(array.length);
        for (int i=0;i<array.length;i++)
            list.add(i,array[i]);
        return list;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setLocale(Lang lang,boolean start) {

        PrefUtil.setStringPreference("Lang",lang.name());

        Locale locale = new Locale(lang.name());
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);
        Resources.getSystem().updateConfiguration(config, null);

        if(start)
        {
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
            finish();
        }
    }


    public Lang getLocale()
    {
        return PrefUtil.getStringPreference("Lang").equals(Lang.ar.name())?Lang.ar:Lang.en;
    }



}
