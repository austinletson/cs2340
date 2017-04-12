package model;

import java.util.ArrayList;

/**
 * Created by austinletson on 3/1/17.
 * Version 1.0
 */

public class UserHandler {
    private final ArrayList<User> _users = new ArrayList<>();
    private static final UserHandler handler = new UserHandler();

    /**
     * get user handler
     * @return user handler
     */
    public static UserHandler getHandler() {
        return handler;
    }



}
