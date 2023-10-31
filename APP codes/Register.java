package com.example.maternalhealthriskprediction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText RegisterFullName, RegisterEmail, RegisterPassword, RegisterReEnterPassword;
    Button registerBtn, gotologin;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterFullName = findViewById(R.id.username);
        RegisterEmail = findViewById(R.id.email);
        RegisterPassword = findViewById(R.id.password);
        RegisterReEnterPassword = findViewById(R.id.reenterpassword);
        registerBtn = findViewById(R.id.signupbutton);
        gotologin = findViewById(R.id.login);

        fauth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = RegisterFullName.getText().toString();
                String email = RegisterEmail.getText().toString();
                String password = RegisterPassword.getText().toString();
                String reenterpassword = RegisterReEnterPassword.getText().toString();

                if(fullname.isEmpty()){
                    RegisterFullName.setError("Full Name is required!");
                    return;
                }
                if(email.isEmpty()){
                    RegisterEmail.setError("Email is required!");
                    return;
                }
                if(password.isEmpty()){
                    RegisterPassword.setError("Password is required!");
                    return;
                }
                if(reenterpassword.isEmpty()){
                    RegisterReEnterPassword.setError("Re-Enter Password is required!");
                    return;
                }
                if(!password.equals(reenterpassword)){
                    RegisterReEnterPassword.setError("Password do not match!");
                    return;
                }

                Toast.makeText(Register.this, "Data Validated!", Toast.LENGTH_SHORT).show();

                fauth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(), Main.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });
    }
}
