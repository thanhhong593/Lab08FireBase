package com.example.lab_08_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.AsyncListUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Sign_in_screen extends AppCompatActivity {


    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private TextView txtRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

//        if (auth.getCurrentUser() != null) {
//            startActivity(new Intent(Sign_in_screen.this, Face_screen.class));
//            finish();
//        }

        // set the view now
        setContentView(R.layout.activity_login);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtRegister = findViewById(R.id.txt_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_in_screen.this,Regiter_screen.class));
            }
        });
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

       

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Sign_in_screen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Sign_in_screen.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {

//                                    ValueEventListener valueEventListener =new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                            FeelingFace feel = snapshot.getValue(FeelingFace.class);
//                                            Intent intent = new Intent(Sign_in_screen.this, Face_screen.class);
//                                            int happy = feel.getHappy();
//                                            int unhappy = feel.getUnHappy();
//                                            int normal = feel.getNormal();
//                                            intent.putExtra("happy",happy);
//                                            intent.putExtra("unhappy",unhappy);
//                                            intent.putExtra("normal",normal);
//                                            intent.putExtra("email",email);
//                                            startActivity(intent);
//                                            finish();
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError error) {
//                                            Log.w("TAG","loadPost :onCancelled",error.toException());
//                                        }
//                                    };

                                    Intent intent = new Intent(Sign_in_screen.this, Face_screen.class);
                                    FeelingFace feel = new FeelingFace();
                                    int happy = feel.getHappy();
                                    int unhappy = feel.getUnHappy();
                                    int normal = feel.getNormal();
                                    intent.putExtra("happy",happy);
                                    intent.putExtra("unhappy",unhappy);
                                    intent.putExtra("normal",normal);
                                    intent.putExtra("email",email);
                                    startActivity(intent);
                                    finish();

                                }
                            }
                        });
            }
        });
    }

}
