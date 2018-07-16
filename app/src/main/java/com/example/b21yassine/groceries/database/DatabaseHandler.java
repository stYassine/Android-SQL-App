package com.example.b21yassine.groceries.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.b21yassine.groceries.models.Grocery;
import com.example.b21yassine.groceries.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_DATABASE ="CREATE TABLE "+ Util.TABLE_NAME+" ( "+Util.KEY_ID+" INTEGER PRIMARY KEY, "+Util.KEY_NAME+"  TEXT, "+Util.KEY_QUANTITY+" TEXT, "+Util.KEY_DATE_ADDED+" LONG ) ";

        sqLiteDatabase.execSQL(CREATE_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME+" ");

        onCreate(sqLiteDatabase);

    }


    /// create grocery
    public void createGrocery(Grocery grocery){

        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues contentValues =new ContentValues();
        contentValues.put(Util.KEY_NAME, grocery.getName());
        contentValues.put(Util.KEY_QUANTITY, grocery.getQuantity());
        contentValues.put(Util.KEY_DATE_ADDED, java.lang.System.currentTimeMillis());

        db.insert(Util.TABLE_NAME,null,contentValues);

    }


    /// get single grocery
    public Grocery getGrocery(int id){

        SQLiteDatabase db =this.getReadableDatabase();

        Grocery grocery =new Grocery();

        Cursor cursor =db.query(
                Util.TABLE_NAME,
                new String[]{ Util.KEY_ID, Util.KEY_NAME, Util.KEY_QUANTITY, Util.KEY_DATE_ADDED },
                Util.KEY_ID+"=?",
                new String[]{ String.valueOf(id) },
                null, null, null, null
        );

        if(cursor.moveToFirst()){


            grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.KEY_ID))));
            grocery.setName(cursor.getString(cursor.getColumnIndex(Util.KEY_NAME)));
            grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Util.KEY_QUANTITY)));

            /// formate Date
            java.text.DateFormat dateFormat =java.text.DateFormat.getDateInstance();
            String formated_date =dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Util.KEY_DATE_ADDED))).getTime());

            grocery.setDate_added(formated_date);

        }

        return grocery;
    }


    /// get all groceries
    public List<Grocery> getAllGroceries(){

        SQLiteDatabase db =this.getReadableDatabase();

        List<Grocery> groceryList =new ArrayList<>();

        Cursor cursor =db.query(
                Util.TABLE_NAME,
                new String[]{ Util.KEY_ID, Util.KEY_NAME, Util.KEY_QUANTITY, Util.KEY_DATE_ADDED },
                null, null, null, null,
                Util.KEY_DATE_ADDED+" DESC"
        );


        if(cursor.moveToFirst()){
            do {

                Grocery grocery =new Grocery();
                grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.KEY_ID))));
                grocery.setName(cursor.getString(cursor.getColumnIndex(Util.KEY_NAME)));
                grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Util.KEY_QUANTITY)));

                /// Format Date
                java.text.DateFormat dateFormat =java.text.DateFormat.getDateInstance();
                String formated_date =dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Util.KEY_DATE_ADDED))).getTime());

                grocery.setDate_added(formated_date);

                groceryList.add(grocery);


            }while (cursor.moveToNext());
        }

        return groceryList;

    }

    /// update grocery
    public int updateGrocery(Grocery grocery){

        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues contentValues =new ContentValues();
        contentValues.put(Util.KEY_NAME, grocery.getName());
        contentValues.put(Util.KEY_QUANTITY, grocery.getQuantity());
        contentValues.put(Util.KEY_DATE_ADDED, java.lang.System.currentTimeMillis());

        return db.update(
                Util.TABLE_NAME,
                contentValues,
                Util.KEY_ID+"=?",
                new String[]{ String.valueOf(grocery.getId()) }
        );

    }

    /// delete grocery
    public void deleteGrocery(int id){

        SQLiteDatabase db =this.getWritableDatabase();

        db.delete(
                Util.TABLE_NAME,
                Util.KEY_ID+"=?",
                new String[]{ String.valueOf(id) }
        );


    }

    /// count groceries
    public int countGroceries(){

        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =db.rawQuery("SELECT * FROM "+Util.TABLE_NAME, null);

        return cursor.getCount();
    }



}
