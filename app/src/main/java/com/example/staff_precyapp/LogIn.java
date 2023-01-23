package com.example.staff_precyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class LogIn extends AppCompatActivity {

    EditText email, password;
    Button logIn_Btn;
    ProgressBar progressBar;
    CheckBox checkBox;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logIn_Btn = findViewById(R.id.signInEmail_Btn);
        progressBar = findViewById(R.id.sign_progressbar);
        checkBox = findViewById(R.id.showPW);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        password.setTransformationMethod(new PasswordTransformationMethod());

        setUpShowPassword();
        setUpLogInButton();

    }

    private void setUpLogInButton() {

        logIn_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logIn_Btn.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                if (email.getText().toString().isEmpty()){
                    email.setError(" Enter email");
                }
                else if (password.getText().toString().isEmpty()){
                    password.setError(" Enter password");
                }
                else {

                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(getApplicationContext(), "Successfully log in", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                    } else {
                                        Toast.makeText(getApplicationContext(), "SIGN IN FAILED", Toast.LENGTH_SHORT).show();
                                        logIn_Btn.setVisibility(View.VISIBLE);
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
//                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
//                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()){
//
//
//                                        HashMap<String, String> savedusers = new HashMap<>();
//                                        savedusers.put("Email", email.getText().toString());
//
//                                        firebaseFirestore.collection("StaffUsers").document(firebaseAuth.getUid())
//                                                .set(savedusers)
//                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void unused) {
//                                                        Log.d("TAG", "SUCCESS DATA UPLOAD");
//                                                        Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_LONG).show();
//
//                                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                                    }
//                                                }).addOnFailureListener(new OnFailureListener() {
//                                                    @Override
//                                                    public void onFailure(@NonNull Exception e) {
//                                                        Log.d("TAG", "data sending" + e);
//                                                        logIn_Btn.setVisibility(View.VISIBLE);
//                                                        progressBar.setVisibility(View.GONE);
//                                                    }
//                                                });
//                                    }
//                                    else {
//                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
//
//                                        logIn_Btn.setVisibility(View.VISIBLE);
//                                        progressBar.setVisibility(View.GONE);
//                                    }
//
//                                }
//                            });



                }
            }
        });

    }

    private void setUpShowPassword() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    password.setTransformationMethod(null);
                }
                else {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}

//                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
//                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()){
//
//
//                                        HashMap<String, String> savedusers = new HashMap<>();
//                                        savedusers.put("Email", email.getText().toString());
//
//                                        firebaseFirestore.collection("StaffUsers").document(firebaseAuth.getUid())
//                                                .set(savedusers)
//                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void unused) {
//                                                        Log.d("TAG", "SUCCESS DATA UPLOAD");
//                                                        Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_LONG).show();
//
//                                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                                    }
//                                                }).addOnFailureListener(new OnFailureListener() {
//                                                    @Override
//                                                    public void onFailure(@NonNull Exception e) {
//                                                        Log.d("TAG", "data sending" + e);
//                                                        logIn_Btn.setVisibility(View.VISIBLE);
//                                                        progressBar.setVisibility(View.GONE);
//                                                    }
//                                                });
//                                    }
//                                    else {
//                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
//
//                                        logIn_Btn.setVisibility(View.VISIBLE);
//                                        progressBar.setVisibility(View.GONE);
//                                    }
//
//                                }
//                            });