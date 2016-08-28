package com.example.ankit.exchangerate;

/**
 * Created by ankit on 28/8/16.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
public class DBController extends SQLiteOpenHelper {
    private static final String tablename = "buyrecord";  // tablename
    private static final String date = "date";  // column name
    private static final String id = "ID";  // auto generated ID column
    private static final String currency = "currency"; // column name
    private static final String quantity = "quantity"; // column name
    private static final String buyvalue = "buyvalue"; // column name
    private static final String databasename = "buyinfo"; // Dtabasename
    private static final int versioncode = 1; //versioncode of the database
    public DBController(Context context) {
        super(context, databasename, null, versioncode);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS " + tablename + "(" + id + " integer primary key, " + date + " date, "+ currency + " text,"+ quantity + " integet," + buyvalue + " double)";
        database.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old,
                          int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS " + tablename;
        database.execSQL(query);
        onCreate(database);
    }
    public ArrayList<HashMap<String, String>> getAllPlace() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM " + tablename;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", cursor.getString(0));
                map.put("date", cursor.getString(1));
                map.put("currency", cursor.getString(2));
                map.put("quantity", cursor.getString(3));
                map.put("buyvalue", cursor.getString(4));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        // return contact list
        return wordList;
    }
}