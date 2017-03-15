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
import model.PurityReport;
import model.ReportHandler;
import model.UserHandler;

/**
 * Created by austinletson on 3/15/17.
 */

public class SubmitPurityReportActivity extends AppCompatActivity {
    EditText latEdit;
    EditText longEdit;
    EditText virusEdit;
    EditText contaminantEdit;
    Spinner conditionSpinner;;
    TextView errorView;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_water_purity_report_page);
        latEdit = (EditText) findViewById(R.id.purityLatitudeDecimal);
        longEdit = (EditText) findViewById(R.id.purityLongitudeDecimal);
        errorView = (TextView) findViewById(R.id.purityErrorText);
        dateText = (TextView) findViewById(R.id.purityDate);
        virusEdit = (EditText) findViewById(R.id.virusPPMEditText);
        contaminantEdit = (EditText) findViewById(R.id.contaminantPPMEditText);


        //Set up automatic information displays
        SimpleDateFormat ft =
                new SimpleDateFormat ("E yyyy.MM.dd");
        dateText.setText(ft.format(Calendar.getInstance().getTime()));


        //Set up condition spinner
        conditionSpinner = (Spinner) findViewById(R.id.purityConditionSpinner);
        ArrayAdapter<String> conditionAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                PurityReport.OverallCondition.values());
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionAdapter);

    }

    /**
     * called when the submit button is pressed
     * @param view
     */
    protected void submit(View view) {
        try {
            double latitude = Double.parseDouble(latEdit.getText().toString());
            double longitude = Double.parseDouble(longEdit.getText().toString());
            double virusPPM = Double.parseDouble(virusEdit.getText().toString());
            double contaminatePPM = Double.parseDouble(contaminantEdit.getText().toString());
            ReportHandler.getHandler().addPurityReport(new PurityReport(
                    latitude, longitude,
                    UserHandler.getHandler().get_currentUser(),
                    (PurityReport.OverallCondition) conditionSpinner.getSelectedItem(),
                    virusPPM, contaminatePPM));
            errorView.setText("Report Submitted");
        } catch (NumberFormatException e) {
            errorView.setText("Long and lat must be integers");
        }
    }
}
