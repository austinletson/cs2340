package a2340.rainapp.controllers;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import a2340.rainapp.R;
import model.User;
import model.UserHandler;
import model.UserType;

import a2340.rainapp.controllers.RegisterActivity;

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
    public void logoutPressed(View view) {
        UserHandler.getHandler().set_currentUser(null);
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    /**
     * called when editProf button is pressed
     * @param view
     */
    public void editProfPressed(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /**
     * called when viewReport button is pressed
     * @param view
     */
    public void viewReportsPressed(View view) {
        Intent intent = new Intent(this, ViewReportsActivity.class);
        startActivity(intent);
    }

    /**
     * called when submit Report button is pressed
     * @param view
     */
    public void submitReportPressed(View view) {
        System.out.println("here");
        Intent intent = new Intent(this, SubmitReportActivity.class);
        startActivity(intent);
    }

    /**
     * called when water availability button is pressed
     * @param view
     */
    public void waterAvailPressed(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /**
     * called when viewPurityReport button is pressed
     * @param view
     */
    public void viewPurityReportsPressed(View view) {
        if (RegisterActivity.loggedInUserType == UserType.WORKER ||
                RegisterActivity.loggedInUserType == UserType.MANAGER){
            Intent intent = new Intent(this, ViewWaterPurityReportsActivity.class);
            startActivity(intent);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(MainApplicationScreenActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("You do not have permission to view purity reports");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

    }

    /**
     * called when submit purity Report button is pressed
     * @param view
     */
    public void submitPurityReportPressed(View view) {
        Intent intent = new Intent(this, SubmitPurityReportActivity.class);
        startActivity(intent);
    }



}