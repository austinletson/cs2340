package model;

import java.util.ArrayList;

/**
 * Created by austinletson on 2/14/17.
 */

public class    User {
    private String _username;
    private String _password;
    private UserType _type;
    private String _email;
    private String _address;
    private String _title;

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
    public UserType get_type() {
        return _type;
    }

    /**
     * sets the type of user
     * @param _type
     */
    public void set_type(UserType _type) {
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

    /**
     * gets password of user
     * @return pass word of user
     */
    public String get_password() {
        return _password;
    }

    /**
     * user constructor
     * @param username
     * @param password
     * @param type
     */
    public User (String username, String password, UserType type) {
        _username = username;
        _password = password;
        _type = type;

    }

    /**
     * enum of user type
     */
    public enum UserType {
        USER, WORKER, MANAGER, ADMIN;
    }
}
