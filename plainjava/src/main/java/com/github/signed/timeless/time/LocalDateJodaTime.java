package com.github.signed.timeless.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public class LocalDateJodaTime implements LocalDate {
    private static int toDateTimeConstant(Month month) {
        switch (month) {
            case January:
                return DateTimeConstants.JANUARY;
            case February:
                return DateTimeConstants.FEBRUARY;
            case March:
                return DateTimeConstants.MARCH;
            case April:
                return DateTimeConstants.APRIL;
            case May:
                return DateTimeConstants.MAY;
            case June:
                return DateTimeConstants.JUNE;
            case July:
                return DateTimeConstants.JULY;
            case August:
                return DateTimeConstants.AUGUST;
            case September:
                return DateTimeConstants.SEPTEMBER;
            case October:
                return DateTimeConstants.OCTOBER;
            case November:
                return DateTimeConstants.NOVEMBER;
            case December:
                return DateTimeConstants.DECEMBER;
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    private final org.joda.time.LocalDate day;

    public LocalDateJodaTime(org.joda.time.LocalDate day) {
        this.day = day;
    }

    @Override
    public boolean is(Month month) {
        return day.monthOfYear().get() == toDateTimeConstant(month);
    }

    @Override
    public boolean isDayOfMonth(int dayOfMonth) {
        return day.dayOfMonth().get() == dayOfMonth;
    }
}
