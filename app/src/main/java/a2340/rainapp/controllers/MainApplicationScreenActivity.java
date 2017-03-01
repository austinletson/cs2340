package a2340.rainapp.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import a2340.rainapp.R;
import model.User;
import model.UserHandler;

/**
 * Created by austinletson on 2/14/17.
 */

public class MainApplicationScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main_page);
    }

    /**
     * called when logout button is pressed
     * @param view
     */
    protected void logoutPressed(View view) {
        UserHandler.getHandler().set_currentUser(null);
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

    /**
     * called when viewReport button is pressed
     * @param view
     */
    protected void viewReportsPressed(View view) {
        Intent intent = new Intent(this, ViewReportsActivity.class);
        startActivity(intent);
    }

    /**
     * called when view Report button is pressed
     * @param view
     */
    protected void submitReportPressed(View view) {
        Intent intent = new Intent(this, SubmitReportActivity.class);
        startActivity(intent);
    }
}
