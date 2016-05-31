package se.oscarb.pinapple;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        passcodeEditText = (EditText) findViewById(R.id.passcode);
        textInputLayout = (TextInputLayout) findViewById(R.id.input_layout);

        passcodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textInputLayout.setError(null);
                switch(s.length()) {
                    case 0: textInputLayout.setError("No digits entered");
                            break;
                    case 4: showCodes();
                            break;
                    default: textInputLayout.setError("Enter four digits");
                            break;
                }
            }
        });

    }

    public void showCodes(View view) {
        showCodes();


        // TODO: Only run method when EditText is not empty

    }

    public void showCodes() {


        try {
            int passcode = Integer.parseInt(passcodeEditText.getText().toString());


            Intent toMainActivityIntent = new Intent(this, MainActivity.class);
            toMainActivityIntent.putExtra(PASSCODE_MESSAGE, passcode);
            startActivity(toMainActivityIntent);
            passcodeEditText.setText("");
            textInputLayout.setError(null);

        } catch (NumberFormatException exception) {
            Log.w(TAG, "NumberFormatException" + exception.getMessage());
        }


        // TODO: Only run method when EditText is not empty

    }
}
