package model;

import java.util.ArrayList;
import model.UserType;

/**
 * Created by austinletson on 2/14/17.
 */

public class User {
    private String _username;
    private String _password;
    private String _type;
    private String _email;
    private String _address;
    private String _title;
    private String _name;
    private long _id;

    /**
     * gets the email of the user
     * @return email of user
     */
    public String get_email() {
        return _email;
    }

    /**
     * sets email
     * @param email
     */
    public void set_email(String email) {
        this._email = email;
    }

    /**
     * gets the address of user
     * @return the users adress
     */
    public String get_address() {
        return _address;
    }

    /**
     * sets the adress of user
     * @param address
     */
    public void set_address(String address) {
        this._address = address;
    }

    /**
     * gets the title of the user
     * @return title of the user
     */
    public String get_title() {
        return _title;
    }

    /**
     * sets the title of the user
     * @param title
     */
    public void set_title(String title) {
        this._title = title;
    }

    /**
     * gets the type of the user
     * @return type of user
     */
    public String get_type() {
        return _type;
    }

    /**
     * sets the type of user
     * @param _type
     */
    public void set_type(String _type) {
        this._type = _type;
    }

    /**
     * sets the user name of the suer
     * @param _username
     */
    public void set_username(String _username) {
        this._username = _username;
    }

    /**
     * sets the password of the user
     * @param _password
     */
    public void set_password(String _password) {
        this._password = _password;
    }

    /**
     * gest username of user
     * @return user name of user
     */
    public String get_username() {

        return _username;
    }

    public void set_name(String name) {
        this._name = _name;
    }

    public String get_name() {
        return _name;
    }

    /**
     * gets password of user
     * @return pass word of user
     */
    public String get_password() {
        return _password;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long get_id() {
        return _id;
    }


    public User() {

    }

    /**
     * user constructor
     * @param username
     * @param password
     * @param type
     */
    public User (long id, String username, String password, String type, String email, String address, String title, String name) {
        _id = id;
        _username = username;
        _password = password;
        _type = type;
        _email = email;
        _address = address;
        _title = title;
        _name = name;

    }

}
