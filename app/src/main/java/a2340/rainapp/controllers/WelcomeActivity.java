package a2340.rainapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;

import a2340.rainapp.R;

/**
 * Created by austinletson on 2/14/17.
 */

public class WelcomeActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        loginButton = (Button) findViewById(R.id.welcomeButtonLogin);
        registerButton = (Button) findViewById(R.id.welcomeButtonRegister);
    }

    protected void onLoginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    protected void onRegisterPressed(View view) {
        //impliment in M5
    }


}
