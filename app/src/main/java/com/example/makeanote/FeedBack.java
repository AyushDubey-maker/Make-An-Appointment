package com.example.makeanote;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FeedBack extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_feedback);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,Admin_Choose_Act.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
