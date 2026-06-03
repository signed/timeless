package com.github.signed.timeless.time;

import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeJodaTime implements DateTime {

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
        return new DateTimeJodaTime(dateTime.toDateTime(timeZone));
    }

    @Override
    public String asString(DateTimeFormatter workLogFormatter) {
        return dateTime.toString(workLogFormatter);
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
        return new DateTimeJodaTime(dateTime.withZone(dateTimeZone));
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
}
