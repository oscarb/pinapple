package se.oscarb.pinapple;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class AddCodeDialogFragment extends DialogFragment {

    // Fields
    private AddCodeDialogListener hostActivity;
    private EditText labelText;
    private EditText codeText;

    // Interface for passing back data to host
    public interface AddCodeDialogListener {
        public void onAddCodeDialogPositiveClick(DialogFragment dialog, Code code);
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

        // Build the Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_code);
        builder.setView(view);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Code code = new Code(labelText.getText().toString(), codeText.getText().toString());
                    hostActivity.onAddCodeDialogPositiveClick(AddCodeDialogFragment.this, code);
                }
            });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hostActivity.onAddCodeDialogNegativeClick(AddCodeDialogFragment.this);
                }
            });
        return builder.create();
    }


}
