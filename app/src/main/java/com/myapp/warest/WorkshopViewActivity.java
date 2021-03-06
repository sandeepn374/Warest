package com.myapp.warest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkshopViewActivity extends AppCompatActivity {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workshopview);

        FirebaseDatabase.getInstance().getReference().child("workshops")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int totDue=0;
                        ArrayList<Workshop> users=new ArrayList<Workshop>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            final Workshop user = snapshot.getValue(Workshop.class);
                            users.add(user);

                        }

                        TableLayout layoutINNER = new TableLayout(WorkshopViewActivity.this);
                        for(final Workshop user:users) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT);


                            TextView tv1 = new TextView(WorkshopViewActivity.this);
                            tv1.setText(user.content);

                            TableRow tr1 = new TableRow(WorkshopViewActivity.this);

                            tr1.setLayoutParams(params);
                            tr1.addView(tv1);
                            tv1.setTextColor(Color.WHITE);
                            tv1.setTextSize(getResources().getDimension(R.dimen.text_size));

                           layoutINNER.addView(tr1);

                        }

                        LinearLayout main = (LinearLayout) findViewById(R.id.main_layout);

                        main.addView(layoutINNER);




                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });




    }










}
