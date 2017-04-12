package model;

import java.util.ArrayList;

/**
 * Created by austinletson on 2/28/17.
 * Version 1.0
 */

class ReportHandler {
    private final ArrayList<Report> reports = new ArrayList<>();
    private final ArrayList<PurityReport> purityReports = new ArrayList<>();
    private int nextId = 1;
    private final int nextPurityId = 1;


    /**
     * gets all the purity reports in an arraylist
     * @return an array list of reports
     */
    public ArrayList<PurityReport> getPurityReports() {
        return purityReports;
    }

    /**

     * gets the next id that will be generated
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

     * gets the next id that will be generated
     * @return the id of the next report generated
     */
    public int getNextPurityId() {
        return nextPurityId;
    }

    /**
     * Generates the next purity id and preps to generate another
     * @return the id that was generated
     */
    public int getNextPurityIdAndIncrement() {
        nextId++;
        return nextPurityId;
    }

    /**
     * gets all the reports in an arraylist
     * @return an array list of reports
     */
    public ArrayList<Report> getReports() {
        return reports;
    }



    /**
     * adds given report
     * @param reportToAdd report added
     */
    public void addReport(Report reportToAdd) {
        reports.add(reportToAdd);
    }

    /**
     * adds given report
     * @param reportToAdd report added
     */
    public void addPurityReport(PurityReport reportToAdd) {
        purityReports.add(reportToAdd);
    }


}
