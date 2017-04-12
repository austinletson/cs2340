package a2340.rainapp.controllers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import a2340.rainapp.R;
import model.PurityReport;
import model.PurityReportCondition;
import database.UserDBHandler;


/**
 * Created by cpettiford
 */

public class ViewHistoryReport extends AppCompatActivity {


    //LineGraphSeries<DataPoint> series;
    private GraphView graphView;
    private Spinner spinner;
    private EditText dateEditText;
    private EditText latEditText;
    private EditText longEditText;
    private GridLabelRenderer gridLabel;

    //private final AppCompatActivity activity = ViewHistoryReport.this;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_quality_history_graph);
        graphView = (GraphView) findViewById(R.id.graph);
        dateEditText = (EditText) findViewById(R.id.historyYearTextEdit);
        latEditText = (EditText) findViewById(R.id.historyLatEdit);
        longEditText = (EditText) findViewById(R.id.historyLongEdit);


        String[] spinnerValues = new String[]{
                "Virus", "Contaminant"
        };


        spinner = (Spinner) findViewById(R.id.historySpinner);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerValues);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(typeAdapter);

        UserDBHandler udb = new UserDBHandler(ViewHistoryReport.this);


        gridLabel = graphView.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Month");
        gridLabel.setVerticalAxisTitle("Virus");

        udb.dummyPurityReport("bob", "7/23/2017", 10, 10, 80, 145, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "8/23/2017", 10, 10, 0, 134, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "9/23/2017", 10, 10, 50, 11, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "10/23/2017", 10, 10, 10, 10, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "11/23/2017", 10, 10, 10, 134, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "12/23/2017", 10, 10, 10, 10, PurityReportCondition.SAFE);

        udb.dummyPurityReport("bob", "7/23/2017", 10, 10, 70, 145, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "8/23/2017", 10, 10, 14, 134, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "9/23/2017", 10, 10, 119, 11, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "10/23/2017", 10, 10, 10, 10, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "11/23/2017", 10, 10, 40, 134, PurityReportCondition.SAFE);
        udb.dummyPurityReport("bob", "12/23/2017", 10, 10, 10, 10, PurityReportCondition.SAFE);
    }

    /**
     * called when button is pressed and history needs to be generated
     *
     * @param view view
     */
    public void historyButtonPressed(View view) {
        graphView.removeAllSeries();
        UserDBHandler dbHandler = new UserDBHandler(this.getApplicationContext());
        List<PurityReport> reports = dbHandler.getAllPurityReports();

        //Get data
        int year = Integer.parseInt(dateEditText.getText().toString());
        double latitude = Double.parseDouble(latEditText.getText().toString());
        double longitude = Double.parseDouble(longEditText.getText().toString());
        String spinnerValue = spinner.getSelectedItem().toString();

        ArrayList<DataPoint> dataPoints = new ArrayList<>();

        //Fill month average hash map with negative ones
        HashMap<Integer, ArrayList<Double>> averageMap = grabMap(latitude, longitude, year, reports, spinnerValue);

        if (averageMap != null) {
            for (int i = 1; i <= 12; i++) {
                if (averageMap.get(i).size() != 0) {
                    double sum = 0;
                    for (double d : averageMap.get(i)) {
                        sum += d;
                    }
                    double average = sum / averageMap.get(i).size();
                    dataPoints.add(new DataPoint(i, average));
                }
            }

            if (spinnerValue.equals("Virus")) {
                gridLabel.setVerticalAxisTitle("Virus");
            } else if (spinnerValue.equals("Contaminant")) {
                gridLabel.setVerticalAxisTitle("Contaminant");
            }


            //convert to array and display graph
            DataPoint[] dataPointsAsArray = new DataPoint[dataPoints.size()];
            dataPointsAsArray = dataPoints.toArray(dataPointsAsArray);
            graphView.addSeries(new LineGraphSeries<>(dataPointsAsArray));
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(ViewHistoryReport.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("There are no reports for this year and long/lat");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

HEAD
    private HashMap<Integer, ArrayList<Double>> grabMap(double latitude, double longitude, int year, List<PurityReport> reports, String spinnerValue) {

    /**
     * returns a map of all the months to the values for that month
     * @param latitude lat
     * @param longitude long
     * @param year year
     * @param reports reports to put in map
     * @param spinnerValue spinnervalue
     * @return The map of all
     */
    public HashMap<Integer, ArrayList<Double>> grabMap(double latitude, double longitude, int year, List<PurityReport> reports, String spinnerValue) {
 aa57442e3cbe9d1f6aec2272ea9955e41d07623f
        HashMap<Integer, ArrayList<Double>> averageMap = new HashMap<>();
        boolean areThereReports = false;
        for (int i = 1; i <= 12; i++) {
            averageMap.put(i, new ArrayList<Double>());
        }
        for (PurityReport report : reports) {


            //Grab year from report date
            Calendar cal = Calendar.getInstance();
            cal.setTime(report.get_reportDateAsDate());
            int reportYear = cal.get(Calendar.YEAR);

            if (report.get_latitude() == latitude
                    && report.get_longitude() == longitude
                    && reportYear == year) {
                areThereReports = true;


                if (spinnerValue.equals("Virus")) {
                    averageMap.get(cal.get(Calendar.MONTH) + 1).add(report.get_virusPPM());
                } else if (spinnerValue.equals("Contaminant")) {
                    averageMap.get(cal.get(Calendar.MONTH) + 1).add(report.get_contaminantPPM());
                }


            }


        }
        return (areThereReports) ? averageMap : null;
    }
}
