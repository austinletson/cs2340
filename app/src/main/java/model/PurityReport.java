package model;

import java.util.Calendar;
import java.util.Date;
import model.User;

/**
 * Created by austinletson on 3/15/17.
 */

public class PurityReport {

    private Date _reportDate;
    private long _reportNumber;
    private String _username;
    private double _latitude;
    private double _longitude;
    private String _condition;
    private double virusPPM;
    private double contaminantPPM;
    private User user = new User();

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
        this._username = _username;
        this._condition = _condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        _reportDate = _reportDate;
        _reportNumber = _reportNumber;
    }

    public PurityReport() {

    }

    public double get_latitude() {
        return _latitude;
    }

    public void set_latitude(double _latitude) {
        this._latitude = _latitude;
    }

    public double get_longitude() {
        return _longitude;
    }

    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }

    public String get_reportDate() {
        return _reportDate.toString();
    }

    public void set_reportDate(String _reportDate) {
        this._reportDate = Calendar.getInstance().getTime();
    }


    public double get_virusPPM() {
        return virusPPM;
    }

    public void set_virusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    public double get_contaminantPPM() {
        return contaminantPPM;
    }

    public void set_contaminantPPM(double contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }

    public String get_username() {
        return user.get_username();
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_condition() {
        return _condition;
    }

    public void set_condition(String _condition) {
        this._condition = _condition;
    }

    public long get_reportNumber() {
        return _reportNumber;
    }

    public void set_reportNumber(long _reportNumber) {
        this._reportNumber = _reportNumber;
    }


    @Override
    public String toString() {
        return _condition +" @(" + _latitude + ", " + _longitude +") , virusPPM:" + virusPPM +
                ", contaminatePPM:" + contaminantPPM;
    }

    /**
     * Enum class to store condition
     */
    public enum OverallCondition {
        SAFE, TREATABLE, UNSAFE
    }
}
