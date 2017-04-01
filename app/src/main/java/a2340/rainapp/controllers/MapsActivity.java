package a2340.rainapp.controllers;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import a2340.rainapp.R;
import model.PurityReport;
import model.Report;
import database.UserDBHandler;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private UserDBHandler db = new UserDBHandler(MapsActivity.this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * @param googleMap map to populate
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        placeSourceReports();
        placePurityReports();
    }



    private void placeSourceReports() {
        //Retrieve source reports from database
        List<Report> reports = db.getAllSourceReports();
        //Cycle through all of reports to add a marker
        for (Report report : reports) {
            //Get attributes for one report
            double _latitude = report.get_latitude();
            double _longitude = report.get_longitude();
            long reportNumber = report.get_reportNumber();
            String reportDate = report.get_reportDate();
            String type = report.get_type();
            String condition = report.get_condition();

            //Add a marker for one report
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(_latitude, _longitude))
                    .title("Date: " + reportDate)
                    .snippet("Condition: " + condition
                            + ", Type: " + type
                            + ", Pos: (" + _latitude + ", " + _longitude + ")"));

        }
    }

    private void placePurityReports(){
        //Retrieve purity reports from database
        List<PurityReport> purityReports = db.getAllPurityReports();
        //Cycle through all of reports to add a marker
        for(PurityReport report: purityReports) {
            //Get attributes for one report
            double _latitude = report.get_latitude();
            double _longitude = report.get_longitude();
            long reportNumber = report.get_reportNumber();
            String reportDate = report.get_reportDate();
            double virusPPM = report.get_virusPPM();
            double contaminantPPM = report.get_contaminantPPM();
            String condition = report.get_condition();

            //Add a marker for one report
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(_latitude, _longitude))
                    .title("Date: " + reportDate)
                    .snippet("Condition: " + condition
                            + ", virusPPM: " + virusPPM
                            + ", contaminantPPM: " + contaminantPPM
                            + ", Pos: (" + _latitude + ", " + _longitude + ")"));

        }


    }
}
