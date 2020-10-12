package com.example.makeanote;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.borjabravo.readmoretextview.ReadMoreTextView;
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


}
