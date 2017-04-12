package tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import a2340.rainapp.controllers.ViewHistoryReport;
import model.PurityReport;
import model.PurityReportCondition;

import static org.junit.Assert.*;

/**
 * Created by austinletson on 4/11/17.
 */
public class ViewHistoryReportTest {
    @Test
    public void historyButtonPressed() throws Exception {
        ViewHistoryReport vhr = new ViewHistoryReport();
        PurityReport purityReport = new PurityReport();
        ArrayList<PurityReport> reports = new ArrayList<>();
        reports.add(new PurityReport(10, 10, "Hello", PurityReportCondition.SAFE, 25, 7));
        HashMap<Integer, ArrayList<Double>> map = vhr.grabMap(10, 10, 2010,  reports,"Virus");
        assert (map.get(1).get(0) == 25);

    }

}