package com.myapp.warest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.google.firebase.auth.*;
import com.google.firebase.storage.*;

import android.widget.*;



public class AdminActivity extends AppCompatActivity
{
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    FragmentManager fragmentManager;
    NavigationView navigationView;
    FrameLayout frameLayout;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://warest-77e4b.appspot.com/");



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminactivity);

        fragmentManager = getSupportFragmentManager();

        setupView();
    }

    private void setupView()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {


                if (menuItem.getItemId() == R.id.logout)
                {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(AdminActivity.this, StartScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP ));
                    finish();
                }
                if(menuItem.getItemId()==R.id.studentview) {
                    startActivity(new Intent(AdminActivity.this, StudentViewActivity.class));
                }
                if(menuItem.getItemId()==R.id.facultyview){
                    startActivity(new  Intent(AdminActivity.this, FacultyViewActivity.class));
                }
                if(menuItem.getItemId()==R.id.organview){
                    startActivity(new  Intent(AdminActivity.this, OrganViewActivity.class));
                }

                return true;
            }
        });
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        drawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void onBackPressed() {
        //  super.onBackPressed();
        moveTaskToBack(true);

    }
}
