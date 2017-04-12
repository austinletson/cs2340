package a2340.rainapp.controllers;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


import database.UserDBHandler;

/**
 * Created by austinletson on 2/28/17.
 * Version 1.0
 */

public class ViewReportsActivity extends ListActivity {

    private UserDBHandler userDBHandler;

    private final ArrayList<String> sourceReports = new ArrayList<>();


    private SQLiteDatabase newDB;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateReports();

        displayResultList();

    }

    private void displayResultList() {
        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sourceReports));
        getListView().setTextFilterEnabled(true);

    }
    private void populateReports() {
        String tableName = UserDBHandler.TABLE_SOURCE_REPORTS;

        try {
            userDBHandler = new UserDBHandler(this.getApplicationContext());
            newDB = userDBHandler.getWritableDatabase();
            String q = "SELECT * FROM " + tableName;
            Cursor c = newDB.rawQuery(q, null);


            if (c.moveToFirst()) {
                do {

                    String reportNumber = c.getString(c.getColumnIndex("report_number"));
                    String condition = c.getString(c.getColumnIndex("condition"));
                    String latitude = c.getString(c.getColumnIndex("latitude"));
                    String longitude = c.getString(c.getColumnIndex("longitude"));
                    String type = c.getString(c.getColumnIndex("type"));
                    String date = c.getString(c.getColumnIndex("date"));
                    String username = c.getString(c.getColumnIndex("username"));



                    sourceReports.add("Username: " + username + ", Report number: " + reportNumber + ",Condition: "
                            + condition + ",Latitude: " + latitude +
                            ",Longitude: " + longitude +
                            ",Type: " + type + ",Date: " + date);
                }while (c.moveToNext());
            }
            c.close();
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            newDB.close();
        }

    }

}
