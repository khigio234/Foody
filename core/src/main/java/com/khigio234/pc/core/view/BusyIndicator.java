package com.khigio234.pc.core.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.khigio234.pc.core.R;


/**
 * Created by PC on 8/2/2016.
 */
public class BusyIndicator extends Dialog {

    //region Properties

    private TextView mTextViewTitle;

    //endregion

    //region Constructors

    public BusyIndicator (Context context) {
        super(context);

        initialize();
    }

    protected BusyIndicator(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

        initialize();
    }

    public BusyIndicator(Context context, int themeResId) {
        super(context, themeResId);

        initialize();
    }

    //endregion

    //region Override methods

    @Override
    public void setTitle(CharSequence title) {
        mTextViewTitle.setText(title);
    }

    //endregion

    //region Private methods

    private void initialize() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.busy_indicator);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCancelable(false);

        mTextViewTitle = (TextView) findViewById(R.id.tv_title);
    }

    //endregion
}
