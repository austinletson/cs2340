package database;

import a2340.rainapp.controllers.LoginActivity;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.content.ContentValues;
import android.database.Cursor;


import java.util.ArrayList;
import java.util.List;

import model.PurityReport;
import model.Report;
import model.User;

/**
 * Created by cpettiford on 3/27/17.
 */

public class UserDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rain_app.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "uID";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USERTYPE = "user_type";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_NAME = "name";

    public static final String TABLE_PURITY_REPORTS = "purity_reports";
    public static final String COLUMN_NO_PR = "report_number";
    public static final String COLUMN_PR_DATE = "date";
    public static final String COLUMN_USERNAME_PR = "username";
    public static final String COLUMN_LATITUDE_PR = "latitude";
    public static final String COLUMN_LONGITUDE_PR = "longitude";
    public static final String COLUMN_CONDITION_PR = "condition";
    public static final String COLUMN_VIRUSPPM = "virus_ppm";
    public static final String COLUMN_CONTAMINANTPPM = "contaminant_ppm";

    public static final String TABLE_SOURCE_REPORTS = "source_reports";
    public static final String COLUMN_NO_SR = "report_number";
    public static final String COLUMN_SR_DATE = "date";
    public static final String COLUMN_USERNAME_SR = "username";
    public static final String COLUMN_LATITUDE_SR = "latitude";
    public static final String COLUMN_LONGITUDE_SR = "longitude";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_CONDITION_SR = "condition";




    private static final String TABLE_CREATE_USER = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT," +
            COLUMN_PASSWORD + " TEXT," +
            COLUMN_USERTYPE + " TEXT," +
            COLUMN_NAME + " TEXT," +
            COLUMN_EMAIL + " TEXT," +
            COLUMN_ADDRESS + " TEXT," +
            COLUMN_TITLE + " TEXT" +
            ")";


    private static final String TABLE_CREATE_PURITY_REPORTS = "CREATE TABLE " + TABLE_PURITY_REPORTS + " (" +
            COLUMN_NO_PR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME_PR + " TEXT," +
            COLUMN_PR_DATE + " TEXT," +
            COLUMN_LATITUDE_PR + " TEXT," +
            COLUMN_LONGITUDE_PR + " TEXT," +
            COLUMN_CONDITION_PR + " TEXT," +
            COLUMN_VIRUSPPM + " TEXT," +
            COLUMN_CONTAMINANTPPM + " TEXT" +
            ")";


    private static final String TABLE_CREATE_SOURCE_REPORTS = "CREATE TABLE " + TABLE_SOURCE_REPORTS + " (" +
            COLUMN_NO_SR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME_SR + " TEXT," +
            COLUMN_SR_DATE + " TEXT," +
            COLUMN_LATITUDE_SR + " TEXT," +
            COLUMN_LONGITUDE_SR + " TEXT," +
            COLUMN_TYPE + " TEXT," +
            COLUMN_CONDITION_SR + " TEXT" +
            ")";

    private String DROP_TABLE_USERS = "DROP TABLE IF EXISTS " + TABLE_USERS;
    private String DROP_TABLE_PURITY_REPORTS = "DROP TABLE IF EXISTS " + TABLE_PURITY_REPORTS;
    private String DROP_TABLE_SOURCE_REPORTS = "DROP TABLE IF EXISTS " + TABLE_SOURCE_REPORTS;

    public UserDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USER);
        db.execSQL(TABLE_CREATE_PURITY_REPORTS);
        db.execSQL(TABLE_CREATE_SOURCE_REPORTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USERS);
        db.execSQL(DROP_TABLE_PURITY_REPORTS);
        db.execSQL(DROP_TABLE_SOURCE_REPORTS);

        onCreate(db);
    }

    /**
     * adds a new user to the database
     * @param user user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.get_username());
        values.put(COLUMN_PASSWORD, user.get_password());
        values.put(COLUMN_USERTYPE, user.get_type());
        values.put(COLUMN_NAME, user.get_name());
        values.put(COLUMN_EMAIL, user.get_email());
        values.put(COLUMN_ADDRESS, user.get_address());
        values.put(COLUMN_TITLE, user.get_title());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    /**
     * gets a list of all users stored in the database
     * @return a list of all users stored in database
     */
    public List<User> getAllUsers() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ID,
                COLUMN_USERNAME,
                COLUMN_PASSWORD,
                COLUMN_USERTYPE,
                COLUMN_NAME,
                COLUMN_EMAIL,
                COLUMN_ADDRESS,
                COLUMN_TITLE
        };
        // sorting orders
        String sortOrder =
                COLUMN_USERNAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                user.set_username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.set_password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                user.set_type(cursor.getString(cursor.getColumnIndex(COLUMN_USERTYPE)));
                user.set_name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                user.set_address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                user.set_email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.set_title(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));

                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }


    /**
     * gets the email of the current user
     * @param username
     * @return the email of the current user
     */
    public String getUserEmail(String username) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = '" + username + "'",null);

        if (cursor.moveToFirst()) {
            do {

                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));

                return cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        //This should never happen
        return "";
    }

    /**
     * gets the current user's address
     * @param username
     * @return the address of the current user
     */
    public String getUserAddress(String username) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = '" + username + "'",null);

        if (cursor.moveToFirst()) {
            do {

                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));

                return cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        //This should never happen
        return "";
    }


    /**
     * gets the current user's title
     * @param username
     * @return the title of the current user
     */
    public String getUserTitle(String username) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = '" + username + "'",null);

        if (cursor.moveToFirst()) {
            do {

                String address = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));

                return cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        //This should never happen
        return "";
    }

    /**
     * gets the current user's type
     * @param username
     * @return the type of the current user
     */
    public String getUserType(String username) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = '" + username + "'",null);

        if (cursor.moveToFirst()) {
            do {

                String type = cursor.getString(cursor.getColumnIndex(COLUMN_USERTYPE));

                return cursor.getString(cursor.getColumnIndex(COLUMN_USERTYPE));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        //This should never happen
        return "";
    }

    /**
     * updates the user's information
     * @param username
     * @param email
     * @param address
     * @param title
     * @return true if user's information is updated, false otherwise
     */
    public boolean updateUser(String username, String email, String address, String title) {

        SQLiteDatabase db = this.getWritableDatabase();

        String updateUserInfo = "UPDATE users SET email = '" + email + "'," +
                "address = '" + address + "'," +
                "title = '" + title + "' WHERE username = '"
                + username + "'";

        try {


            db.execSQL(updateUserInfo);

            return true;
        }
        catch(SQLiteException e) {
            e.printStackTrace();

        }

        db.close();
        return false;
    }

    /**
     * deletes user from the database
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USERS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.get_id())});
        db.close();
    }


    /**
     * checks whether the username is taken
     * @param username
     * @return true if username is taken, false otherwise
     */
    public boolean checkUser(String username) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USERNAME + " = ?";

        // selection argument
        String[] selectionArgs = {username};


        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    /**
     * checks whether the user is stored in the database
     * @param username
     * @param password
     * @return true if user already exists, false otherwise
     */
    public boolean checkUser(String username, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {username, password};


        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    /**
     * adds a purity report to the database
     * @param report
     */
    public void addPurityReport(PurityReport report) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME_PR, report.get_username());
        values.put(COLUMN_PR_DATE, report.get_reportDate());
        values.put(COLUMN_LATITUDE_PR, report.get_latitude());
        values.put(COLUMN_LONGITUDE_PR, report.get_longitude());
        values.put(COLUMN_CONDITION_PR, report.get_condition());
        values.put(COLUMN_VIRUSPPM, report.get_virusPPM());
        values.put(COLUMN_CONTAMINANTPPM, report.get_contaminantPPM());


        // Inserting Row
        db.insert(TABLE_PURITY_REPORTS, null, values);
        db.close();
    }

    /**
     * adds dummy data for purity reports
     * @param username username
     * @param date date
     * @param latitude latitude
     * @param longitude longitude
     * @param virusPPM virusPPM
     * @param contaminantPPM contaminantPPM
     * @param condition condition
     */
    public void dummyPurityReport(String username, String date,
                                              double latitude, double longitude,
                                              double virusPPM, double contaminantPPM,
                                              String condition) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME_PR, username);
        values.put(COLUMN_PR_DATE, date);
        values.put(COLUMN_LATITUDE_PR, latitude);
        values.put(COLUMN_LONGITUDE_PR, longitude);
        values.put(COLUMN_CONDITION_PR, condition);
        values.put(COLUMN_VIRUSPPM, virusPPM);
        values.put(COLUMN_CONTAMINANTPPM, contaminantPPM);


        // Inserting Row
        db.insert(TABLE_PURITY_REPORTS, null, values);
        db.close();
    }

    /**
     * gets a list of all purity reports
     * @return a list of all purity reports
     */
    public List<PurityReport> getAllPurityReports() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_NO_PR,
                COLUMN_USERNAME_PR,
                COLUMN_PR_DATE,
                COLUMN_LATITUDE_PR,
                COLUMN_LONGITUDE_PR,
                COLUMN_CONDITION_PR,
                COLUMN_VIRUSPPM,
                COLUMN_CONTAMINANTPPM
        };
        // sorting orders
        String sortOrder =
                COLUMN_USERNAME_PR + " ASC";
        List<PurityReport> purityReportList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the purity report table

        Cursor cursor = db.query(TABLE_PURITY_REPORTS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PurityReport purityReport = new PurityReport();
                purityReport.set_username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME_PR)));
                purityReport.set_reportDate(cursor.getString(cursor.getColumnIndex(COLUMN_PR_DATE)));
                purityReport.set_latitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE_PR))));
                purityReport.set_longitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE_PR))));
                purityReport.set_condition(cursor.getString(cursor.getColumnIndex(COLUMN_CONDITION_PR)));
                purityReport.set_virusPPM(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_VIRUSPPM))));
                purityReport.set_contaminantPPM(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_CONTAMINANTPPM))));

                purityReportList.add(purityReport);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return purity report list
        return purityReportList;
    }


    /**
     * adds a source report to the database
     * @param report
     */
    public void addSourceReport(Report report) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME_SR, report.get_username());
        values.put(COLUMN_SR_DATE, report.get_reportDate());
        values.put(COLUMN_LATITUDE_SR, report.get_latitude());
        values.put(COLUMN_LONGITUDE_SR, report.get_longitude());
        values.put(COLUMN_CONDITION_SR, report.get_condition());
        values.put(COLUMN_TYPE, report.get_type());


        // Inserting Row
        db.insert(TABLE_SOURCE_REPORTS, null, values);
        db.close();
    }

    /**
     * adds dummy data for source report
     * @param report report
     * @param username username
     * @param date date
     * @param latitude latitude
     * @param longitude longitude
     * @param condition condition
     * @param type type
     */
    public void dummySourceReport(String username, String date, double latitude,
                                  double longitude, String condition, String type) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME_SR, username);
        values.put(COLUMN_SR_DATE, date);
        values.put(COLUMN_LATITUDE_SR, latitude);
        values.put(COLUMN_LONGITUDE_SR, longitude);
        values.put(COLUMN_CONDITION_SR, condition);
        values.put(COLUMN_TYPE, type);


        // Inserting Row
        db.insert(TABLE_SOURCE_REPORTS, null, values);
        db.close();

    }

    /**
     * gets a list of all source reports
     * @return a list of all source reports
     */
    public List<Report> getAllSourceReports() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USERNAME_SR,
                COLUMN_SR_DATE,
                COLUMN_LATITUDE_SR,
                COLUMN_LONGITUDE_SR,
                COLUMN_CONDITION_PR,
                COLUMN_TYPE,
        };
        // sorting orders
        String sortOrder =
                COLUMN_USERNAME_PR + " ASC";
        List<Report> SourceReportList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_SOURCE_REPORTS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Report sourceReport = new Report();sourceReport.set_username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME_PR)));
                sourceReport.set_reportDate(cursor.getString(cursor.getColumnIndex(COLUMN_PR_DATE)));
                sourceReport.set_latitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE_PR))));
                sourceReport.set_longitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE_PR))));
                sourceReport.set_condition(cursor.getString(cursor.getColumnIndex(COLUMN_CONDITION_PR)));
                sourceReport.set_type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));

                SourceReportList.add(sourceReport);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return sourceReport list
        return SourceReportList;
    }

}