package a2340.rainapp.controllers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.annotation.Nullable;
import database.InputValidation;
import model.User;
import model.UserType;
import database.UserDBHandler;


import a2340.rainapp.R;

/**
 * Created by austinletson on 2/14/17.
 * Version 1.0
 */

public class RegisterActivity extends AppCompatActivity {

    private final AppCompatActivity activity = RegisterActivity.this;




    private EditText userNameEditText;
    private EditText passwordEditText;
    private Spinner typeSpinner;
    private TextView errorTextView;
    private User user;

    private InputValidation inputValidation;
    private UserDBHandler userDBHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        initViews();
        initObject();
    }

    private void initViews() {


        userNameEditText = (EditText) findViewById(R.id.register_usernameEdit);
        passwordEditText = (EditText) findViewById(R.id.register_passwordEdit);
        errorTextView = (TextView) findViewById(R.id.register_errorTextView);

        /*
            Initialize Spinner
         */

        String[] arraySpinner = new String[] {
                UserType.USER, UserType.WORKER, UserType.MANAGER, UserType.ADMINISTRATOR
        };

        typeSpinner = (Spinner) findViewById(R.id.register_typeSpinner);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
    }

    private void initObject() {
        inputValidation = new InputValidation(activity);
        userDBHandler = new UserDBHandler(activity);
        user = new User();
    }



    /**
     * Method called when register is pressed
     * @param view view
     */
    public void onRegisterPressed(View view){
        postDataToSQLite();
    }

    private void postDataToSQLite() {
        if (inputValidation.isEditTextFilled(userNameEditText, errorTextView, getString(R.string.error_message_username))) {
            return;
        }
        if (inputValidation.isEditTextFilled(passwordEditText, errorTextView, getString(R.string.error_message_password))) {
            return;
        }

        if (!userDBHandler.checkUser(userNameEditText.getText().toString().trim())) {

            String usernameInput = userNameEditText.getText().toString();
            String passwordInput = passwordEditText.getText().toString();
            //String userType = typeSpinner.getSelectedItem().toString();


            user.set_username(usernameInput);
            user.set_password(passwordInput);
            user.set_type(typeSpinner.getSelectedItem().toString());




            userDBHandler.addUser(user);

            errorTextView.setText(getString(R.string.successful_registration));


        } else {
            errorTextView.setText(getString(R.string.error_username_exists));
        }


    }
}
