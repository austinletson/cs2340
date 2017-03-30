package a2340.rainapp.controllers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import a2340.rainapp.R;
import database.InputValidation;
import model.SourceReportCondition;
import model.SourceReportWaterType;
import model.SourceReportWaterType;
import database.UserDBHandler;
import model.User;

import model.SourceReportCondition;
import model.Report;

/**
 * Created by austinletson on 3/1/17.
 */

public class SubmitReportActivity extends AppCompatActivity {

    EditText latEdit;
    EditText longEdit;

    Spinner conditionSpinner;
    Spinner typeSpinner;
    TextView errorView;
    TextView dateText;
    TextView typeText;
    TextView nameText;

    private String username;
    private InputValidation inputValidation;
    private String[] arraySpinner1;
    private String[] arraySpinner2;

    private Report sourceReport;

    private final AppCompatActivity activity = SubmitReportActivity.this;

    private UserDBHandler userDBHandler;

    private ArrayList<String> sourceReports = new ArrayList<String>();
    private String tableName = userDBHandler.TABLE_USERS;
    private SQLiteDatabase newDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_report_page);


        conditionSpinner = (Spinner) findViewById(R.id.waterConditionSpinner);

        this.arraySpinner1 = new String[]{
                SourceReportCondition.WASTE, SourceReportCondition.TREATABLE_MUDDY,
                SourceReportCondition.TREATABLE_CLEAR, SourceReportCondition.TREATABLE_POTABLE
        };

        ArrayAdapter<String> conditionAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                arraySpinner1);
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionAdapter);


        typeSpinner = (Spinner) findViewById(R.id.reportTypeSpinner);

        this.arraySpinner2 = new String[]{
                SourceReportWaterType.BOTTLED, SourceReportWaterType.LAKE, SourceReportWaterType.SPRING,
                SourceReportWaterType.STREAM, SourceReportWaterType.WELL, SourceReportWaterType.OTHER
        };


        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                arraySpinner2);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        initViews();
        initObjects();

    }

    private void initViews() {
        latEdit = (EditText) findViewById(R.id.latitudeText);
        longEdit = (EditText) findViewById(R.id.longitudeText);
        errorView = (TextView) findViewById(R.id.reportErrorView);
        nameText = (TextView) findViewById(R.id.reportNameText);


        dateText = (TextView) findViewById(R.id.dateText);
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd");
        dateText.setText(ft.format(Calendar.getInstance().getTime()));

        openAndQueryDatabase();

        nameText.setText(username);

        typeText = (TextView) findViewById(R.id.typeLabel);
    }

    private void initObjects() {
        userDBHandler = new UserDBHandler(activity);
        inputValidation = new InputValidation(activity);
        sourceReport = new Report();

    }


    /**
     * called when the submit button is pressed
     *
     * @param view
     */
    public void submit(View view) {
        postDataToSQLite();
    }

    private void postDataToSQLite() {

        User user = new User();
        String username = user.get_username();
        System.out.println(username);

        double latitude = Double.parseDouble(latEdit.getText().toString());
        double longitude = Double.parseDouble(longEdit.getText().toString());
        String date = dateText.getText().toString();

        if (!inputValidation.isEditTextFilled(latEdit, errorView, getString(R.string.error_latitude))) {
            if (latitude < 0) {
                errorView.setText(getString(R.string.error_latitude));
                return;
            }
        }
        if (!inputValidation.isEditTextFilled(longEdit, errorView, getString(R.string.error_longitude))) {
            if (longitude < 0) {
                errorView.setText(getString(R.string.error_longitude));
                return;
            }
        }


        sourceReport.set_latitude(latitude);
        sourceReport.set_longitude(longitude);
        sourceReport.set_type(typeSpinner.getSelectedItem().toString());
        sourceReport.set_condition(conditionSpinner.getSelectedItem().toString());
        sourceReport.set_reportDate(date);
        sourceReport.set_username(username);

        userDBHandler.addSourceReport(sourceReport);

        errorView.setText("Report submitted");



    }

    private void openAndQueryDatabase() {
        try {
            userDBHandler = new UserDBHandler(this.getApplicationContext());
            newDB = userDBHandler.getWritableDatabase();
            String q = "SELECT username FROM " + tableName;
            Cursor c = newDB.rawQuery(q, null);

            if (c != null ) {
                if (c.moveToFirst()) {
                    username = c.getString(c.getColumnIndex("username"));


                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            newDB.close();
        }

    }
}
