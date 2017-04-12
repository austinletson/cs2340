package model;


/**
 * Created by austinletson on 2/14/17.
 * Version 1.0
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
     * @param email email
     */
    public void set_email(String email) {
        this._email = email;
    }

    /**
     * gets the address of user
     * @return the users address
     */
    public String get_address() {
        return _address;
    }

    /**
     * sets the address of user
     * @param address address
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
     * @param title title
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
     * @param _type type
     */
    public void set_type(String _type) {
        this._type = _type;
    }

    /**
     * sets the user name of the user
     * @param _username username
     */
    public void set_username(String _username) {
        this._username = _username;
    }

    /**
     * sets the password of the user
     * @param _password password
     */
    public void set_password(String _password) {
        this._password = _password;
    }

    /**
     * gets username of user
     * @return user name of user
     */
    public String get_username() {

        return _username;
    }

    public void set_name(String name) {
        this._name = name;
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

    /**
     * sets the id number for user
     * @param _id id
     */
    public void set_id(long _id) {
        this._id = _id;
    }

    /**
     * returns the id number of user
     * @return _id
     */
    public long get_id() {
        return _id;
    }


    public User() {

    }


}
