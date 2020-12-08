package com.example.makeanote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_Choose_Act extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_choose_act);

    }
    public void pendingAppointments(View view){
        Intent i=new Intent(this,Admin_Activity.class);
        startActivity(i);
        finish();
    }
    public void listUsers(View view){

        Intent i=new Intent(this,Admin_View_Users.class);
        startActivity(i);
        finish();
    }
    public void feedBack(View view){
        Intent i=new Intent(this,FeedBack.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity2.class);
        startActivity(i);
        finish();
        super.onBackPressed();

    }
}
