package com.example.earthquakeRadar.Views;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import com.example.earthquakeRadar.Utils.LocaleHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.earthquakeRadar.R;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    NavController navController;

    FrameLayout frameLayout;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        context = this;
        resources = context.getResources();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new MainDashboardFragment(), "");
        transaction.commit();
        Objects.requireNonNull(getSupportActionBar()).setTitle(resources.getString(R.string.dashboard));

        navigationView = findViewById(R.id.bottom_navigation_view);
        navigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.page2) {


                MainDashboardFragment fragment = new MainDashboardFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment, "");
                fragmentTransaction.commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle(resources.getString(R.string.dashboard));


            } else if (item.getItemId() == R.id.page1) {

                mapFragment fragment = new mapFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment, "");
                fragmentTransaction.commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle(resources.getString(R.string.map));

            } else if (item.getItemId() == R.id.page3) {

                InfoFragment fragment = new InfoFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment, "");
                fragmentTransaction.commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle(resources.getString(R.string.info));



            }


            return true;
        });


    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }*/

    @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        String lang = LocaleHelper.getLang(this);
        if (lang.equals("de")) {
            menu.findItem(R.id.de).setChecked(true);
        } else {
            //menu.findItem(R.id.eng).setChecked(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        //Toast.makeText(context, "opened", Toast.LENGTH_SHORT).show();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        /*if(item.getItemId() == R.id.eng){
            //Toast.makeText(context, "EN", Toast.LENGTH_SHORT).show();
            item.setChecked(!item.isChecked());


        }else */
        if (item.getItemId() == R.id.de) {
            //Toast.makeText(context, "DE", Toast.LENGTH_SHORT).show();

            item.setChecked(!item.isChecked());
            if (item.isChecked()) {
                LocaleHelper.saveLang(this,"de");
                String lang = "de";
                Locale locale = new Locale(lang);
                Resources resources = context.getResources();
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getApplicationContext().createConfigurationContext(config);
                resources.updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                //recreate();
            } else {
                LocaleHelper.saveLang(this,"en");
                String lang = "en";
                Resources resources = context.getResources();
                Locale locale = new Locale(lang);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getApplicationContext().createConfigurationContext(config);
                resources.updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                //Objects.requireNonNull((((AppCompatActivity) context)).getSupportActionBar());

                // recreate();

            }
        }
        return super.onOptionsItemSelected(item);
    }
}