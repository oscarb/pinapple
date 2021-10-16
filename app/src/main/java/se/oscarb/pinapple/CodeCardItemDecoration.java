package se.oscarb.pinapple;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/*
 * Add gutter (margin between cards in the GridLayout)
 * Resource: http://stackoverflow.com/a/28533234/6131896
 */
public class CodeCardItemDecoration extends RecyclerView.ItemDecoration {
    private int margin;

    public CodeCardItemDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = margin;
        outRect.right = margin;
        outRect.top = margin;
        outRect.bottom = margin;

    }


}
