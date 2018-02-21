package com.example.sc1111.finastrahack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class LandingPageActivity extends NavigationActivity {

    private static final String TAG = "LandingPageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        setView();
        Log.d(TAG, "ID: " + "NAV");

    }

    //TODO: better implementation
    private String getName(){
        return "John Smith";
    }

    //TODO: better implementation
    private String getLoanAmt(){
        float loanamt = 40000;
        return "$"+ String.valueOf(loanamt);
    }
}
