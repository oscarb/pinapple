package se.oscarb.pinapple;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCodeDialogFragment extends DialogFragment implements TextWatcher {

    // Fields
    private AddCodeDialogListener hostActivity;
    private EditText labelText;
    private EditText codeText;
    private TextInputLayout labelTextInputLayout;
    private TextInputLayout codeTextInputLayout;
    private Dialog dialog;



    // Interface for passing back data to host
    public interface AddCodeDialogListener {
        public void onAddCodeDialogPositiveClick(DialogFragment dialog, String label, int value);
        public void onAddCodeDialogNegativeClick(DialogFragment dialog);

    }

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

        // Inflate view and save references to textfields
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_code, null);
        labelText = (EditText) view.findViewById(R.id.label);
        codeText = (EditText) view.findViewById(R.id.code);
        labelTextInputLayout = (TextInputLayout) view.findViewById(R.id.label_input_layout);
        codeTextInputLayout = (TextInputLayout) view.findViewById(R.id.code_input_layout);



        labelText.addTextChangedListener(this);
        codeText.addTextChangedListener(this);

       // Toast.makeText(AddCodeDialogFragment.this, labelText.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Text", ""+labelText);


        // Build the Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle(R.string.add_code);
        builder.setView(view);
        builder.setPositiveButton(R.string.add_code, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int codeValue = Integer.parseInt(codeText.getText().toString());
                    hostActivity.onAddCodeDialogPositiveClick(AddCodeDialogFragment.this,
                            labelText.getText().toString(),
                            codeValue);
                }
            });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hostActivity.onAddCodeDialogNegativeClick(AddCodeDialogFragment.this);
                }
            });

        // Disable add button
        dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                checkPositiveButton((AlertDialog)dialog);
            }
        });

        return dialog;
    }

    private void checkPositiveButton(AlertDialog dialog) {
        if(labelText.length() > 0 && codeText.length() > 0) {
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
        if(labelText.hasFocus()) {
            labelTextInputLayout.setError(null);
            if (labelText.length() == 0) {
                labelTextInputLayout.setError("Label is required");
            }
        }

        // Check for input errors in EditText for label
        if(codeText.hasFocus()) {
            codeTextInputLayout.setError(null);
            if (codeText.length() == 0) {
                codeTextInputLayout.setError("Code is required");
            }
        }


    }


}
