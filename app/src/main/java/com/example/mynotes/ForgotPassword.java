package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    TextInputEditText editTextEmail;

    Button resetPassword;

    TextView signIn;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextEmail = findViewById(R.id.email);
        resetPassword = findViewById(R.id.resetpass);
        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

            resetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailid = editTextEmail.getText().toString();
                    if(emailid.isEmpty()){
                        Toast.makeText(ForgotPassword.this, "Email-ID can't be empty.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        firebaseAuth.sendPasswordResetEmail(emailid)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(ForgotPassword.this, "Reset Password has been sent to the given Email-ID.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ForgotPassword.this, LoginPage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ForgotPassword.this, "Error, Email-ID is not registered.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            });
    }
}