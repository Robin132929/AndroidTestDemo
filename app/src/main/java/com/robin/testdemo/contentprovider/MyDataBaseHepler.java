package com.robin.testdemo.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class MyDataBaseHepler extends SQLiteOpenHelper {
   public static final String creat_book="create table table1 ("+
           "id integer primary key autoincrement,"+
           "author text, "+
           "price real, "+
           "name text)";

   private Context context;
    public MyDataBaseHepler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(creat_book);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
