package com.example.makeanote;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Admin_View_Users extends AppCompatActivity {
    ListView listView;
    int[] images={R.drawable.family_father,R.drawable.family_daughter,R.drawable.family_grandfather
    ,R.drawable.family_grandmother,R.drawable.family_older_sister,R.drawable.family_son};
    String[] email={"raj123@gamil.com","alexaIsHere@yahoo.com","rickymorty@fb.com",
    "granny2611@gmail.com","florasam123@gmail.com","ayushdubey2611@gmail.com"};
    String[] name={"Rajendra Sharma","Alexa Trivia","Uncle George"
    ,"Aunt Alena","Miss.Florida","Mr.Ayush Dubey"};
    String[] age={"26","23","54","48","24","19"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view_users_list);
        listView=findViewById(R.id.list_user);

        ProgramAdapter programAdapter=new ProgramAdapter(this,name,email,age,images);
        listView.setAdapter(programAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent i =new Intent(Admin_View_Users.this,Admin_Choose_Act.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
