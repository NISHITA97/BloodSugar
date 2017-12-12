package com.example.android.bloodsugar1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nishita Aggarwal on 22-07-2017.
 */

public class DatabaseEvening extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bloodsugarEvening";
    private static final String TABLE_NAME = "sugarEvening";
    private static final String KEY_E = "Evening";
    private static final String KEY_DATE="Date";

    public DatabaseEvening(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_SUGARMORNING_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_E + " INTEGER,"
                + KEY_DATE + " TEXT" + ")";
        database.execSQL(CREATE_SUGARMORNING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    void addData(Data d)
    {
        Log.e("addINdatabaseEVEning","called");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_E, d.level); // Contact Name
        //values.put(KEY_DATE,d1.toString());
        values.put(KEY_DATE,d.date);

        try {
            db.insert(TABLE_NAME, null, values);
        }
        catch (Exception e)
        {
            Log.e("insERT EVE","failed");
        }
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        //Log.e("ate1=",String.valueOf(d1.getDate()));
        Log.e("Date=",d.date);
    }

    public List<Data> readData() {
        List<Data> dataList = new ArrayList<Data>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("in readData","start");
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data d = new Data();
                d.level=Integer.parseInt(cursor.getString(0));
                d.date=cursor.getString(1);
                Log.e("d.date=",d.date);
                // Adding data to list
                dataList.add(d);
                Log.e("d.level",String.valueOf(d.level));
                Log.e("in getAllcont",dataList.toString());
            } while (cursor.moveToNext());
        }
        return dataList;
    }
}
