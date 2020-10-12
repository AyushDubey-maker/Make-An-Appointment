package com.example.makeanote;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
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

import androidx.appcompat.app.AppCompatActivity;

import com.onurkaganaldemir.ktoastlib.KToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateAppointment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
   EditText text,address;
   TextView date;
    Button Submit;
    String s;
    RadioButton button;
    RadioGroup radioGroup;


    private DatePickerDialog.OnDateSetListener mDateSetListener;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.createapp);
        Submit=findViewById(R.id.submit);
        text=findViewById(R.id.textDetails);

        date=findViewById(R.id.date);
        address=findViewById(R.id.address);
        Spinner spinner=findViewById(R.id.spinner1);
        radioGroup=findViewById(R.id.radioGroup);
 //Activating Spinner containing names we have stored in string value//
  ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_spinner_item);
  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  spinner.setAdapter(adapter);
  spinner.setOnItemSelectedListener(this);



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


            Intent i = new Intent(CreateAppointment.this, PevAppointment.class);
            //This code is written to transfer date and test details to next page//
            s = text.getText().toString();
            String d = date.getText().toString();
            String a = address.getText().toString();
            i.putExtra("Value", s);
            i.putExtra("keyValue", d);

            startActivity(i);
            KToast.infoToast(this, "Your Appointment is Fixed For Address: " + a, Gravity.BOTTOM, KToast.LENGTH_AUTO);
            finish();
        }


//this code generates a toast message when we select a  hospital
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

      if(parent.getItemAtPosition(position).equals("Select Hospital"))
      {
//null because we just selected hospital
      } else{
         String item=parent.getItemAtPosition(position).toString();
         Toast.makeText(parent.getContext(), "Selected: " +item, Toast.LENGTH_SHORT).show();
     }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //code to run radio buttons..//
    public void checkButton(View v){
        int radioId=radioGroup.getCheckedRadioButtonId();
        button=findViewById(radioId);
    }
}
