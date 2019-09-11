package com.example.simplyshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DATABASEMANGAER {
    private Context context;
    private SQLHelper helper;
    private SQLiteDatabase db;

    public static final String pantry_DB_Name = "pantry";
    public static final int pantry_DB_version = 2;
    public static final String pantry_DB_tableName = "pantryItem";
    private static final String Create = String.format("CREATE TABLE " + pantry_DB_tableName +
            "(itemID INTEGER PRIMARY KEY, " +
            "name TEXT, " +
            "price TEXT, " +
            "quantity TEXT, " +
            "location TEXT, " +
            "imgID TEXT);");

    //constructor which is also used to open database to write
    public DATABASEMANGAER(Context c){
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    //opening for read
    public DATABASEMANGAER openReadable() throws android.database.SQLException {
        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return DATABASEMANGAER.this;
    }

    //close the database helper
    public void close(){
        helper.close();
    }

    //clear table
    public void clearTable(){
        db = helper.getReadableDatabase();
        db.delete(pantry_DB_Name, null, null);
    }

    //create a row
    public boolean createRow(Integer id, String name, String price, String quantity, String location, String imgID) {

        synchronized (this.db) {
            ContentValues newItem = new ContentValues();
            newItem.put("itemID", id);
            newItem.put("name", name);
            newItem.put("price", price);
            newItem.put("quantity", quantity);
            newItem.put("location", location);
            newItem.put("imgID", imgID);

            try {
                db.insertOrThrow(pantry_DB_tableName, null, newItem);
            }catch (Exception e){
                Log.w("DATBASEMANAGER", "error in inserting row: \n" + e.toString());
               e.printStackTrace();
               return false;
            }

            return true;
        }

    }



    //retrieve table from database
    public ArrayList<String[]> retrieveRows(){
        ArrayList<String[]> itemRow = new ArrayList<String[]>();
        String[] columns = new String[] {"itemID", "name", "price", "quantity", "location", "imgID"};

        Cursor cursor = db.query(pantry_DB_tableName, columns, null, null, null, null, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            String[] tableRow = new String[columns.length];
            tableRow[0] = Integer.toString(cursor.getInt(0));
            tableRow[1] = cursor.getString(1);
            tableRow[2] = cursor.getString(2);
            tableRow[3] = cursor.getString(3);
            tableRow[4] = cursor.getString(4);
            tableRow[5] = cursor.getString(5);
            itemRow.add(tableRow);

            Log.i("db: ", "here2");
            cursor.moveToNext();
        }
        Log.i("db: ", "here3");
        if(cursor != null && !cursor.isClosed() ){
            cursor.close();
        }
        return itemRow;
    }




    //class to open database and create table
    private class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper(Context c){
            super(c, pantry_DB_Name, null, pantry_DB_version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Create);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + pantry_DB_tableName);
            onCreate(db);
        }
    }

}
