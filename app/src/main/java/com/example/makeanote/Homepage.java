package com.example.makeanote;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.hanks.htextview.base.HTextView;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
    Button contact;
    TextView bottom_text;
    private HTextView textViewTyper;//animated TextView
    int delay = 2000; //milliseconds
    Handler handler;
    ArrayList<String> arrMessages = new ArrayList<>();
    int position=0;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_main);

        arrMessages.add("HOME PAGE");

        textViewTyper=findViewById(R.id.prevApp);

        textViewTyper.animateText(arrMessages.get(position));
        /* Change Messages every 2 Seconds */
        handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){

                handler.postDelayed(this, delay);
                if(position>=arrMessages.size())
                    position=0;
                textViewTyper.animateText(arrMessages.get(position));
                position++;
            }
        }, delay);
        //adding scroll view to bottomText.
        bottom_text=findViewById(R.id.bottom_text);
        bottom_text.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Homepage.this)
                        .setTitle("Follow these Steps:")
                        .setIcon(R.drawable.ic_baseline_language_24)
                        .setMessage(R.string.dialog_message)
                        .setNegativeButton("Ok", null).create().show();

            }
        });
    }


    public void createApp(View view){
        Intent i= new Intent( this, CreateAppointment.class);
        startActivity(i);
        finish();
    }

    public void sendEmail(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "ayushdubey2611@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Queries Regarding Appointment");

        startActivity(Intent.createChooser(intent, "Send Email"));


    }
    public void callMe(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+91 9819024415" ));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Homepage.this,MainActivity2.class));

                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //This Will Make A dialog Appear which will ask you if you want to exit the app Or Not.
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Homepage.super.onBackPressed();
                    }
                }).create().show();
    }


}
