package a2340.rainapp.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import a2340.rainapp.R;
import database.InputValidation;
import model.SourceReportCondition;
import model.SourceReportWaterType;
import database.UserDBHandler;

import model.Report;

/**
 * Created by austinletson on 3/1/17.
 * Version 1.0
 */

public class SubmitReportActivity extends AppCompatActivity {

    private EditText latEdit;
    private EditText longEdit;

    private Spinner conditionSpinner;
    private Spinner typeSpinner;
    private TextView errorView;
    private TextView dateText;

    private InputValidation inputValidation;


    private Report sourceReport;

    private final AppCompatActivity activity = SubmitReportActivity.this;

    private UserDBHandler userDBHandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String[] arraySpinnerCondition;
        String[] arraySpinnerType;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_report_page);


        conditionSpinner = (Spinner) findViewById(R.id.waterConditionSpinner);

        arraySpinnerCondition = new String[]{
                SourceReportCondition.WASTE, SourceReportCondition.TREATABLE_MUDDY,
                SourceReportCondition.TREATABLE_CLEAR, SourceReportCondition.TREATABLE_POTABLE
        };

        ArrayAdapter<String> conditionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                arraySpinnerCondition);
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionAdapter);


        typeSpinner = (Spinner) findViewById(R.id.reportTypeSpinner);

        arraySpinnerType = new String[]{
                SourceReportWaterType.BOTTLED, SourceReportWaterType.LAKE, SourceReportWaterType.SPRING,
                SourceReportWaterType.STREAM, SourceReportWaterType.WELL, SourceReportWaterType.OTHER
        };


        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                arraySpinnerType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        initViews();
        initObjects();

    }

    private void initViews() {
        latEdit = (EditText) findViewById(R.id.latitudeText);
        longEdit = (EditText) findViewById(R.id.longitudeText);
        errorView = (TextView) findViewById(R.id.reportErrorView);
        TextView nameText = (TextView) findViewById(R.id.reportNameText);


        dateText = (TextView) findViewById(R.id.dateText);
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd", Locale.getDefault());
        dateText.setText(ft.format(Calendar.getInstance().getTime()));


        nameText.setText(LoginActivity.loggedInUser);

        //TextView typeText = (TextView) findViewById(R.id.typeLabel);
    }

    private void initObjects() {
        userDBHandler = new UserDBHandler(activity);
        inputValidation = new InputValidation(activity);
        sourceReport = new Report();

    }


    /**
     * called when the submit button is pressed
     *
     * @param view view
     */
    public void submit(View view) {
        storeSourceReportData();
    }

    private void storeSourceReportData() {


        double latitude = Double.parseDouble(latEdit.getText().toString());
        double longitude = Double.parseDouble(longEdit.getText().toString());
        String date = dateText.getText().toString();

        if (inputValidation.isEditTextFilled(latEdit, errorView, getString(R.string.error_latitude))) {
            if (latitude < 0) {
                errorView.setText(getString(R.string.error_latitude));
                return;
            }
        }
        if (inputValidation.isEditTextFilled(longEdit, errorView, getString(R.string.error_longitude))) {
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
        sourceReport.set_username(LoginActivity.loggedInUser);

        userDBHandler.addSourceReport(sourceReport);

        errorView.setText(getString(R.string.report_submitted));



    }
}
