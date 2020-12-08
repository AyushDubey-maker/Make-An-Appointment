package com.example.makeanote;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.onurkaganaldemir.ktoastlib.KToast;

import java.util.ArrayList;



public class Admin_Activity extends AppCompatActivity {
    DataBaseHelper myDB;
    TextView app_text;
    ArrayAdapter listAdapter;
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_listview);
        app_text=findViewById(R.id.admin_app_content);


         updateUI();

        }



   private void updateUI() {
       final ListView listView = (ListView) findViewById(R.id.listView);
       myDB = new DataBaseHelper(this);
       final ArrayList<String> theList = new ArrayList<>();
       Cursor data = myDB.getAllData();
       if (data.getCount() == 0) {
           Toast.makeText(this, "No Appointments Till Now", Toast.LENGTH_SHORT).show();
       } else {
           while (data.moveToNext()) {

               theList.add("Patient Details \n\n"+"Patient Name: " + data.getString(0) + "\n\n" + "Patient Address :" + data.getString(1) + "\n\n" + "Appointment Date :" + data.getString(2) + "\n\n" + "Phone No :" + data.getString(3) + "\n");
                listAdapter = new ArrayAdapter<String>(this, R.layout.admin_layout, R.id.admin_app_content, theList);
               listView.setAdapter(listAdapter);

           }
       }
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               final int item=position;
               new AlertDialog.Builder(Admin_Activity.this).setIcon(android.R.drawable.ic_delete)
                       .setTitle("Delete Appointment For This Patient")
                       .setMessage("Admin Do you want to delete the patient appointment details?")
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               theList.remove(item);
                               listAdapter.notifyDataSetChanged();
                           }
                       })
                       .setNegativeButton("No",null)
                       .show();
               return true;
           }
       });

   }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(Admin_Activity.this,Admin_Choose_Act.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
