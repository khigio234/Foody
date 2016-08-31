package com.khigio234.pc.core.view;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by PC on 8/2/2016.
 */
public class Constants {

    //region Constructor

    private Constants() {

    }

    //endregion

    //region Page

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            MAIN_PAGE, LOGIN_PAGE, REGISTER_PAGE
    })

    public @interface PageKey {}

    public static final int MAIN_PAGE = 0;
    public static final int LOGIN_PAGE = 1;
    public static final int REGISTER_PAGE = 2;

    //endregion
}
