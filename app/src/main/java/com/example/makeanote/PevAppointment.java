package com.example.makeanote;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.onurkaganaldemir.ktoastlib.KToast;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PevAppointment extends AppCompatActivity {
    TextView textView,txtdate,disp,disp_phone,phone_no;
    String st;
    Button viewReport;
    DataBaseHelper myDb;
    Button deleteReport;
    ScrollView parent;

    protected void onCreate(Bundle savedInstanceState) {
       getSupportActionBar().setTitle("Your Appointment");
//       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.prevapp_main);
        textView=findViewById(R.id.appointment);
        txtdate=findViewById(R.id.date);
        disp=findViewById(R.id.display);
        viewReport=findViewById(R.id.view_report);
        deleteReport=findViewById(R.id.delete_report);
        disp_phone=findViewById(R.id.display_phone);
        phone_no=findViewById(R.id.phone_no);
        parent=findViewById(R.id.parent);

        myDb=new DataBaseHelper(this);

       deleteReport.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Integer deleteRows=myDb.deleteData(phone_no.getText().toString());
               if(deleteRows>0){
                   KToast.warningToast(PevAppointment.this, "Your Appointment Cancelled", Gravity.BOTTOM, Toast.LENGTH_SHORT);

               }else{
//                   Toast.makeText(PevAppointment.this, "You Have No Appointment To Cancel", Toast.LENGTH_SHORT).show();
                   showSnackBar();

               }
           }
       });

        viewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myDb.getAllData();
                if(res.getCount()==0){
                    //show message
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("TEST DETAILS :"+ res.getString(0)+"\n");
                    buffer.append("Appointment Date :"+ res.getString(1)+"\n");
                    buffer.append("Your Address :"+ res.getString(2)+"\n");
                    buffer.append("Phone No :"+ res.getString(3)+"\n\n");

                }
               //Show All Data
                showMessage("Your Appointments:",buffer.toString());
            }
        });


        String date=txtdate.getText().toString().trim();


        st=getIntent().getExtras().getString("Value");
        textView.setText(st);
        String d=getIntent().getExtras().getString("keyValue");
        txtdate.setText(d);

        String phone_No=getIntent().getStringExtra("key_phone");
        phone_no.setText(phone_No);

        //Code to generate a Youtube Video..//
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
       // builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

    //Exit App Code..//

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
                startActivity(new Intent(PevAppointment.this,MainActivity2.class));

                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showSnackBar(){
        Snackbar.make(findViewById(android.R.id.content),"Appointment already deleted",Snackbar.LENGTH_SHORT)
                .setAction("Make an Appointment",new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent i=new Intent(PevAppointment.this,CreateAppointment.class);
                        startActivity(i);
                        finish();
                    }
                })
    .show();
    }
     // To enable the function Of Back Button.
    @Override
    public void onBackPressed() {
        Intent i=new Intent(PevAppointment.this,Homepage.class);
        startActivity(i);
        finish();
    }
}
