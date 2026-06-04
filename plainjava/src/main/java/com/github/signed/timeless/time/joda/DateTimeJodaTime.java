package com.github.signed.timeless.time.joda;

import com.github.signed.timeless.time.DateTime;
import com.github.signed.timeless.time.DateTimeZone;
import com.github.signed.timeless.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class DateTimeJodaTime implements DateTime {
    private static final DateTimeFormatter workLogFormatter = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":").appendMinuteOfHour(2).toFormatter();

    private final org.joda.time.DateTime dateTime;

    public DateTimeJodaTime(org.joda.time.DateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public org.joda.time.DateTime toJoda() {
        return dateTime;
    }

    @Override
    public DateTime toDateTime(DateTimeZone timeZone) {
        var jodaTimeDateTimeZone = ToJodaTime.toJodaTime(timeZone);
        return new DateTimeJodaTime(dateTime.toDateTime(jodaTimeDateTimeZone));
    }

    @Override
    public DateTime plusDays(int days) {
        return new DateTimeJodaTime(dateTime.plusDays(days));
    }

    @Override
    public LocalDate toLocalDate() {
        return new LocalDateJodaTime(dateTime.toLocalDate());
    }

    @Override
    public DateTime withZone(DateTimeZone dateTimeZone) {
        var jodaTimeDateTimeZone = ToJodaTime.toJodaTime(dateTimeZone);
        return new DateTimeJodaTime(dateTime.withZone(jodaTimeDateTimeZone));
    }

    @Override
    public boolean isBefore(DateTime other) {
        return dateTime.isBefore(other.toJoda());
    }

    @Override
    public int compareTo(DateTime other) {
        return dateTime.compareTo(other.toJoda());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DateTime otherDateTime) {
            return dateTime.equals(otherDateTime.toJoda());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    @Override
    public String asWorkLogString() {
        return dateTime.toString(workLogFormatter);
    }

}
