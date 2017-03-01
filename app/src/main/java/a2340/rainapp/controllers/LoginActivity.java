package a2340.rainapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import a2340.rainapp.R;
import model.User;
import model.UserHandler;

/**
 * Created by austinletson on 2/13/17.
 */

public class LoginActivity extends AppCompatActivity {
    EditText userNameEditText;
    EditText passwordEditText;
    TextView alertTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        userNameEditText = (EditText) findViewById(R.id.register_usernameEdit);
        passwordEditText = (EditText) findViewById(R.id.register_passwordEdit);
        alertTextView = (TextView) findViewById(R.id.alertTextView);
    }

    /**
     * called when login pressed
     * @param view
     */
    protected void loginPressed(View view) {
        //grab username and password input
        String inputUserName = userNameEditText.getText().toString();
        String inputPassword = passwordEditText.getText().toString();

        //check for existing users
        for (User user: UserHandler.getHandler().get_users()) {
            if (user.get_username().equals(inputUserName) && user.get_password().equals(inputPassword)) {
                UserHandler.getHandler().set_currentUser(user);
                Intent intent = new Intent(this, MainApplicationScreenActivity.class);
                startActivity(intent);
                return;
            }
        }

        alertTextView.setText("Wrong username and passowrd");

    }
}

