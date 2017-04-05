package model;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public PurityReport(double _latitude, double _longitude, String _username, String _condition, double virusPPM, double contaminantPPM) {
        this._latitude = _latitude;
        this._longitude = _longitude;
        this._username = _username;
        this._condition = _condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;

    }

    public PurityReport() {

    }


    /**
     * gets the latitude
     * @return  the latitude
     */
    public double get_latitude() {
        return _latitude;
    }


    /**
     * sets the latitude
     * @param _latitude latitude
     */
    public void set_latitude(double _latitude) {
        this._latitude = _latitude;
    }

    /**
     * gets the longitude
     * @return the longitude
     */
    public double get_longitude() {
        return _longitude;
    }


    /**
     * sets the longitude
     * @param _longitude longitude
     */
    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }


    /**
     * gets the date of the submitted purity report
     * @return the date of the submitted purity report
     */
    public String get_reportDate() {
        return df.format(_reportDate);
    }

    public Date get_reportDateAsDate() {return _reportDate;}


    /**
     * sets the date of the purity report
     * @param _reportDate date of report submission
     */
    public void set_reportDate(String _reportDate) {
        try {
            this._reportDate = df.parse(_reportDate);
        } catch (ParseException e) {
            Log.d("Debug", "Unable to parse date");
        }
    }

    /**
     * gets the virusPPM of purity report
     * @return the virusPPM
     */
    public double get_virusPPM() {
        return virusPPM;
    }


    /**
     * sets the virusPPM of the purity report
     * @param virusPPM virusPPM
     */
    public void set_virusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }


    /**
     * gets the contaminantPPM of the purity report
     * @return the contaminantPPM
     */
    public double get_contaminantPPM() {
        return contaminantPPM;
    }


    /**
     * sets the contaminantPPM
     * @param contaminantPPM contaminantPPM
     */
    public void set_contaminantPPM(double contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }


    /**
     * gets the username of
     * @return the current user's username
     */
    public String get_username() {
        return _username;
    }


    /**
     * sets the user's username
     * @param _username username
     */
    public void set_username(String _username) {
        this._username = _username;
    }

    /**
     * gets the condition of the water
     * @return the condition of the water
     */
    public String get_condition() {
        return _condition;
    }


    /**
     * sets the condition of the water
     * @param _condition condition
     */
    public void set_condition(String _condition) {
        this._condition = _condition;
    }


    /**
     * gets the number of the report
     * @return the number of the report
     */
    public long get_reportNumber() {
        return _reportNumber;
    }


    /**
     * sets the number of the srouce report
     * @param _reportNumber
     */
    public void set_reportNumber(long _reportNumber) {
        this._reportNumber = _reportNumber;
    }


    @Override
    public String toString() {
        return _condition + " @(" + _latitude + ", " + _longitude + ") , virusPPM:" + virusPPM +
                ", contaminatePPM:" + contaminantPPM;
    }
}


