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

import a2340.rainapp.R;
import database.InputValidation;
import model.PurityReport;
import database.UserDBHandler;
import model.PurityReportCondition;



/**
 * Created by austinletson on 3/15/17.
 * Version 1.0
 */

public class SubmitPurityReportActivity extends AppCompatActivity {

    private EditText latEdit;
    private EditText longEdit;
    private EditText virusEdit;
    private EditText contaminantEdit;
    private Spinner conditionSpinner;
    private TextView errorView;
    private TextView dateText;
    private UserDBHandler userDBHandler;
    private InputValidation inputValidation;

    private PurityReport purityReport;

    private final AppCompatActivity activity = SubmitPurityReportActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_water_purity_report_page);



        //Set up condition spinner
        conditionSpinner = (Spinner) findViewById(R.id.purityConditionSpinner);

        String[] arraySpinnerCondition = new String[]{
                PurityReportCondition.SAFE, PurityReportCondition.TREATABLE, PurityReportCondition.UNSAFE
        };

        ArrayAdapter<String> conditionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                arraySpinnerCondition);
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionAdapter);

        initViews();
        initObjects();

    }

    private void initViews() {
        setContentView(R.layout.submit_water_purity_report_page);
        latEdit = (EditText) findViewById(R.id.purityLatitudeDecimal);
        longEdit = (EditText) findViewById(R.id.purityLongitudeDecimal);
        errorView = (TextView) findViewById(R.id.purityErrorText);


        dateText = (TextView) findViewById(R.id.purityDate);
        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
        dateText.setText(ft.format(Calendar.getInstance().getTime()));

        virusEdit = (EditText) findViewById(R.id.virusPPMEditText);
        contaminantEdit = (EditText) findViewById(R.id.contaminantPPMEditText);
    }

    private void initObjects() {
        userDBHandler = new UserDBHandler(activity);
        inputValidation = new InputValidation(activity);
        purityReport = new PurityReport();

    }


    /**
     * called when the submit button is pressed
     *
     * @param view view
     */
    public void submit(View view) {
        storePurityReportData();
    }

    private void storePurityReportData() {

        double latitude = Double.parseDouble(latEdit.getText().toString());
        double longitude = Double.parseDouble(longEdit.getText().toString());
        double virusPPM = Double.parseDouble(virusEdit.getText().toString());
        double contaminatePPM = Double.parseDouble(contaminantEdit.getText().toString());
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
        if (inputValidation.isEditTextFilled(virusEdit, errorView, getString(R.string.error_virusPPM))) {
            if (virusPPM < 0) {
                errorView.setText(getString(R.string.error_virusPPM));
                return;
            }
        }
        if (inputValidation.isEditTextFilled(contaminantEdit, errorView, getString(R.string.error_contaminantPPM))) {
            if (contaminatePPM < 0) {
                errorView.setText(getString(R.string.error_contaminantPPM));
                return;
            }
        }



        purityReport.set_latitude(latitude);
        purityReport.set_longitude(longitude);
        purityReport.set_virusPPM(virusPPM);
        purityReport.set_contaminantPPM(contaminatePPM);
        purityReport.set_condition(conditionSpinner.getSelectedItem().toString());
        purityReport.set_reportDate(date);
        purityReport.set_username(LoginActivity.loggedInUser);

        userDBHandler.addPurityReport(purityReport);



        errorView.setText(getString(R.string.report_submitted));






    }
}
