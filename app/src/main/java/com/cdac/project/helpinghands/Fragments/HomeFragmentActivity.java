package com.cdac.project.helpinghands.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cdac.project.helpinghands.R;

public class HomeFragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ActionBar actionBar=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // actionBar.setIcon(R.drawable.img);
        actionBar.setHomeButtonEnabled(true);
        //actionBar.setHomeAsUpIndicator(R.drawable.img);
        actionBar.setTitle("Helping Hands");

//

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.container, new MyAccountFragment());
        ft.commit();
        actionBar.setTitle("My Account");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_edit) {
//            return true;
//        }
//        if (id == R.id.action_save) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myaccount) {
            MyAccountFragment myAccountFragment=new MyAccountFragment();
            FragmentManager fm=getSupportFragmentManager();

            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.container, myAccountFragment);
            ft.commit();
            actionBar.setTitle("My Account");
            // Handle the camera action
        } else if (id == R.id.nav_mydonations) {
            DonateFragment donateFragment=new DonateFragment();
            FragmentManager fm=getSupportFragmentManager();

            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.container,donateFragment);

            ft.commit();
            actionBar.setTitle("Donate");

        } else if (id == R.id.nav_donate) {
            NewDonationFragment newDonationFragment=new NewDonationFragment();
            FragmentManager fm=getSupportFragmentManager();

            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.container,newDonationFragment);
            ft.commit();

            actionBar.setTitle("New Donation");

        } else if (id == R.id.nav_consume) {
            ConsumeFragment consumeFragment=new ConsumeFragment();
            FragmentManager fm=getSupportFragmentManager();

            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.container,consumeFragment);
            ft.commit();

            actionBar.setTitle("Consume");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
