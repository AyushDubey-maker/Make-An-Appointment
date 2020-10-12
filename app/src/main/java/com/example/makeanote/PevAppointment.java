package com.example.makeanote;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.onurkaganaldemir.ktoastlib.KToast;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PevAppointment extends AppCompatActivity {
    TextView textView,txtdate,disp;
    String st;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.prevapp_main);
        textView=findViewById(R.id.appointment);
        txtdate=findViewById(R.id.date);
        disp=findViewById(R.id.display);

        String date=txtdate.getText().toString().trim();
        if(date.isEmpty()){
            KToast.errorToast(this,"Please Fill the Appointment details", Gravity.TOP,KToast.LENGTH_AUTO);
        }

        st=getIntent().getExtras().getString("Value");
        textView.setText(st);
        String d=getIntent().getExtras().getString("keyValue");
        txtdate.setText(d);

        //Code to generate a Youtube Video..//
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
    //Exit App Code..//
    public void exitApp(View args0){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }
}
