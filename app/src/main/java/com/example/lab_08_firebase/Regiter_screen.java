package com.example.lab_08_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Regiter_screen extends AppCompatActivity {
    private TextView txtSignIn;
    private EditText txtYourName,txtEmail,txtPassWord,txtPassWord1;
    private Button btnRegister;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    // creating a variable for
    // our object class
    FeelingFace feelingFace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter_screen);



        // below line is used to get reference for our database.


        // initializing our object
        // class variable.
        feelingFace = new FeelingFace();

        txtYourName= (EditText) findViewById(R.id.txt_your_name);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassWord = (EditText) findViewById(R.id.txt_passinput);
        txtPassWord1 =(EditText) findViewById(R.id.txt_editpassinput);
        btnRegister = (Button) findViewById(R.id.btn_SignIN);
        txtSignIn=findViewById(R.id.txt_SignIN);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regiter_screen.this, Sign_in_screen.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yourName =txtYourName.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String passWord =txtPassWord.getText().toString().trim();
                String editPass = txtPassWord1.getText().toString().trim();

                if(yourName.isEmpty()){
                    txtYourName.setError("Full name required");
                    txtYourName.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    txtYourName.setError("email required");
                    txtYourName.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    txtEmail.setError("please provide valid email ");
                    txtEmail.requestFocus();
                    return;
                }
                if(passWord.isEmpty()){
                    txtPassWord.setError(" password required");
                    txtPassWord.requestFocus();
                    return;
                }
                if(passWord.length()<6){
                    txtPassWord.setError(" password >6 characters required");
                    txtPassWord.requestFocus();
                    return;
                }
                if(editPass.isEmpty()){
                    txtPassWord1.setError(" edit password required");
                    txtPassWord1.requestFocus();
                    return;
                }
                if(editPass.length()<6){
                    txtPassWord.setError("edit password >6 characters required");
                    txtPassWord.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email,editPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FeelingFace feelingFace = new FeelingFace(yourName,email,passWord,0,0,0);

                                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(feelingFace).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Regiter_screen.this,"Users has been register sucessfully ",Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.VISIBLE);
                                                Intent intent = new Intent(Regiter_screen.this, Sign_in_screen.class);
                                                startActivity(intent);
                                            }else {
                                                Toast.makeText(Regiter_screen.this,"Fail to  register! try agian!  ",Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }

                                        }
                                    });
                                }else{
                                    Toast.makeText(Regiter_screen.this,"Fail to  register! try agian!  ",Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
                 }
        });
    }


}