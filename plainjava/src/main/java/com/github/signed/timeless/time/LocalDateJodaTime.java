package com.github.signed.timeless.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;

public class LocalDateJodaTime implements LocalDate {
    private static int toDateTimeConstant(Month month) {
        return switch (month) {
            case January -> DateTimeConstants.JANUARY;
            case February -> DateTimeConstants.FEBRUARY;
            case March -> DateTimeConstants.MARCH;
            case April -> DateTimeConstants.APRIL;
            case May -> DateTimeConstants.MAY;
            case June -> DateTimeConstants.JUNE;
            case July -> DateTimeConstants.JULY;
            case August -> DateTimeConstants.AUGUST;
            case September -> DateTimeConstants.SEPTEMBER;
            case October -> DateTimeConstants.OCTOBER;
            case November -> DateTimeConstants.NOVEMBER;
            case December -> DateTimeConstants.DECEMBER;
        };
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

    @Override
    public LocalDate plusDays(int days) {
        return new LocalDateJodaTime(day.plusDays(days));
    }

    @Override
    public DateTime toDateTimeAtStartOfDay(DateTimeZone zone) {
        return day.toDateTimeAtStartOfDay(zone);
    }

}
