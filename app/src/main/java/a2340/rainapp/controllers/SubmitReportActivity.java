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

import org.w3c.dom.Text;

import a2340.rainapp.R;
import model.Report;
import model.ReportHandler;
import model.UserHandler;

/**
 * Created by austinletson on 3/1/17.
 */

public class SubmitReportActivity extends AppCompatActivity {
    EditText latEdit;
    EditText longEdit;
    Spinner typeSpinner;
    Spinner conditionSpinner;
    TextView errorView;
    TextView dateText;
    TextView idNumberText;
    TextView nameText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_report_page);
        latEdit = (EditText) findViewById(R.id.latitudeText);
        longEdit = (EditText) findViewById(R.id.longitudeText);
        errorView = (TextView) findViewById(R.id.reportErrorView);
        dateText = (TextView) findViewById(R.id.dateText);
        idNumberText = (TextView) findViewById(R.id.reportNumberText);
        nameText =(TextView) findViewById(R.id.reportNameText);


        //Set up automatic information displays
        SimpleDateFormat ft =
                new SimpleDateFormat ("E yyyy.MM.dd");
        dateText.setText(ft.format(Calendar.getInstance().getTime()));
        idNumberText.setText("" + ReportHandler.getHandler().getNextId());
        nameText.setText(UserHandler.getHandler().get_currentUser().get_username());


        //Set up type spinner
        typeSpinner = (Spinner) findViewById(R.id.reportTypeSpinner);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Report.Type.values());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        //Set up condition spinner
        conditionSpinner = (Spinner) findViewById(R.id.waterConditionSpinner);
        ArrayAdapter<String> conditionAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Report.Condition.values());
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
            ReportHandler.getHandler().addReport(new Report(
                    latitude, longitude,
                    UserHandler.getHandler().get_currentUser(),
                    (Report.Type) typeSpinner.getSelectedItem(),
                    (Report.Condition) conditionSpinner.getSelectedItem()));
            errorView.setText("Report Submitted");
        } catch (NumberFormatException e) {
            errorView.setText("Long and lat must be integers");
        }
    }
}
