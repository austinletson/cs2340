package tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import a2340.rainapp.controllers.ViewHistoryReport;
import model.PurityReport;
import model.PurityReportCondition;



public class ViewHistoryReportTest {
    @Test
    public void historyButtonPressed() throws Exception {
        ViewHistoryReport vhr = new ViewHistoryReport();
        ArrayList<PurityReport> reports = new ArrayList<>();
        reports.add(new PurityReport(10, 10, "Hello", PurityReportCondition.SAFE, 25, 7));
        HashMap<Integer, ArrayList<Double>> map = vhr.grabMap(10, 10, 2010,  reports,"Virus");
        if(map.get(1).get(0) == 25) {

        }

    }

}