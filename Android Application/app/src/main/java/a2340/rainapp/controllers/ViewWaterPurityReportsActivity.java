package a2340.rainapp.controllers;

import java.util.ArrayList;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import database.UserDBHandler;




/**
 * Created by cpettiford on 3/28/17.
 * Version 1.0
 */

public class ViewWaterPurityReportsActivity extends ListActivity {

    private UserDBHandler userDBHandler;

    private final ArrayList<String> purityReports = new ArrayList<>();
    private SQLiteDatabase newDB;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populatePurityReports();

        displayResultList();

    }

    private void displayResultList() {
        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, purityReports));
        getListView().setTextFilterEnabled(true);

    }
    private void populatePurityReports() {

        String tableName = userDBHandler.TABLE_PURITY_REPORTS;

        try {
            userDBHandler = new UserDBHandler(this.getApplicationContext());
            newDB = userDBHandler.getWritableDatabase();
            String q = "SELECT * FROM " + tableName;
            Cursor c = newDB.rawQuery(q, null);

            if (c != null ) {
                if (c.moveToFirst()) {
                    do {
                        String condition = c.getString(c.getColumnIndex("condition"));
                        String latitude = c.getString(c.getColumnIndex("latitude"));
                        String longitude = c.getString(c.getColumnIndex("longitude"));
                        String virusPPM = c.getString(c.getColumnIndex("virus_ppm"));
                        String contaminantPPM = c.getString(c.getColumnIndex("contaminant_ppm"));
                        String date = c.getString(c.getColumnIndex("date"));
                        String username = c.getString(c.getColumnIndex("username"));



                        purityReports.add("Username: " + username + ",Condition: " + condition + ",Latitude: " + latitude +
                                ",Longitude: " + longitude + ",Virus PPM: " + virusPPM +
                                ",Contaminant PPM: " + contaminantPPM + ",Date: " + date);
                    }while (c.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            newDB.close();
        }

    }
}