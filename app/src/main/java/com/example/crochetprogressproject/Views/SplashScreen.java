package com.example.crochetprogressproject.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.crochetprogressproject.R;
import com.example.crochetprogressproject.Utils.LocaleHelper;
import com.example.crochetprogressproject.databinding.ActivitySplashScreenBinding;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    int check = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*String langs = LocaleHelper.getLang(this);
        String lang = "en";

        if (langs.equals("de")) {
            lang = "de";
        }
        */

        binding = ActivitySplashScreenBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        binding.groupLang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.engCheck) {
                    check = 1;

                } else {
                    check = 2;
                }
            }
        });


    }

    public void start(View view) {

        if (check == -1) {
            Toast.makeText(this, "Select Language Option", Toast.LENGTH_SHORT).show();
            return;
        }
        if (check == 1) {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        if (check == 2) {
            Locale locale = new Locale("de");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        binding.layout.setVisibility(View.INVISIBLE);
        binding.progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);
    }
}