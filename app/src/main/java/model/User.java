package model;

/**
 * Created by austinletson on 2/14/17.
 */

public class User {
    private String _username;
    private String _password;

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

    public User (String username, String password) {
        _username = username;
        _password = password;

    }
}
