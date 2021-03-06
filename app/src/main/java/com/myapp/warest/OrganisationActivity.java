package com.myapp.warest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.firebase.auth.*;
import android.net.*;
import android.app.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.*;
import com.google.android.gms.tasks.*;
import android.support.annotation.*;
import android.widget.*;



public class OrganisationActivity extends AppCompatActivity
{
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    Button updateprofile;
    private FirebaseAuth auth;

    FragmentManager fragmentManager;
    NavigationView navigationView;
    FrameLayout frameLayout;



    //Uri filePath;
    ProgressDialog pd;

   // FirebaseStorage storage = FirebaseStorage.getInstance();
   // StorageReference storageRef = storage.getReferenceFromUrl("gs://warest-77e4b.appspot.com/");



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation);

        fragmentManager = getSupportFragmentManager();
        auth = FirebaseAuth.getInstance();

        setupView();
    }

    private void setupView()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        updateprofile=(Button) findViewById(R.id.Updateorgprofile);
       // videoProfile=(Button)findViewById(R.id.videoProfile);

        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");




    /*    updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
                mDatabase.keepSynced(true);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onCancelled(DatabaseError p1) {

                    }

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {


                            if (child.child("email").getValue().toString().equals(auth.getCurrentUser().getEmail())) {






                                if (child.child("communitySub").getValue().toString().equals("College/University")) {

                                    // editor.putString("type", "Student");
                                    // editor.commit();

                                    //progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(OrganisationActivity.this, CollegeProfile.class);
                                    startActivity(intent);
                                    finish();
                                } else if(child.child("communitySub").getValue().toString().equals("Corporate"))  {


                                    // editor.putString("type", "Faculty");
                                    // editor.commit();



                                    //progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(OrganisationActivity.this, CorporateProfile.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else   {


                                    // editor.putString("type", "Faculty");
                                    // editor.commit();



                                    //progressBar.setVisibility(View.GONE);
                                     Intent intent = new Intent(OrganisationActivity.this, TrainingProfile.class);
                                     startActivity(intent);
                                    finish();
                                }





                            }

                        }
                    }
                });
                // startActivity(new Intent(FacultyActivity.this, SelectFacultyType.class));
            }
        });*/


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {
                //selectDrawerItem(menuItem);
                if (menuItem.getItemId() == R.id.contactus)
                {
                    startActivity(new Intent(OrganisationActivity.this, ContactusActivity.class));

                }
                if (menuItem.getItemId() == R.id.logout)
                {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(OrganisationActivity.this, StartScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP ));
                    finish();
                }
                if(menuItem.getItemId()==R.id.aboutus) {
                    startActivity(new Intent(OrganisationActivity.this, AboutusActivity.class));
                }
                if(menuItem.getItemId()==R.id.Updateorgprofile) {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
                    mDatabase.keepSynced(true);
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onCancelled(DatabaseError p1) {

                        }

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {


                                if (child.child("email").getValue().toString().equals(auth.getCurrentUser().getEmail())) {






                                    if (child.child("communitySub").getValue().toString().equals("College/University")) {

                                        // editor.putString("type", "Student");
                                        // editor.commit();

                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(OrganisationActivity.this, CollegeProfile.class);
                                        startActivity(intent);
                                        finish();
                                    } else if(child.child("communitySub").getValue().toString().equals("Corporate"))  {


                                        // editor.putString("type", "Faculty");
                                        // editor.commit();



                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(OrganisationActivity.this, CorporateProfile.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    else   {


                                        // editor.putString("type", "Faculty");
                                        // editor.commit();



                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(OrganisationActivity.this, TrainingProfile.class);
                                        startActivity(intent);
                                        finish();
                                    }





                                }

                            }
                        }
                    });
                }

                if(menuItem.getItemId()==R.id.vieworgprofile) {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
                    mDatabase.keepSynced(true);
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onCancelled(DatabaseError p1) {

                        }

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {


                                if (child.child("email").getValue().toString().equals(auth.getCurrentUser().getEmail())) {


                                    //SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(FacultyActivity.this);
                                    //SharedPreferences.Editor editor = app_preferences.edit();



                                    if (child.child("communitySub").getValue().toString().equals("College/University")) {

                                        // editor.putString("type", "Student");
                                        // editor.commit();

                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(OrganisationActivity.this, ViewCollegeProfile.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    else if (child.child("communitySub").getValue().toString().equals("Corporate")) {

                                        // editor.putString("type", "Student");
                                        // editor.commit();

                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(OrganisationActivity.this, ViewCorporateProfile.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else   {


                                        // editor.putString("type", "Faculty");
                                        // editor.commit();



                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(OrganisationActivity.this, ViewTrainingProfile.class);
                                        startActivity(intent);
                                        finish();
                                    }





                                }

                            }
                        }
                    });
                }




                return true;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode==RESULT_CANCELED)
        {
            // action cancelled
        }
        if(resultCode==RESULT_OK)
        {
            // Create a storage reference
            Uri uri = data.getData();
            pd.show();
           // StorageReference riversRef = storageRef.child("profilevideos/"+FirebaseAuth.getInstance().getCurrentUser().getEmail());
           // UploadTask uploadTask = riversRef.putFile(uri);

            // Register observers to listen for when the download is done or if it fails
           /* uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    pd.dismiss();
                    Toast.makeText(OrganisationActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    pd.dismiss();
                    Toast.makeText(OrganisationActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setToolbarElevation(boolean specialToolbarBehaviour)
    {
        if (specialToolbarBehaviour)
        {
            toolbar.setElevation(0.0f);
            frameLayout.setElevation(getResources().getDimension(R.dimen.elevation_toolbar));
        }
        else
        {
            toolbar.setElevation(getResources().getDimension(R.dimen.elevation_toolbar));
            frameLayout.setElevation(0.0f);
        }
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

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }


}
