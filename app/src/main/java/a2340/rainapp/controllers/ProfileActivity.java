package a2340.rainapp.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import a2340.rainapp.R;
import model.User;

/**
 * Created by austinletson on 2/21/17.
 */

public class ProfileActivity extends AppCompatActivity {
    EditText emailEdit;
    EditText addressEdit;
    EditText titleEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        emailEdit = (EditText) findViewById(R.id.profile_emailEdit);
        addressEdit = (EditText) findViewById(R.id.profile_addressEdit);
        titleEdit = (EditText) findViewById(R.id.profile_titleEdit);
        emailEdit.setText(User.getCurrentUser().get_email());
        addressEdit.setText(User.getCurrentUser().get_address());
        titleEdit.setText(User.getCurrentUser().get_title());
    }

    /**
     * Called when update is pressed, updates all profile fields
     * @param view
     */
    protected void onUpdatePressed (View view) {
        User.getCurrentUser().set_email(emailEdit.getText().toString());
        User.getCurrentUser().set_address(addressEdit.getText().toString());
        User.getCurrentUser().set_title(titleEdit.getText().toString());
    }
}
