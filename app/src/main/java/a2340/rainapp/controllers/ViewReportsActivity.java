package a2340.rainapp.controllers;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import a2340.rainapp.R;
import model.Report;
import model.ReportHandler;

/**
 * Created by austinletson on 2/28/17.
 */

public class ViewReportsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reports_page);
        final ListView listView = (ListView) findViewById(R.id.list);
        final ArrayAdapter<Report> adapter = new ArrayAdapter<Report>(this,
                android.R.layout.simple_expandable_list_item_1, ReportHandler.getHandler().getReports());
        listView.setAdapter(adapter);
    }
}
