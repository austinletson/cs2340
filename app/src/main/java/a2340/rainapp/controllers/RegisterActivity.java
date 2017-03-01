package a2340.rainapp.controllers;


import android.os.Bundle;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import a2340.rainapp.R;
import model.User;
import model.UserHandler;

/**
 * Created by austinletson on 2/14/17.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText nameTextEdit;
    EditText userNameEditText;
    EditText passwordEditText;
    Spinner typeSpinner;
    TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        userNameEditText = (EditText) findViewById(R.id.register_usernameEdit);
        passwordEditText = (EditText) findViewById(R.id.register_passwordEdit);
        nameTextEdit = (EditText) findViewById(R.id.register_nameEdit);
        errorTextView = (TextView) findViewById(R.id.register_errorTextView);

        /*
            Initialize Spinner
         */
        typeSpinner = (Spinner) findViewById(R.id.register_typeSpinner);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.UserType.values());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
    }

    /**
     * Method called when register is pressed
     * @param view
     */
    protected void onRegisterPressed(View view){
        //Grab entered data
        String usernameInput = userNameEditText.getText().toString();
        String passwordInput = passwordEditText.getText().toString();

        //Check if fields were empty
        if (usernameInput.equals("") || passwordInput.equals("")) {
            errorTextView.setText("One or more fields are empty");
            return;
        }
        //check exist for existing users
        for(User u: UserHandler.getHandler().get_users()) {
            if (u.get_username().equals(usernameInput)) {
                errorTextView.setText("That user name is already taken");
                return;
            }
        }

        //create a new user and store them in users

        UserHandler.getHandler().addUser(new User(usernameInput, passwordInput, (User.UserType) typeSpinner.getSelectedItem()));
        errorTextView.setText("Registered");
    }
}
