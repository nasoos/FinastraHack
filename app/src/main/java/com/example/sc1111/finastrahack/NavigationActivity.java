package com.example.sc1111.finastrahack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public boolean isSecured = false;

    private UserData userData;
    private Intent intent;
    private Menu nav_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userData = (UserData) getIntent().getSerializableExtra("userData");

        //TODO: Implement error checking for userData == null
        if (userData == null){
            isSecured = false;
        }
        else{
            isSecured = true;
        }
    }

    public void setView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        };

        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headernav = navigationView.getHeaderView(0);
        nav_menu = navigationView.getMenu();
        TextView nameText = (TextView) headernav.findViewById(R.id.userDataName);
        TextView emailText = (TextView) headernav.findViewById(R.id.userDataEmail);

        if (isSecured) {
            //TODO: Replace navbar header text with database variables
            nameText.setText("temp name text");
            emailText.setText("temp email text");
            nav_menu.add(0, Menu.FIRST, Menu.FIRST, "Home").setIcon(R.drawable.ic_menu_home);
            nav_menu.add(1, Menu.FIRST + 1, Menu.FIRST, "Trends").setIcon(R.drawable.ic_menu_trends);
            nav_menu.add(2, Menu.FIRST + 2, Menu.FIRST, "Contact Us").setIcon(R.drawable.ic_menu_mail);
            nav_menu.add(3, Menu.FIRST + 3, Menu.FIRST, "Settings").setIcon(R.drawable.ic_menu_settings);
            nav_menu.add(4, Menu.FIRST + 4, Menu.FIRST, "Sign Out").setIcon(R.drawable.ic_menu_signout);
        }
        else {
            nameText.setText("Login");
            emailText.setText("");
            nav_menu.add(0, Menu.FIRST, Menu.FIRST, "Trends").setIcon(R.drawable.ic_menu_trends);
            nav_menu.add(1, Menu.FIRST + 1, Menu.FIRST, "Contact Us").setIcon(R.drawable.ic_menu_mail);
            nav_menu.add(2, Menu.FIRST + 2, Menu.FIRST, "Settings").setIcon(R.drawable.ic_menu_settings);
        }

    }

    public void onLoginClick(View v){
        if (!isSecured){
            intent = new Intent(NavigationActivity.this, LoginActivity.class);
            navigate();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (isSecured) {
            if (id == nav_menu.getItem(0).getItemId()) {
                intent = new Intent(NavigationActivity.this, LandingPageActivity.class);
                navigate();
            } else if (id == nav_menu.getItem(1).getItemId()) {
                intent = new Intent(NavigationActivity.this, burn_out.class);
                navigate();
            } else if (id == nav_menu.getItem(2).getItemId()) {
                contactUs();
            } else if (id == nav_menu.getItem(3).getItemId()) {
                intent = new Intent(NavigationActivity.this, SettingsActivity.class);
                intent.putExtra("userData", userData);
                startActivity(intent);
            }else if (id == nav_menu.getItem(4).getItemId()) {
                //TODO: Replace with better logic
                intent = new Intent(NavigationActivity.this, IntroActivity.class);
                userData = null;
                navigate();
            }
        }
        else {
            if (id == nav_menu.getItem(0).getItemId()) {
                intent = new Intent(NavigationActivity.this, burn_out.class);
                navigate();
            } else if (id == nav_menu.getItem(1).getItemId()) {
                contactUs();
            } else if (id == nav_menu.getItem(2).getItemId()) {
                intent = new Intent(NavigationActivity.this, SettingsActivity.class);
                intent.putExtra("userData", userData);
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void navigate(){
        intent.putExtra("userData", userData);
        if (intent != null){
            new CountDownTimer(100, 10) {
                public void onFinish() {
                    startActivity(intent);
                    finish();
                    overridePendingTransition(0, 0);
                }
                public void onTick(long millisUntilFinished) {
                    // millisUntilFinished    The amount of time until finished.
                }
            }.start();
        }
    }

    private void contactUs(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "emailaddress@domain.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Line");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(intent, ""));
    }
}
