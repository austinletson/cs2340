package a2340.rainapp.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import a2340.rainapp.R;

/**
 * Created by austinletson on 2/14/17.
 */

public class MainApplicationScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_app);
    }

    protected void logoutPressed(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
