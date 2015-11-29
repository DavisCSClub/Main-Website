package org.dcsc.utilities.datetime;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by tktong on 8/8/2015.
 */
public class DcscDateTimeConverter {
    private static final String REGEX_TIME_FORMAT = "([0-9]{2}):([0-9]{2}):([0-9]{2})";

    public static Time stringToSqlTime(String time) {
        if(!time.matches(REGEX_TIME_FORMAT)) {
            time += ":00";
        }

        return Time.valueOf(time);
    }

    public static Date stringToSqlDate(String date) {
        return Date.valueOf(date);
    }
}
