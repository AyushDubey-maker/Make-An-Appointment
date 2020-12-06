package com.example.makeanote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    //Taken params in the same slide.
    public static final String DATABASE_NAME="Patient.db";
    public static final String TABLE_NAME="Patient_table";
    public static final String COL_1="TEST_DETAILS";
    public static final String COL_2="DATE";
    public static final String COL_3="ADDRESS";
    public static final String COL_4="PHONE_NO";

    public DataBaseHelper( Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+ TABLE_NAME +" (TEST_DETAILS,DATE,ADDRESS,PHONE_NO INTEGER PRIMARY KEY) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
     onCreate(db);
    }
    public boolean insertData(String test_det,String date,String address,String phone_no){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,test_det);
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,phone_no);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String test_det,String date,String address,String phone_no){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,test_det);
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,phone_no);
        db.update(TABLE_NAME,contentValues,"PHONE_NO = ?",new String[] { phone_no });
        return true;
    }
    public Integer deleteData(String phone_no){
        SQLiteDatabase db=this.getWritableDatabase();
         return db.delete(TABLE_NAME,"PHONE_NO = ?",new String[]{phone_no});

    }
}
