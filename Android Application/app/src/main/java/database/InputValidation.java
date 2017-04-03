package database;

/**
 * Created by cpettiford on 3/27/17.
 */

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


public class InputValidation {
    private Context context;
    private TextView alertTextView;


    public InputValidation(Context context) {
        this.context = context;
    }


    /**
     * checks whether the input edit text is filled
     * @param editText editText
     * @param alertTextView alertTextView
     * @param message message
     * @return false if not filled, true otherwise
     */
    public boolean isEditTextFilled(EditText editText, TextView alertTextView, String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty()) {
            alertTextView.setText(message);
            hideKeyboardFrom(editText);
        }
        return true;
    }



    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager)  context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}

