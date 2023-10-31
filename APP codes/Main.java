package com.example.maternalhealthriskprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class Main extends AppCompatActivity {
    TextView verifying;
    Button verifyingEmail;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        Button logoutbtn = findViewById(R.id.logout);
        verifying = findViewById(R.id.verifying);
        verifyingEmail = findViewById(R.id.Verify);

        if(!auth.getCurrentUser().isEmailVerified()){
            verifying.setVisibility(View.VISIBLE);
            verifyingEmail.setVisibility(View.VISIBLE);
        }

        verifyingEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Main.this, "Email Verification Sent!", Toast.LENGTH_SHORT).show();
                        verifying.setVisibility(View.GONE);
                        verifyingEmail.setVisibility(View.GONE);
                    }
                });
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }
}
