package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by austinletson on 2/28/17.
 */

public class Report {

    private String _reportDate;
    private int _reportNumber;
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
     *
     * @return latitude
     */
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
        return _reportDate;
    }

    public void set_reportDate(String _reportDate) {
        this._reportDate = Calendar.getInstance().getTime().toString();
    }

    public int get_reportNumber() {
        return _reportNumber;
    }

    public void set_reportNumber(int _reportNumber) {
        this._reportNumber = _reportNumber;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }


    public String get_condition() {
        return _condition;
    }

    public void set_condition(String _condition) {
        this._condition = _condition;
    }

    public void set_username(String _username) {
        this._username = _username;
    }


}
