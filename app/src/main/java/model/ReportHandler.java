package model;

import java.util.ArrayList;

/**
 * Created by austinletson on 2/28/17.
 */

public class ReportHandler {
    private ArrayList<Report> reports = new ArrayList<>();
    private int nextId = 1;
    private static ReportHandler handler = new ReportHandler();

    /**
     * gets the next id that will be genrated
     * @return the id of the next report generated
     */
    public int getNextId() {
        return nextId;
    }

    /**
     * Generates the next id and preps to generate another
     * @return the id that was generated
     */
    public int getNextIdAndIncrement() {
        nextId++;
        return nextId;
    }

    /**
     * gets all the reports in an arraylist
     * @return an array list of reports
     */
    public ArrayList<Report> getReports() {
        return reports;
    }

    /**
     * gets the report handler
     * @return gets the report handler
     */
    public static ReportHandler getHandler() {
        return handler;
    }


    private ReportHandler() {
        handler = this;
        reports.add(new Report(1, 1, new User("a", "a", User.UserType.ADMIN), Report.Type.BOTTLED, Report.Condition.POTABLE));
    }

    /**
     * adds given report
     * @param reportToAdd
     */
    public void addReport(Report reportToAdd) {
        reports.add(reportToAdd);
    }


}
