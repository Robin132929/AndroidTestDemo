package com.robin.testdemo.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.sql.SQLData;

public class MyContentProvider extends ContentProvider {
    private static final int TABLE1 = 0;
    private static final int TABLE2 = 1;
    private static final int TABLE3 = 2;
    private static final int TABLE4 = 3;
    private static UriMatcher uriMatcher;
    private MyDataBaseHepler myDataBaseHepler;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.robin.androidtest", "table1", TABLE1);
        uriMatcher.addURI("com.robin.androidtest", "table1/#", TABLE2);
        uriMatcher.addURI("com.robin.androidtest", "table2", TABLE3);
        uriMatcher.addURI("com.robin.androidtest", "table2/#", TABLE4);

    }

    private static final String TAG = "MyContentProvider";

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        myDataBaseHepler=new MyDataBaseHepler(getContext(),"table1.db",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db=myDataBaseHepler.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)) {
            case TABLE1:
                cursor=db.query("table1",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TABLE2:
                String table_id=uri.getPathSegments().get(1);
                for (int i = 0; i < uri.getPathSegments().size(); i++) {
                    Log.i(TAG, "query getPathSegments(): "+uri.getPathSegments().get(i));

                }
                cursor=db.query("table1",projection,"id=?",new String[]{table_id},null,null,sortOrder);

                break;
            case TABLE3:
                break;
            case TABLE4:
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1:
                return "vnd.android.cursor.dir/vnd.com.robin.androidtest.table1";
            case TABLE2:
                return "vnd.android.cursor.item/vnd.com.robin.androidtest.table1";
            case TABLE3:
                return "vnd.android.cursor.dir/vnd.com.robin.androidtest.table2";
            case TABLE4:
                return "vnd.android.cursor.item/vnd.com.robin.androidtest.table2";
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db=myDataBaseHepler.getWritableDatabase();
        Uri result=null;
        switch (uriMatcher.match(uri)) {
            case TABLE1:
            case TABLE2:
                long new_tableis=db.insert("table1",null,values);
                result=Uri.parse("content://com.robin.androidtest/table1"+new_tableis);

            break;
            default:
                break;
        }
        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db=myDataBaseHepler.getWritableDatabase();
        int deleteRow=0;
        switch (uriMatcher.match(uri)) {
            case TABLE1:
                deleteRow=db.delete("table1",selection,selectionArgs);
                break;
            case TABLE2:
                String up_id=uri.getPathSegments().get(1);
                deleteRow=db.delete("table1","id=?",new String[]{up_id});

                break;
            default:
                break;
        }
        return deleteRow;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db=myDataBaseHepler.getWritableDatabase();
       int updateRow=0;
        switch (uriMatcher.match(uri)) {
            case TABLE1:
                updateRow=db.update("table1",values,selection,selectionArgs);
                break;
            case TABLE2:
                String up_id=uri.getPathSegments().get(1);
                updateRow=db.update("table1",values,"id=?",new String[]{up_id});

                break;
            default:
                break;
        }

        return updateRow;
    }



























}
