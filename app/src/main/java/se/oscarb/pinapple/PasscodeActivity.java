package se.oscarb.pinapple;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class PasscodeActivity extends AppCompatActivity {

    // Fields
    public final static String PASSCODE_MESSAGE = "se.oscarb.pinapple.PASSCODE";
    private static final String TAG = PasscodeActivity.class.getSimpleName();
    private EditText passcodeEditText;
    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        Log.d(TAG, "In the onCreate() event");

        // Initialize fields
        passcodeEditText = (EditText) findViewById(R.id.passcode);
        //textInputLayout = (TextInputLayout) findViewById(R.id.input_layout);

        //passcodeEditText.setShowSoftInputOnFocus(true);

        passcodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Show codes once 4 digits are entered
                if (s.length() == 4) {
                    showCodes();
                }
            }
        });

    }


    // Start MainActivity
    public void showCodes(View view) {
        showCodes();

    }

    private void showCodes() {

        // Verify input is valid int
        try {
            int passcode = Integer.parseInt(passcodeEditText.getText().toString());

            Intent toMainActivityIntent = new Intent(this, MainActivity.class);
            //toMainActivityIntent.setFlags(toMainActivityIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            toMainActivityIntent.putExtra(PASSCODE_MESSAGE, passcode);
            startActivity(toMainActivityIntent);
            passcodeEditText.setText("");
            //textInputLayout.setError(null);
            //finish();

        } catch (NumberFormatException exception) {
            Log.w(TAG, "NumberFormatException" + exception.getMessage());
        }

    }
}
