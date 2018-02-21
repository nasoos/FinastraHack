package com.example.sc1111.finastrahack.models;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sc1111.finastrahack.LandingPageActivity;
import com.example.sc1111.finastrahack.LoginActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(i);
        overridePendingTransition(0,0);
    }
}
