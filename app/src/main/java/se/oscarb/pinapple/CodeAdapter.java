package se.oscarb.pinapple;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CodeAdapter extends BaseAdapter {

    // Fields
    private Context context;
    private List<Code> codeList;


    // Constructor
    public CodeAdapter(Context c, List<Code> codes) {
        context = c;
        codeList = codes;
    }

    @Override
    public int getCount() {
        return codeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if(convertView == null) {
            // View is not recycled, initialize new view
            textView = new TextView(context);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(codeList.get(position).getLabel());

        return textView;
    }
}
