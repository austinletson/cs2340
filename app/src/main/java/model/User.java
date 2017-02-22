package model;

import java.util.ArrayList;

/**
 * Created by austinletson on 2/14/17.
 */

public class User {
    private String _username;
    private String _password;
    private UserType _type;
    private String _email;
    private String _address;
    private String _title;

    //static information holders
    private static ArrayList<User> _users = new ArrayList<>();
    private static User currentUser;

    public String get_email() {
        return _email;
    }

    public void set_email(String email) {
        this._email = email;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String address) {
        this._address = address;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String title) {
        this._title = title;
    }

    public UserType get_type() {
        return _type;
    }

    public void set_type(UserType _type) {
        this._type = _type;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    public static ArrayList<User> get_users() {
        return _users;
    }

    public static void addUser(User user) {
        _users.add(user);
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_username() {

        return _username;
    }

    public String get_password() {
        return _password;
    }

    public User (String username, String password, UserType type) {
        _username = username;
        _password = password;
        _type = type;

    }

    public enum UserType {
        USER, WORKER, MANAGER, ADMIN;
    }
}
