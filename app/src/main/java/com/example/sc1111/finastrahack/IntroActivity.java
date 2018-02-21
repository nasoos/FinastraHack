package com.example.sc1111.finastrahack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);
    }
}
