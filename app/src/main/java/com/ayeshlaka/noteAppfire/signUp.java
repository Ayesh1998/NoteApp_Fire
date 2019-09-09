package com.ayeshlaka.noteAppfire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUp extends AppCompatActivity {

    FirebaseAuth firebaseAuthe;
    EditText email,password,confirmPass;
    String email1,password1,confirmPass1;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = (EditText) findViewById(R.id.etSignUpEmail);
        password = (EditText) findViewById(R.id.etSignUpPassword);
        confirmPass = (EditText) findViewById(R.id.etSignUpConfirmPassword);
        signUp = findViewById(R.id.signUpUpButton);
        firebaseAuthe = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email1 = email.getText().toString();
                password1 = password.getText().toString();
                confirmPass1 = confirmPass.getText().toString();

                if (TextUtils.isEmpty(email1)){
                    Toast.makeText(signUp.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)){
                    Toast.makeText(signUp.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmPass1)){
                    Toast.makeText(signUp.this, "Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!(password1.equals(confirmPass1))){
                    Toast.makeText(signUp.this, "Check passwords", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuthe.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(signUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseAuthe.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if ( (task.isSuccessful())){
                                        Toast.makeText(signUp.this, "Verify your email", Toast.LENGTH_LONG).show();
                                        Intent goActitviyMain = new Intent(signUp.this, com.ayeshlaka.noteAppfire.MainActivity.class);
                                        startActivity(goActitviyMain);
                                    }else{
                                        Toast.makeText(signUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(signUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
