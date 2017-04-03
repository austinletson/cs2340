package a2340.rainapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import a2340.rainapp.R;
import database.InputValidation;
import database.UserDBHandler;


/**
 * Created by austinletson on 2/13/17.
 */

public class LoginActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LoginActivity.this;
    EditText userNameEditText;
    EditText passwordEditText;
    TextView alertTextView;

    static String loggedInUser = "";
    static String loggedInUserEmail = "";
    static String loggedInUserAddress = "";
    static String loggedInUserTitle = "";
    static String loggedInUserType = "";

    private InputValidation inputValidation;
    private UserDBHandler userDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        getSupportActionBar().hide();

        initView();
        initObjects();
    }

    private void initView() {
        userNameEditText = (EditText) findViewById(R.id.login_usernameEdit);
        passwordEditText = (EditText) findViewById(R.id.login_passwordEdit);
        alertTextView = (TextView) findViewById(R.id.login_alertTextView);
    }

    private void initObjects() {
        userDBHandler = new UserDBHandler(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * called when login pressed
     * @param view
     */
    public void loginPressed(View view) {
        verifyFromSQLite();


    }

    private void verifyFromSQLite() {
        if (!inputValidation.isEditTextFilled(userNameEditText, alertTextView, getString(R.string.error_message_username))) {
            return;
        }
        if (!inputValidation.isEditTextFilled(passwordEditText, alertTextView, getString(R.string.error_message_password))) {
            return;
        }
        if (userDBHandler.checkUser(userNameEditText.getText().toString().trim()
                , passwordEditText.getText().toString().trim())) {

            loggedInUser = userNameEditText.getText().toString();




            loggedInUserEmail = userDBHandler.getUserEmail(LoginActivity.loggedInUser);
            loggedInUserAddress = userDBHandler.getUserAddress(LoginActivity.loggedInUser);
            loggedInUserTitle = userDBHandler.getUserTitle(LoginActivity.loggedInUser);

            loggedInUserType = userDBHandler.getUserType(LoginActivity.loggedInUser);



            Intent intent = new Intent(this, MainApplicationScreenActivity.class);

            startActivity(intent);

        } else {
            alertTextView.setText(R.string.error_valid_username_password);
        }
    }



}