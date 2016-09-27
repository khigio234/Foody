package com.khigio234.pc.core.util;

import com.khigio234.pc.core.model.services.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PC on 9/27/2016.
 */
public class QueryDate {
    private static final ThreadLocal<DateFormat> DF = new ThreadLocal<DateFormat>() {
        @Override
        public DateFormat initialValue() {
            return new SimpleDateFormat(Configuration.TIMESTAMP_FORMAT);
        }
    };

    private final Date mDate;

    public QueryDate(Date date) {
        mDate = date;
    }

    @Override
    public String toString() {
        return DF.get().format(mDate);
    }
}
