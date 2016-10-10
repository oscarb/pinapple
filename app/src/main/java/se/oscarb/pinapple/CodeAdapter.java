package se.oscarb.pinapple;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.ViewHolder> {

    // Fields
    private Context context;
    private List<Code> codeList;
    private int passcode;


    // Constructor
    public CodeAdapter(Context c, List<Code> codes, int passcode) {
        context = c;
        codeList = codes;
        this.passcode = passcode;
    }

    // Constructor
    public CodeAdapter(List<Code> codeList, int passcode) {
        this.codeList = codeList;
        this.passcode = passcode;
    }

    /* From RecyclerView.Adapter */
    @Override
    public int getItemCount() {
        return codeList.size();
    }


    @Override
    public CodeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout, a Card
        View codeCardView = inflater.inflate(R.layout.card_code, parent, false);

        // Return a ViewHolder
        return new ViewHolder(codeCardView);
    }

    // Populate cards
    @Override
    public void onBindViewHolder(CodeAdapter.ViewHolder viewHolder, int position) {
        // Get static references from our ViewHolder
        TextView contentText = viewHolder.contentText;

        // Populate RecyclerView with each CardView
        if (codeList.size() > 0) {
            Code code = codeList.get(position);

            // Set data with only one EditText
            // http://stackoverflow.com/questions/16335178/different-size-of-strings-in-the-same-textview

            // Decrypt code and pad to 4 digits
            Crypto crypto = new XorCrypto();

            long decryptedCode = Math.abs(crypto.decrypt(code.getEncryptedValue(), passcode));

            String codeString;
            if (code.getPattern() == null) {
                codeString = String.format(Locale.getDefault(), "%04d", decryptedCode);
            } else {
                // Create string using pattern and decrypted code with zero-padding
                codeString = code.getPattern();

                String decryptedCodeText = String.valueOf(decryptedCode);
                for (int i = decryptedCodeText.length() - 1; codeString.contains("d"); i--) {
                    int j = codeString.lastIndexOf('d');
                    char x = (i >= 0) ? decryptedCodeText.charAt(i) : '0';
                    codeString = codeString.substring(0, j) + x + codeString.substring(j + 1);
                }
            }


            //String cardString = String.format(Locale.getDefault(), "%04d", decryptedCode);
            String text = code.getLabel() + "\n" + codeString;
            SpannableString spannableString = new SpannableString(text);
            spannableString.setSpan(new RelativeSizeSpan(2), code.getLabel().length() + 1, text.length(), 0);
            contentText.setText(spannableString);
        }
    }

    // Cache the views using the ViewHolder pattern
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Fields
        public TextView labelText;
        public TextView codeText;
        public TextView contentText;


        // Constructor taking each view and saving them to the static fields
        public ViewHolder(View itemView) {
            super(itemView);

            labelText = (TextView) itemView.findViewById(R.id.label);
            codeText = (TextView) itemView.findViewById(R.id.code);
            contentText = (TextView) itemView.findViewById(R.id.content);

        }
    }
}
