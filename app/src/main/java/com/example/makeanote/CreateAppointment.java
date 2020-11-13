package com.example.makeanote;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.onurkaganaldemir.ktoastlib.KToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateAppointment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
   EditText text,address,phone_no;
   TextView date;
    Button Submit;
    String s;
    RadioButton button;
    RadioGroup radioGroup;
   FirebaseUser firebaseUser;
    DataBaseHelper myDb;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Create Appointment");



        super.onCreate(savedInstanceState);
        setContentView(R.layout.createapp);
        Submit=findViewById(R.id.submit);
        text=findViewById(R.id.editTextTextMultiLine);
        phone_no=findViewById(R.id.phoneNo);
        date=findViewById(R.id.date);
        address=findViewById(R.id.address);
        Spinner spinner=findViewById(R.id.spinner1);
        Spinner spinner2=findViewById(R.id.spinner2);
        radioGroup=findViewById(R.id.radioGroup);

        myDb=new DataBaseHelper(this);

 //Activating Spinner containing names we have stored in string value//
  ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_spinner_item);
  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  spinner.setAdapter(adapter);
  spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> Adapter=ArrayAdapter.createFromResource(this,R.array.TestNames,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(Adapter);
        spinner2.setOnItemSelectedListener(this);

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


     //Activating Date Picker //

      date.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Calendar cal=Calendar.getInstance();
              int year=cal.get(Calendar.YEAR);
              int month=cal.get(Calendar.MONTH);
              int day=cal.get(Calendar.DAY_OF_MONTH);

              DatePickerDialog dialog =new DatePickerDialog(
                      CreateAppointment.this,
                      android.R.style.Theme_Holo_Dialog_MinWidth,
                      mDateSetListener,
                      year,month,day);
              dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
              dialog.show();

          }
      });
      mDateSetListener=new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker datePicker, int year, int month, int day) {
             month=month+1;
             String mdate=month+"/"+day+"/"+year;
             date.setText(mdate);
          }
      };

    }


    public void submit(View view) {
        if (text.length() == 0) {
            KToast.errorToast(this, "Enter Your Test Details To Begin Further", Gravity.BOTTOM, KToast.LENGTH_SHORT);
        } else {
         boolean isInserted=   myDb.insertData(text.getText().toString(), address.getText().toString(),
                    date.getText().toString(),
                    phone_no.getText().toString());
         if(isInserted==true) {
            // Toast.makeText(CreateAppointment.this,"Data Inserted",Toast.LENGTH_SHORT).show();
             Intent i = new Intent(CreateAppointment.this, PevAppointment.class);
             //This code is written to transfer date and test details to next page//
             s = text.getText().toString();
             String d = date.getText().toString();
             String a = address.getText().toString();
             i.putExtra("Value", s);
             i.putExtra("keyValue", d);
             String phoneNo=phone_no.getText().toString();
             i.putExtra("key_phone",phoneNo);
             startActivity(i);
            KToast.infoToast(this, "Your Appointment is Fixed For Address: " + a, Gravity.BOTTOM, KToast.LENGTH_AUTO);
             finish();
         } else if(!isInserted){
             Toast.makeText(CreateAppointment.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
         }
        }
    }


//this code generates a toast message when we select a  hospital but not For Select Test
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//      if(parent.getItemAtPosition(position).equals("Select Hospital"))
//      {
////null because we just selected hospital
//      } else{
//         String item=parent.getItemAtPosition(position).toString();
//         Toast.makeText(parent.getContext(), "Selected: " +item, Toast.LENGTH_SHORT).show();
//     }




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //code to run radio buttons..//
    public void checkButton(View v){
        int radioId=radioGroup.getCheckedRadioButtonId();
        button=findViewById(radioId);
    }
    //Logout Using Menu
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
                startActivity(new Intent(CreateAppointment.this,MainActivity2.class));

                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
      Intent i=new Intent(CreateAppointment.this,Homepage.class);
      startActivity(i);
      finish();
    }
}
