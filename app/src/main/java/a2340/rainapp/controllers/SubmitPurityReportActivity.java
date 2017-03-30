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
import model.User;



/**
 * Created by austinletson on 3/15/17.
 */

public class SubmitPurityReportActivity extends AppCompatActivity {

    EditText latEdit;
    EditText longEdit;
    EditText virusEdit;
    EditText contaminantEdit;
    Spinner conditionSpinner;
    TextView errorView;
    TextView dateText;
    private UserDBHandler userDBHandler;
    private InputValidation inputValidation;
    private String[] arraySpinner;

    private PurityReport purityReport;

    private final AppCompatActivity activity = SubmitPurityReportActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_water_purity_report_page);

        //Set up automatic information displays
//        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd");
//        dateText.setText(ft.format(Calendar.getInstance().getTime()));


        //Set up condition spinner
        conditionSpinner = (Spinner) findViewById(R.id.purityConditionSpinner);

        this.arraySpinner = new String[]{
                PurityReportCondition.SAFE, PurityReportCondition.TREATABLE, PurityReportCondition.UNSAFE
        };

        ArrayAdapter<String> conditionAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                arraySpinner);
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
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd");
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
        double virusPPM = Double.parseDouble(virusEdit.getText().toString());
        double contaminatePPM = Double.parseDouble(contaminantEdit.getText().toString());
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
        if (!inputValidation.isEditTextFilled(virusEdit, errorView, getString(R.string.error_virusPPM))) {
            if (virusPPM < 0) {
                errorView.setText(getString(R.string.error_virusPPM));
                return;
            }
        }
        if (!inputValidation.isEditTextFilled(contaminantEdit, errorView, getString(R.string.error_contaminantPPM))) {
            if (contaminatePPM < 0) {
                errorView.setText(getString(R.string.error_contaminantPPM));
                return;
            }
        }



        //purityReport.set_username(user.get_username());
        purityReport.set_latitude(latitude);
        purityReport.set_longitude(longitude);
        purityReport.set_virusPPM(virusPPM);
        purityReport.set_contaminantPPM(contaminatePPM);
        purityReport.set_condition(conditionSpinner.getSelectedItem().toString());
        purityReport.set_reportDate(date);

        userDBHandler.addPurityReport(purityReport);

        errorView.setText("Report submitted");



    }
}
