package a2340.rainapp.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import a2340.rainapp.R;
import model.User;

/**
 * Created by austinletson on 2/14/17.
 */

public class MainApplicationScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_app);
    }

    /**
     * called when logout button is pressed
     * @param view
     */
    protected void logoutPressed(View view) {
        User.setCurrentUser(null);
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    /**
     * called when editProf button is pressed
     * @param view
     */
    protected void editProfPressed(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
