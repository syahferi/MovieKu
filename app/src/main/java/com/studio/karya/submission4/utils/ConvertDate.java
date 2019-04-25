package com.studio.karya.submission4.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ConvertDate {

    public ConvertDate() {
    }

    public String date(String dateRelease) {

        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd MMM yyyy";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.getDefault());
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());

        Date date;
        String str = null;

        try {
            if (dateRelease != null) {
                date = inputFormat.parse(dateRelease);
                str = outputFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return str;
    }
}
