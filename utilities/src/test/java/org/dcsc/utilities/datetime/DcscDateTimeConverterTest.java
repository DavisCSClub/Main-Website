package org.dcsc.utilities.datetime;

import org.junit.Assert;
import org.junit.Test;


public class DcscDateTimeConverterTest {
    private static final String DATE = "2015-01-23";
    private static final String TIME = "12:34:56";

    @Test
    public void stringToSqlTime() {
        Assert.assertEquals(TIME, DcscDateTimeConverter.stringToSqlTime(TIME).toString());
    }

    @Test
    public void stringToSqlDate() {
        Assert.assertEquals(DATE, DcscDateTimeConverter.stringToSqlDate(DATE).toString());
    }
}