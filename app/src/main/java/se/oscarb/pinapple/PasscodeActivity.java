package se.oscarb.pinapple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PasscodeActivity extends AppCompatActivity {

    // Fields
    public final static String PASSCODE_MESSAGE = "se.oscarb.pinapple.PASSCODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
    }

    public void showCodes(View view) {
        EditText passcodeEditText = (EditText) findViewById(R.id.passcode);
        String passcode = passcodeEditText.getText().toString();

        // TODO: Only run method when EditText is not empty

        Intent toMainActivityIntent = new Intent(this, MainActivity.class);
        toMainActivityIntent.putExtra(PASSCODE_MESSAGE, passcode);
        startActivity(toMainActivityIntent);

    }
}
