package com.example.makeanote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN=3000;
    Animation topAnim,botAnim;
    TextView command;
    ImageView notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_main);
            topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
            botAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
            command=(TextView)findViewById(R.id.textView);
             notes= findViewById(R.id.image);
             notes.setAnimation(topAnim);
             command.setAnimation(botAnim);
             new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                   Pair[] pairs=new Pair[2];
                   pairs[0]=new Pair<View,String>(notes,"logo_image");
                   pairs[1]=new Pair<View,String>(command,"logo_name");
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                        startActivity(intent,options.toBundle());


                    }
                  finish();

                }
            },SPLASH_SCREEN);

        }
    }
