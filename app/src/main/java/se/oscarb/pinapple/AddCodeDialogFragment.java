package se.oscarb.pinapple;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;

public class AddCodeDialogFragment extends DialogFragment implements TextWatcher {

    // Fields
    private AddCodeDialogListener hostActivity;
    private EditText labelText;
    private EditText codeText;
    private TextInputLayout labelTextInputLayout;
    private TextInputLayout codeTextInputLayout;
    private Dialog dialog;

    // Get the host activity and save it to hostActivity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Verify that the host activity implements the interface AddCodeDialogListener
        try {
            hostActivity = (AddCodeDialogListener) activity;
        } catch (ClassCastException exception) {
            throw new ClassCastException(activity.toString()
                    + " does not implement AddCodeDialogListener");
        }


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Inflate view and save references to EditTexts
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_code, null);
        labelText = (EditText) view.findViewById(R.id.label);
        codeText = (EditText) view.findViewById(R.id.code);
        labelTextInputLayout = (TextInputLayout) view.findViewById(R.id.label_input_layout);
        codeTextInputLayout = (TextInputLayout) view.findViewById(R.id.code_input_layout);

        // Let this class listen to both EditTexts for changes
        labelText.addTextChangedListener(this);
        codeText.addTextChangedListener(this);

        // Build the Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle(R.string.add_code);
        builder.setView(view);
        // User clicks "Add Code"
        builder.setPositiveButton(R.string.add_code, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Send code and label to MainActivity, encryption happens there

                // int codeValue = Integer.parseInt(codeText.getText().toString());
                hostActivity.onAddCodeDialogPositiveClick(AddCodeDialogFragment.this,
                        labelText.getText().toString(),
                        codeText.getText().toString());
            }
        });
        // User clicks "Cancel"
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hostActivity.onAddCodeDialogNegativeClick(AddCodeDialogFragment.this);
            }
        });

        dialog = builder.create();

        // Disable add code button
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                checkPositiveButton((AlertDialog) dialog);
            }
        });

        return dialog;
    }

    // Check for valid input in both EditTexts, then enable positive button
    private void checkPositiveButton(AlertDialog dialog) {
        if (labelText.getText().toString().trim().length() > 0 && codeText.length() > 0) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
        } else {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }

    }

    /*
     * Implemented from TextWatcher
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        checkPositiveButton((AlertDialog) dialog);

        // Check for input errors in EditText for label
        if (labelText.hasFocus()) {
            labelTextInputLayout.setError(null);
            if (labelText.getText().toString().trim().length() == 0) {
                labelTextInputLayout.setError(getResources().getString(R.string.label_is_required));
            }
        }

        // Check for input errors in EditText for label
        if (codeText.hasFocus()) {
            codeTextInputLayout.setError(null);
            if (codeText.length() == 0) {
                codeTextInputLayout.setError(getResources().getString(R.string.code_is_required));
            }
        }
    }

    // Interface for passing back data to host
    public interface AddCodeDialogListener {
        void onAddCodeDialogPositiveClick(DialogFragment dialog, String label, String value);

        void onAddCodeDialogNegativeClick(DialogFragment dialog);
    }

}
