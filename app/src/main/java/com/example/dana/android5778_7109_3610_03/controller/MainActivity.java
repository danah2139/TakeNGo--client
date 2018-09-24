package com.example.dana.android5778_7109_3610_03.controller;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.text.Layout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.dana.android5778_7109_3610_03.R;
import com.example.dana.android5778_7109_3610_03.model.backend.MyReceiver;
import com.example.dana.android5778_7109_3610_03.model.backend.MyService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intent=new IntentFilter();
        MyReceiver receiver=new MyReceiver();//create variable in type the exist class "Receiver"
        intent.addAction("INVITATION_SET");
        Intent serviceIntent=new Intent(getBaseContext(), MyService.class);//create intent in type MyService
        startService(serviceIntent);//that run an the background
        registerReceiver(receiver,intent);//register this app as receiver of the service
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //noinspection deprecation
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
        getMenuInflater().inflate(R.menu.main, menu);
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
        FragmentManager manager = getSupportFragmentManager();

        if (id == R.id.nav_info) {
            OptionOneFragment optionOneFragment = new OptionOneFragment();
            manager.beginTransaction().replace(R.id.content_main, optionOneFragment).commit();

        } else if (id == R.id.nav_add_order) {
            OptionTwoFragment optionTwoFragment = new OptionTwoFragment();
            manager.beginTransaction().replace(R.id.content_main, optionTwoFragment).commit();

        } else if (id == R.id.nav_show_available_cars) {
            OptionThreeFragment optionThreeFragment = new OptionThreeFragment();
            manager.beginTransaction().replace(R.id.content_main,optionThreeFragment).commit();

        } else if (id == R.id.nav_close_order) {
            OptionFourFragment optionFourFragment = new OptionFourFragment();
            manager.beginTransaction().replace(R.id.content_main,optionFourFragment).commit();

        } else if (id == R.id.nav_exit) {
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
