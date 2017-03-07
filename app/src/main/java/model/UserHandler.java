package model;

import java.util.ArrayList;

/**
 * Created by austinletson on 3/1/17.
 */

public class UserHandler {
    private User _currentUser;
    private ArrayList<User> _users = new ArrayList<>();
    private static UserHandler handler = new UserHandler();

    /**
     * get user handler
     * @return user handler
     */
    public static UserHandler getHandler() {
        return handler;
    }

    /**
     * get current user
     * @return current user
     */
    public User get_currentUser() {
        return _currentUser;
    }

    /**
     * set current user
     * @param _currentUser
     */
    public void set_currentUser(User _currentUser) {
        this._currentUser = _currentUser;
    }

    /**
     * get users
     * @return ArrayList of users
     */
    public ArrayList<User> get_users() {
        return _users;
    }

    /**
     * add user
     * @param user
     */
    public void addUser(User user) {
        _users.add(user);
    }
}
