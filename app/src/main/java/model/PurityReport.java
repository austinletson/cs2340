package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by austinletson on 3/15/17.
 */

public class PurityReport {

    private Date _reportDate;
    private int _reportNumber;
    private User _user;
    private double _latitude;
    private double _longitude;
    private OverallCondition _condition;
    private double virusPPM;
    private double contaminantPPM;

    public PurityReport(double _latitude, double _longitude, User _user, OverallCondition _condition, double virusPPM, double contaminantPPM) {
        this._latitude = _latitude;
        this._longitude = _longitude;
        this._user = _user;
        this._condition = _condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        _reportDate = Calendar.getInstance().getTime();
        _reportNumber = ReportHandler.getHandler().getNextPurityIdAndIncrement();
    }

    @Override
    public String toString() {
        return _condition +" @(" + _latitude + ", " + _longitude +") , virusPPM:" + virusPPM + ", contaminatePPM:" + contaminantPPM;
    }

    public enum OverallCondition {
        SAFE, TREATABLE, UNSAFE
    }
}
