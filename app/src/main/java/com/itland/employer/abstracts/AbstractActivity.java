package com.itland.employer.abstracts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.itland.employer.MainActivity;
import com.itland.employer.R;
import com.itland.employer.util.PrefUtil;
import com.itland.employer.util.SharedPreferencesKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Saad on 5/1/2018.
 */

public abstract class AbstractActivity extends AppCompatActivity {


    public enum Lang { ar,en }

    private OnImagePicked onImagePicked;
    private int PICK_IMAGE_CODE = 22;

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

        PrefUtil.setStringPreference(SharedPreferencesKeys.language,lang.name());

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
        return PrefUtil.getStringPreference(SharedPreferencesKeys.language).equals(Lang.ar.name())?Lang.ar:Lang.en;
    }

    public void pickImage(OnImagePicked onImagePicked)
    {
        this.onImagePicked = onImagePicked;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_CODE && resultCode == Activity.RESULT_OK && data != null)
        {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                onImagePicked.onImagePicked(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public interface OnImagePicked{
        public void onImagePicked(Bitmap bitmap);
    }
}
