package com.example.sc1111.finastrahack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LandingPageActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        setView();
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
