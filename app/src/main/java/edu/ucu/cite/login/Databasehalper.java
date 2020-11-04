package edu.ucu.cite.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class Databasehalper extends SQLiteOpenHelper {

    public Databasehalper (Context context){
        super(context,"Login.db",null,1);
    }

    //create a table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  register( username text primary key, password text,name text,address text,gender text,status text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists register");
    }
    //insert data
    public Boolean addUser(  String Eusername,String Epassword,String Ename, String Eaddress, String Egender,String Estatus){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("username",Eusername);
        contentValues.put("password",Epassword);
        contentValues.put("name",Ename);
        contentValues.put("address",Eaddress);
        contentValues.put("gender",Egender);
        contentValues.put("status",Estatus);
        long result = db.insert("register",null,contentValues);
        if(result==-1) return false;
        else return true;

    }
    //validation for username in register form
    public Boolean checkUser(String username){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from register where username=?", new String[]{username});
        if (cursor.getCount()>0) return false;
        else return true;

    }
    //validation for password in Login
    public Boolean User(String Username,String Password){
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from register where username=? and password=?",new String[]{Username,Password});
        if (cursor.getCount()>0) return true;
        else return false;
    }


}
