package com.example.makeanote;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.onurkaganaldemir.ktoastlib.KToast;

public class Admin_Login extends AppCompatActivity {
    EditText password, email;
    Button but_admin_enter;
    FirebaseAuth firebaseAuth;
    ProgressBar admin_progressbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        firebaseAuth = FirebaseAuth.getInstance();

        email=findViewById(R.id.admin_email);
        password=findViewById(R.id.admin_password);
        but_admin_enter=(Button)findViewById(R.id.admin_enter);
        admin_progressbar=findViewById(R.id.admin_progress_bar);
        admin_progressbar.setVisibility(View.GONE);
        but_admin_enter.setOnClickListener(new View.OnClickListener() {
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
                        .addOnCompleteListener(Admin_Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                admin_progressbar.setVisibility(View.VISIBLE);
                                if (task.isSuccessful()) {
                                    Intent i=new Intent(Admin_Login.this,Admin_Activity.class);
                                    startActivity(i);
                                } else {
                                   admin_progressbar.setVisibility(View.GONE);
                                    KToast.errorToast(Admin_Login.this,"Invalid Admin Recheck!", Gravity.BOTTOM,KToast.LENGTH_SHORT);
                                }
                            }
                        });
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i =new Intent(this,MainActivity2.class);
        startActivity(i);
        super.onBackPressed();
    }
}
