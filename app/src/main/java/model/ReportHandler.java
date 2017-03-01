package model;

import java.util.ArrayList;

/**
 * Created by austinletson on 2/28/17.
 */

public class ReportHandler {
    private ArrayList<Report> reports = new ArrayList<>();
    private int nextId;
    private static ReportHandler handler = new ReportHandler();


    public int getNextId() {
        nextId++;
        return nextId;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public static ReportHandler getHandler() {
        return handler;
    }

    public ReportHandler() {
        handler = this;
        reports.add(new Report(1, 1, new User("a", "a", User.UserType.ADMIN), Report.Type.BOTTLED, Report.Condition.POTABLE));
    }


}
