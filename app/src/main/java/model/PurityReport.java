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

    /**
     *
     * @param _latitude lat to set
     * @param _longitude long to set
     * @param _user user who made reptort
     * @param _condition condition of report
     * @param virusPPM virus PPM to set
     * @param contaminantPPM contaminant PPM to set
     */
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

    /**
     * Enum class to store condition
     */
    public enum OverallCondition {
        SAFE, TREATABLE, UNSAFE
    }
}
