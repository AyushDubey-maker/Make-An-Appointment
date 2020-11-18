package com.example.makeanote;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BMI_Calculator extends AppCompatActivity {
    Button bmi_button;
    TextView bmiText;
    EditText getheight,getweight;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_calculator);
        bmi_button=findViewById(R.id.bmi_but);
        bmiText=findViewById(R.id.bmi_text);
        getheight=findViewById(R.id.getHeight);
        getweight=findViewById(R.id.getWeight);
        bmi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String strWeight=getweight.getText().toString();
               String strHeight=getheight.getText().toString();
               if(strWeight.equals("")){
                   getweight.setError("Please Enter Your Weight");
                   getweight.requestFocus();
                   return;
               }if(strHeight.equals("")){
                   getheight.setError("Please Enter Your Height");
                   getheight.requestFocus();
                   return;
                }
               float weight=Float.parseFloat(strWeight);
               float height=Float.parseFloat(strHeight)/100;
               float bmiValue=BMICalculate(weight,height);
               bmiText.setText(interpreteBMI(bmiValue));

            }
        });
    }
    public float BMICalculate(float weight,float height){
        return weight/(height*height);
    }public String interpreteBMI(float bmiValue){
        if(bmiValue<16){
            return "Severly UnderWeight";
        }else if(bmiValue<18.5){
            return "UnderWeight";
        }else if(bmiValue<25){
            return "Normal";
        }else if(bmiValue<30){
            return "OverWeight";
        }
        else
            return "Obese";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(BMI_Calculator.this,Homepage.class);
        startActivity(i);

    }
}
