package com.khigio234.pc.foody.commons;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by PC on 8/16/2016.
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    //region Properties

    private int mSpanCount;
    private int mSpacing;
    private boolean mIncludeEdge;

    //endregion

    //region Constructors
    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        mSpanCount = spanCount;
        mSpacing = spacing;
        mIncludeEdge = includeEdge;
    }

    //endregion

    //region Override method

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // item position
        int position = parent.getChildAdapterPosition(view);
        // item column
        int column = position % mSpanCount;

        if (mIncludeEdge){
            outRect.left = mSpacing - column * mSpacing / mSpanCount;
            outRect.right = (column + 1) * mSpacing / mSpanCount;
            if (position < mSpanCount){
                outRect.top = mSpacing;
            }
            outRect.bottom = mSpacing;
        }else {
            outRect.left = column * mSpacing / mSpanCount;
            outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount;
            if (position >= mSpanCount){
                outRect.top = mSpacing;
            }
            outRect.bottom = mSpacing;
        }
    }

    //endregion


}
