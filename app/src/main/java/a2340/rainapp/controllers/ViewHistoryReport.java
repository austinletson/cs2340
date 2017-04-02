package a2340.rainapp.controllers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;

import a2340.rainapp.R;
import model.PurityReport;
import model.ReportHandler;


/**
 * Created by cpettiford
 */

public class ViewHistoryReport extends AppCompatActivity {


    LineGraphSeries<DataPoint> series;
    GraphView graphView;
    Spinner spinner;
    EditText dateEditText;
    EditText latEditText;
    EditText longEditText;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_quality_history_graph);
        graphView = (GraphView) findViewById(R.id.graph);
        dateEditText = (EditText) findViewById(R.id.historyYearTextEdit);
        latEditText = (EditText) findViewById(R.id.historyLatEdit);
        longEditText = (EditText) findViewById(R.id.historyLongEdit);


        String[] spinnerValues = new String[] {
                "Virus", "Contaminant"
        };


        spinner = (Spinner) findViewById(R.id.historySpinner);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(typeAdapter);
    }

    /**
     * called when button is pressed and history needs to be generated
     * @param view
     */
    public void historyButtonPressed(View view) {
        ArrayList<PurityReport> reports = ReportHandler.getHandler().getPurityReports();

        //Get data
        int year = Integer.parseInt(dateEditText.getText().toString());
        double latitude = Double.parseDouble(latEditText.getText().toString());
        double longitude = Double.parseDouble(longEditText.getText().toString());
        String spinnerValue = spinner.getSelectedItem().toString();

        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        boolean areThereReports = false;
        for (PurityReport report: reports) {

            //Grab year from report date
            Calendar cal = Calendar.getInstance();
            cal.setTime(report.get_reportDateAsDate());
            int reportYear = cal.get(Calendar.YEAR);

            if (report.get_latitude() == latitude
                    && report.get_longitude() == longitude
                    && reportYear == year){
                areThereReports = true;
                if (spinnerValue.equals("Virus")) {
                    dataPoints.add(new DataPoint(cal.get(Calendar.MONTH), report.get_virusPPM()));
                } else if(spinnerValue.equals("Contaminant")) {
                    dataPoints.add(new DataPoint(cal.get(Calendar.MONTH), report.get_contaminantPPM()));
                }
            }
        }

        if (areThereReports) {
            //convert to array and display graph
            DataPoint[] dataPointsAsArray = new DataPoint[dataPoints.size()];
            dataPointsAsArray = dataPoints.toArray(dataPointsAsArray);
            graphView.addSeries(new LineGraphSeries<>(dataPointsAsArray));
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(ViewHistoryReport.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("You do not have permission to view purity reports");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
}
