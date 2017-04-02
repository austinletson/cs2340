package a2340.rainapp.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import a2340.rainapp.R;
import database.InputValidation;
import model.User;
import database.UserDBHandler;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;


/**
 * Created by austinletson on 2/21/17.
 */

public class ProfileActivity extends AppCompatActivity {
    private EditText emailEdit;
    private EditText addressEdit;
    private EditText titleEdit;
    private TextView alertTextView;



    private InputValidation inputValidation;
    private UserDBHandler userDBHandler;

    private static final String SELECT_SQL = "SELECT email, address, title FROM users WHERE username = " + LoginActivity.loggedInUser;

    private User user;
    private SQLiteDatabase db;

    private final AppCompatActivity activity = ProfileActivity.this;

    private String tableName = userDBHandler.TABLE_USERS;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        initViews();
        initObjects();


    }

    @Override
    public void onResume() {
        super.onResume();
        if (LoginActivity.loggedInUserEmail != null && LoginActivity.loggedInUserEmail.length() > 0 ) {
            emailEdit.setHint(LoginActivity.loggedInUserEmail);

        }

        if (LoginActivity.loggedInUserAddress != null && LoginActivity.loggedInUserAddress.length() > 0) {
            addressEdit.setHint(LoginActivity.loggedInUserAddress);

        }

        if (LoginActivity.loggedInUserTitle != null && LoginActivity.loggedInUserTitle.length() > 0) {
            titleEdit.setHint(LoginActivity.loggedInUserTitle);

        }
    }

    private void initViews() {
        emailEdit = (EditText) findViewById(R.id.profile_emailEdit);
        addressEdit = (EditText) findViewById(R.id.profile_addressEdit);
        titleEdit = (EditText) findViewById(R.id.profile_titleEdit);
        alertTextView = (TextView) findViewById(R.id.profileAlertView);


        if (LoginActivity.loggedInUserEmail != null && LoginActivity.loggedInUserEmail.length() > 0 ) {
            emailEdit.setHint(LoginActivity.loggedInUserEmail);

        }

        if (LoginActivity.loggedInUserAddress != null && LoginActivity.loggedInUserAddress.length() > 0) {
            addressEdit.setHint(LoginActivity.loggedInUserAddress);

        }

        if (LoginActivity.loggedInUserTitle != null && LoginActivity.loggedInUserTitle.length() > 0) {
            titleEdit.setHint(LoginActivity.loggedInUserTitle);

        }

    }

    private void initObjects() {
        userDBHandler = new UserDBHandler(activity);
        inputValidation = new InputValidation(activity);
        user = new User();

    }

    /**
     * Called when update is pressed, updates all profile fields
     * @param view
     */
    public void onUpdatePressed (View view) {
        postDataToSQLite();
        userDBHandler.getAllUsers();

    }

    private void postDataToSQLite() {
        if (!inputValidation.isEditTextFilled(addressEdit, alertTextView, getString(R.string.error_message_address))) {
            return;
        }
        if (!inputValidation.isEditTextFilled(emailEdit, alertTextView, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isEditTextFilled(titleEdit, alertTextView, getString(R.string.error_message_title))) {
            return;
        } else {
            alertTextView.setText("Your information has been saved.");
        }



        String addressInput = addressEdit.getText().toString();
        String emailInput = emailEdit.getText().toString();
        String titleInput = titleEdit.getText().toString();

        boolean updateSuccessful = userDBHandler.updateUser(LoginActivity.loggedInUser, emailInput, addressInput, titleInput);

        if(updateSuccessful) {
            LoginActivity.loggedInUserEmail = emailInput;
            LoginActivity.loggedInUserAddress = addressInput;
            LoginActivity.loggedInUserTitle = titleInput;
        }


    }


}
