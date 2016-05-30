package se.oscarb.pinapple;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.ViewHolder> {

    // Fields
    private Context context;
    private List<Code> codeList;


    // Constructor
    public CodeAdapter(Context c, List<Code> codes) {
        context = c;
        codeList = codes;
    }

    // Constructor
    public CodeAdapter(List<Code> codes) {
        codeList = codes;
    }

    @Override
    public int getItemCount() {
        return codeList.size();
    }

    @Override
    public CodeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View codeCardView = inflater.inflate(R.layout.card_code, parent, false);

        // Return a ViewHolder
        ViewHolder viewHolder = new ViewHolder(codeCardView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CodeAdapter.ViewHolder viewHolder, int position) {
        // Get static references from our ViewHolder
        TextView labelText = viewHolder.labelText;
        TextView codeText = viewHolder.codeText;
        TextView contentText = viewHolder.contentText;


        // Populate RecyclerView with each CardView
        if(codeList.size() > 0) {
            Code code = codeList.get(position);

            // Set data
            //labelText.setText(code.getLabel());
            //codeText.setText(code.getValue());

            // title 24sp or 14sp

            // http://stackoverflow.com/questions/16335178/different-size-of-strings-in-the-same-textview

            String text = code.getLabel() + "\n" + code.getValue();
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
