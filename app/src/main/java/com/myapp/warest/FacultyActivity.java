package com.myapp.warest;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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



public class FacultyActivity extends AppCompatActivity
{
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    Button updateprofile,videoProfile, uploadtutorial,uploadnotes;
    private FirebaseAuth auth;

    FragmentManager fragmentManager;
    NavigationView navigationView;
    FrameLayout frameLayout;



    Uri filePath;
    ProgressDialog pd;

    //creating reference to firebase storage
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://warest-77e4b.appspot.com/");



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        auth = FirebaseAuth.getInstance();

        fragmentManager = getSupportFragmentManager();

        setupView();
        // if (savedInstanceState == null) showHome();
    }

    private void setupView()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        updateprofile=(Button) findViewById(R.id.Updatestudentprofile);
        videoProfile=(Button)findViewById(R.id.videoProfile);
        uploadtutorial=(Button)findViewById(R.id.uploadtutorial);
        uploadnotes=(Button)findViewById(R.id.uploadnotes);

        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");

       /* videoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("video/mp4");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select a file"), 101);
            }
        });*/


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


                                //SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(FacultyActivity.this);
                                //SharedPreferences.Editor editor = app_preferences.edit();



                                if (child.child("communitySub").getValue().toString().equals("Freelance")) {

                                   // editor.putString("type", "Student");
                                   // editor.commit();

                                    //progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(FacultyActivity.this, UpdateFreelancerProfile.class);
                                    startActivity(intent);
                                    finish();
                                } else   {


                                   // editor.putString("type", "Faculty");
                                   // editor.commit();



                                    //progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(FacultyActivity.this, UpdateTraineeProfile.class);
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


      /*  uploadnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacultyActivity.this, UploadTutorialActivity.class));
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
                    startActivity(new Intent(FacultyActivity.this, ContactusActivity.class));

                }
                if (menuItem.getItemId() == R.id.logout)
                {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(FacultyActivity.this, StartScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP ));
                    finish();
                }
                if(menuItem.getItemId()==R.id.aboutus) {
                    startActivity(new Intent(FacultyActivity.this, AboutusActivity.class));
                }
                if(menuItem.getItemId()==R.id.workshops) {
                    startActivity(new Intent(FacultyActivity.this, WorkshopViewActivity.class));
                }
                if(menuItem.getItemId()==R.id.Updatestudentprofile) {
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



                                    if (child.child("communitySub").getValue().toString().equals("Freelance")) {

                                        // editor.putString("type", "Student");
                                        // editor.commit();

                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(FacultyActivity.this, UpdateFreelancerProfile.class);
                                        startActivity(intent);
                                        finish();
                                    } else   {


                                        // editor.putString("type", "Faculty");
                                        // editor.commit();



                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(FacultyActivity.this, UpdateTraineeProfile.class);
                                        startActivity(intent);
                                        finish();
                                    }





                                }

                            }
                        }
                    });

                }
                if(menuItem.getItemId()==R.id.videoProfile) {
                    Intent intent = new Intent();
                    intent.setType("video/mp4");
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(Intent.createChooser(intent, "Select a file"), 101);
                }
                if(menuItem.getItemId()==R.id.uploadtutorial) {
                    startActivity(new Intent(FacultyActivity.this, UploadTutorialActivity.class));
                }
                if(menuItem.getItemId()==R.id.uploadnotes) {
                    startActivity(new Intent(FacultyActivity.this, UploadTutorialActivity.class));
                }

                if(menuItem.getItemId()==R.id.viewfacultyProfile) {
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



                                    if (child.child("communitySub").getValue().toString().equals("Freelance")) {

                                        // editor.putString("type", "Student");
                                        // editor.commit();

                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(FacultyActivity.this, ViewFreelancerProfile.class);
                                        startActivity(intent);
                                        finish();
                                    } else   {


                                        // editor.putString("type", "Faculty");
                                        // editor.commit();



                                        //progressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(FacultyActivity.this, ViewTraineeProfile.class);
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
            StorageReference riversRef = storageRef.child("profilevideos/"+FirebaseAuth.getInstance().getCurrentUser().getEmail());
            UploadTask uploadTask = riversRef.putFile(uri);

            // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    pd.dismiss();
                    Toast.makeText(FacultyActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    pd.dismiss();
                    Toast.makeText(FacultyActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();
                }
            });
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

    public void showSnackbarMessage(View v)
    {/*
		 EditText et_snackbar = (EditText) findViewById(R.id.et_snackbar);
		 TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
		 View view = findViewById(R.id.coordinator_layout);
		 if (et_snackbar.getText().toString().isEmpty()) {
		 textInputLayout.setError(getString(R.string.alert_text));
		 } else {
		 textInputLayout.setErrorEnabled(false);
		 et_snackbar.onEditorAction(EditorInfo.IME_ACTION_DONE);
		 Snackbar.make(view, et_snackbar.getText().toString(), Snackbar.LENGTH_LONG)
		 .setAction(getResources().getString(android.R.string.ok), new View.OnClickListener() {
		 @Override
		 public void onClick(View v) {
		 // Do nothing
		 }
		 })
		 .show();
		 }*/
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
