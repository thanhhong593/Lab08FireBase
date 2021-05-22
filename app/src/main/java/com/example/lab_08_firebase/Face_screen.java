package com.example.lab_08_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Face_screen extends AppCompatActivity {
    private ImageView imgHappy,imgUnHappy,imgNormal;
    private Button btnFinish;
    private ProgressBar progressBar;
    private int happydata,unhappydata,normaldata;
    // creating a variable for our
    // Firebase Database.

    String email;
    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    FeelingFace feelingFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_screen);
        // initializing our edittext and button
        imgHappy =(ImageView) findViewById(R.id.btn_happy);
        imgUnHappy = (ImageView) findViewById(R.id.btn_unhappy);
        imgNormal = (ImageView) findViewById(R.id.btn_normal);
        btnFinish = findViewById(R.id.btn_finish);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // below line is used to get the
        // instance of our FIrebase database.

        Intent intent = getIntent();
        happydata = intent.getIntExtra("happy",R.id.btn_happy);
        unhappydata = intent.getIntExtra("happy",R.id.btn_unhappy);
        normaldata = intent.getIntExtra("happy",R.id.btn_normal);
        email = intent.getStringExtra("email");
        // below line is used to get reference for our database.
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // initializing our object
        // class variable.
        feelingFace = new FeelingFace();

        imgHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","1");
                Toast.makeText(Face_screen.this,"Happy finish",Toast.LENGTH_SHORT).show();

                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("happy").setValue(happydata+=1);
            }
        });
        imgUnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag","2");
                Toast.makeText(Face_screen.this,"Happy finish",Toast.LENGTH_SHORT).show();
                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("unHappy").setValue(unhappydata+=1);
            }
        });
        imgNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Face_screen.this,"Happy finish",Toast.LENGTH_SHORT).show();
                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("normal").setValue(normaldata+=1);
            }
        });


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Face_screen.this,MainActivity.class));
            }
        });

    }


}