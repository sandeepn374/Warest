package com.myapp.warest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.net.*;
import android.app.*;
import com.google.firebase.storage.*;
import android.view.*;
import com.google.android.gms.tasks.*;
import android.widget.*;
import android.support.annotation.*;

/**
 * Created by kshravi on 27/11/2017 AD.
 */

public class UploadTutorialActivity extends AppCompatActivity {

    EditText subject, instructor,description;
    Button chooseFile,upload;

	Uri filePath;
    ProgressDialog pd;

    //creating reference to firebase storage
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://warest-77e4b.appspot.com/");



	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadtutorial);
        subject=(EditText)findViewById(R.id.subject);
        instructor=(EditText)findViewById(R.id.instructor);
        description=(EditText)findViewById(R.id.description);
        chooseFile=(Button) findViewById(R.id.chooseFile);
		upload=(Button)findViewById(R.id.upload);
	
		pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");
	
	
		chooseFile.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setType("*/*");
					intent.setAction(Intent.ACTION_PICK);
					startActivityForResult(Intent.createChooser(intent, "Select a file"), 101);
				}
			});
		
		
		
		upload.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(filePath != null) {
						pd.show();

						StorageReference childRef = storageRef.child("tutorials/"+filePath.getLastPathSegment());

						//uploading the image
						UploadTask uploadTask = childRef.putFile(filePath);

						uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
								@Override
								public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
									pd.dismiss();
									Toast.makeText(UploadTutorialActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
								}
							}).addOnFailureListener(new OnFailureListener() {
								@Override
								public void onFailure(@NonNull Exception e) {
									pd.dismiss();
									Toast.makeText(UploadTutorialActivity.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
								}
							});
					}
					else {
						Toast.makeText(UploadTutorialActivity.this, "Select a good file ", Toast.LENGTH_SHORT).show();
					}
				}
		 	});
    }


	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
        }
    }

}
