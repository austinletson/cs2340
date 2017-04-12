package a2340.rainapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import a2340.rainapp.R;

/**
 * Created by austinletson on 2/14/17.
 * Version 1.0
 */

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        //Button loginButton = (Button) findViewById(R.id.welcomeButtonLogin);
        //Button registerButton = (Button) findViewById(R.id.welcomeButtonRegister);
    }

    /**
     * called when login button pressed
     * @param view view
     */
    public void onLoginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * called when register button pressed
     * @param view view
     */
    public void onRegisterPressed(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}
