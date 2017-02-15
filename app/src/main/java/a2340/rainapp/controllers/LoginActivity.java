package a2340.rainapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import a2340.rainapp.R;
import model.User;

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
        userNameEditText = (EditText) findViewById(R.id.usernameEdit);
        passwordEditText = (EditText) findViewById(R.id.passwordEdit);
        alertTextView = (TextView) findViewById(R.id.alertTextView);
    }

    protected void loginPressed(View view) {
        User dummyUser = new User("user", "pass");
        if (userNameEditText.getText().toString().equals(dummyUser.get_username()) &&
                passwordEditText.getText().toString().equals(dummyUser.get_password())) {

            Intent intent = new Intent(this, MainApplicationScreenActivity.class);
            startActivity(intent);
        } else {
            alertTextView.setText("Wrong username and passowrd");
        }
    }
}

