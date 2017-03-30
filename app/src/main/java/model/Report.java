package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by austinletson on 2/28/17.
 */

public class Report {

    private Date _reportDate;
    private int _reportNumber;
    private User _user;
    private double _latitude;
    private double _longitude;
    private Type _type;
    private Condition _condition;

    /**
     * Constructor for report
     * @param _latitude
     * @param _longitude
     * @param _user
     * @param _type
     * @param _condition
     */
    public Report(double _latitude, double _longitude, User _user, Type _type, Condition _condition) {
        this._latitude = _latitude;
        this._longitude = _longitude;
        this._user = _user;
        this._type = _type;
        this._condition = _condition;
        _reportDate = Calendar.getInstance().getTime();
        _reportNumber = ReportHandler.getHandler().getNextIdAndIncrement();
    }

    @Override
    public String toString() {
        return "" + _type + "/" +_condition +" @(" + _latitude + ", " + _longitude +")";
    }

    /**
     *
     * @return latitude
     */
    public double get_latitude() {
        return _latitude;
    }

    /**
     *
     * @return longitude
     */
    public double get_longitude() {
        return _longitude;
    }

    /**
     *
     * @return report date
     */
    public Date get_reportDate() {
        return _reportDate;
    }

    /**
     *
     * @return report number
     */
    public int get_reportNumber() {
        return _reportNumber;
    }

    /**
     *
     * @return report type
     */
    public Type get_type() {
        return _type;
    }

    /**
     *
     * @return report condition
     */
    public Condition get_condition() {
        return _condition;
    }

    /**
     * Enum of report type
     */
    public enum Type {
        BOTTLED, WELL, STREAM, LAKE, SPRING, OTHER
    }

    /**
     * Enum of report condition
     */
    public enum Condition {
        WASTE, TREATABLE_CLEAR, TREATABLE_MUDDY, POTABLE
    }
}
