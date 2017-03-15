package a2340.rainapp.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import a2340.rainapp.R;
import model.PurityReport;
import model.Report;
import model.ReportHandler;

/**
 * Created by austinletson on 3/15/17.
 */

public class ViewWaterPurityReportsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_purity_reports);
        final ListView listView = (ListView) findViewById(R.id.purityList);
        final ArrayAdapter<PurityReport> adapter = new ArrayAdapter<PurityReport>(this,
                android.R.layout.simple_expandable_list_item_1, ReportHandler.getHandler().getPurityReports());
        listView.setAdapter(adapter);
    }
}
