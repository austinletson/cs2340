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
    private int _latitude;
    private int _longitude;
    private Type _type;
    private Condition _condition;

    public Report(int _latitude, int _longitude, User _user, Type _type, Condition _condition) {
        this._latitude = _latitude;
        this._longitude = _longitude;
        this._user = _user;
        this._type = _type;
        this._condition = _condition;
        _reportDate = Calendar.getInstance().getTime();
        _reportNumber = ReportHandler.getHandler().getNextId();
    }

    @Override
    public String toString() {
        return "" + _type + "/" +_condition +" @(" + _latitude + ", " + _longitude +")";
    }

    public enum Type {
        BOTTLED, WELL, STREAM, LAKE, SPRING, OTHER
    }

    public enum Condition {
        WASTE, TREATABLE_CLEAR, TREATABLE_MUDDY, POTABLE
    }
}
