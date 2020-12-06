package com.example.makeanote;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.onurkaganaldemir.ktoastlib.KToast;

public class MainActivity2 extends AppCompatActivity {
    Button new_userTrans;
    Button goHome;
    Button admin_login;
    ImageView image;
    TextView logoText,forgotPassword;
    EditText password, email;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    FirebaseUser firebaseUser;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        new_userTrans = findViewById(R.id.new_user);
       admin_login=findViewById(R.id.admin_login);

        image = findViewById(R.id.logo);
        logoText = findViewById(R.id.sinUp);
        forgotPassword=findViewById(R.id.forgot);
        password =  findViewById(R.id.password);
        goHome = findViewById(R.id.go);
        firebaseAuth = FirebaseAuth.getInstance();


        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser !=null){
            Intent intent=new Intent(MainActivity2.this,Homepage.class);
            startActivity(intent);
            finish();
        }


        email =  findViewById(R.id.email);
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        new_userTrans.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
             

                Intent i = new Intent(MainActivity2.this, Login.class);

                startActivity(i);
                progressBar.setVisibility(View.GONE);
            }


        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,Forgot_Password.class));
            }
        });

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = email.getText().toString().trim();

                String val2 = password.getText().toString().trim();

                if (val.isEmpty()) {
                    email.setError("Field cannot be Empty");
                    return;
               }   else {
                    email.setError(null);

                    //return;
                }


                if (val2.isEmpty()) {
                    password.setError("Field cannot be Empty");
                    return;
                }  else {
                    password.setError(null);


                }
                //firebase Authentication proccess----//
                firebaseAuth.signInWithEmailAndPassword(val, val2)
                        .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.VISIBLE);
                                if (task.isSuccessful()) {
                               Intent i=new Intent(MainActivity2.this,Homepage.class);
                               startActivity(i);
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    KToast.errorToast(MainActivity2.this,"Invalid User Recheck!", Gravity.BOTTOM,KToast.LENGTH_SHORT);
                                }
                            }
                        });
            }
        });
        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity2.this,Admin_Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}



















