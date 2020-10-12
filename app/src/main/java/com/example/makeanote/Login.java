package com.example.makeanote;

import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.onurkaganaldemir.ktoastlib.KToast;

public class Login extends AppCompatActivity {
    EditText regName,  regEmail, regConfirmPassword, regPassword;
    Button regButton;
    FirebaseAuth rootNode;
    ProgressBar progressBar;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinup);

        regName = findViewById(R.id.full_name);
        regEmail = findViewById(R.id.email1);
        regPassword = findViewById(R.id.password);
        regConfirmPassword = findViewById(R.id.confirmPassword);
        regButton = findViewById(R.id.reg_btn);
        progressBar=findViewById(R.id.progress_bar);
        rootNode = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=regEmail.getText().toString().trim();
                String password=regPassword.getText().toString().trim();
                String fullName=regName.getText().toString().trim();
                String confirmPassword=regConfirmPassword.getText().toString().trim();
                if(fullName.isEmpty()){
                    KToast.errorToast(Login.this, "Please Enter Your FullName",Gravity.BOTTOM, Toast.LENGTH_SHORT);

                    return;

                }
                if(email.isEmpty()){
                    KToast.errorToast(Login.this,"Please Enter Email",Gravity.BOTTOM,Toast.LENGTH_SHORT);

                    return;
                }
             else if(password.length()<=6){
                    KToast.warningToast(Login.this, "Password Must Be Greater than 6 Characters",Gravity.BOTTOM, KToast.LENGTH_SHORT);
                }
                if(password.isEmpty()){
                    KToast.errorToast(Login.this, "Please Enter Password",Gravity.BOTTOM, KToast.LENGTH_SHORT);

                    return;
                }
                if(confirmPassword.isEmpty()){
                    KToast.errorToast(Login.this, "Enter Confirm Password",Gravity.BOTTOM, KToast.LENGTH_SHORT);

                    return;
                }
               // This process will only set-up if password==confirmPassword---//
                if (password.equals(confirmPassword)) {


                    rootNode.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {
                                        KToast.successToast(Login.this, "Data Saved", Gravity.BOTTOM, KToast.LENGTH_SHORT);
                                        Intent i = new Intent(Login.this, MainActivity2.class);
                                        startActivity(i);
                                    } else {
                                        KToast.errorToast(Login.this, "Authentication Failed..Password Weak",Gravity.BOTTOM, KToast.LENGTH_SHORT);
                                    }
                                }
                            });
                }
            }
        });
    }

}

