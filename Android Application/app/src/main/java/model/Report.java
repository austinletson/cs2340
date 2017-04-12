package model;

import java.util.Calendar;

/**
 * Created by austinletson on 2/28/17.
 * Version 1.0
 */

public class Report {

    private String _reportDate;
    private String _username;
    private double _latitude;
    private double _longitude;
    private String _type;
    private String _condition;


    @Override
    public String toString() {
        return "" + _type + "/" +_condition +" @(" + _latitude + ", " + _longitude +")";
    }

    /**
     * gets latitude of the source report
     * @return the latitude of the source report
     */
    public double get_latitude() {
        return _latitude;
    }


    /**
     * sets the latitude of the source report
     * @param _latitude latitude
     */
    public void set_latitude(double _latitude) {
        this._latitude = _latitude;
    }

    /**
     * gets the longitude of the source report
     * @return the longitude of the source report
     */
    public double get_longitude() {
        return _longitude;
    }

    /**
     * sets the longitude for the source report
     * @param _longitude longitude
     */
    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }

    /**
     * gets the date of the submitted source report
     * @return the date of the submitted source report
     */
    public String get_reportDate() {
        return _reportDate;
    }

    /**
     * sets the date of the source report
     * @param _reportDate report date
     */
    public void set_reportDate(String _reportDate) {
        this._reportDate = Calendar.getInstance().getTime().toString();
    }




    /**
     * gets the type of the source water
     * @return the type of the source water
     */
    public String get_type() {
        return _type;
    }


    /**
     * sets the type of the source water
     * @param _type type
     */
    public void set_type(String _type) {
        this._type = _type;
    }

    /**
     * gets the condition of the source water
     * @return the condition of the source water
     */
    public String get_condition() {
        return _condition;
    }

    /**
     * sets the condition of the source water
     * @param _condition condition
     */
    public void set_condition(String _condition) {
        this._condition = _condition;
    }


    /**
     * sets the username of the user
     * @param _username username
     */
    public void set_username(String _username) {
        this._username = _username;
    }

    /**
     * gets the username of the user
     * @return the username of the user
     */
    public String get_username() {
        return _username;
    }


}
