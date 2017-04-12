package database;



import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * Created by cpettiford on 3/27/17.
 * Version 1.0
 */


public class InputValidation {
    private final Context context;


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
            return true;
        }
        return false;
    }



    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager)  context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}

