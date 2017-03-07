package a2340.rainapp.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;

import a2340.rainapp.R;
import model.Report;
import model.ReportHandler;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    private void place_reports(){
        //Retrieve reports from Report handler
        ArrayList<Report> reports = ReportHandler.getHandler().getReports();
        //Cycle through all of reports to add a marker
        for(Report report: reports) {
            //Get attributes for one report
            double _latitude = report.get_latitude();
            double _longitude = report.get_longitude();
            int reportNumber = report.get_reportNumber();
            Date reportDate = report.get_reportDate();
            Report.Type type = report.get_type();
            Report.Condition condition = report.get_condition();

            //Add a marker for one report
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(_latitude, _longitude))
                    .title("Report #: " + reportNumber)
                    .snippet("Date: " + reportDate
                    + "\nWater Condition: " + condition
                    + "\nWater Type" + type));

        }
    }

}
