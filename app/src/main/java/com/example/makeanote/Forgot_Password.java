package com.example.makeanote;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.onurkaganaldemir.ktoastlib.KToast;

public class Forgot_Password extends AppCompatActivity {
private EditText passwordEmail;
private Button resetPassword;
private FirebaseAuth firebaseAuth;

@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        passwordEmail=(EditText)findViewById(R.id.Verifyemail);
        resetPassword=(Button) findViewById(R.id.resetPass);

        firebaseAuth=FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=passwordEmail.getText().toString().trim();
                if(useremail.equals("")){
                    KToast.warningToast(Forgot_Password.this, "Please enter Your Registered Email Id", Gravity.BOTTOM, KToast.LENGTH_SHORT);
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                               KToast.successToast(Forgot_Password.this, "Password reset mail sent!",Gravity.BOTTOM, KToast.LENGTH_SHORT);
                                finish();
                                startActivity(new Intent(Forgot_Password.this,MainActivity2.class));
                            }else{
                                KToast.errorToast(Forgot_Password.this, "Error In Sending Email",Gravity.BOTTOM, KToast.LENGTH_SHORT);
                            }
                        }
                    });
                }
            }
        });

    }
}
