package com.github.signed.timeless.time.joda;

import com.github.signed.timeless.time.DateTime;
import com.github.signed.timeless.time.DateTimeZone;
import com.github.signed.timeless.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class DateTimeJodaTime implements DateTime {
    private static final DateTimeFormatter workLogFormatter = new DateTimeFormatterBuilder().appendHourOfDay(2).appendLiteral(":").appendMinuteOfHour(2).toFormatter();

    static org.joda.time.DateTime jodaDateTimeIn(DateTime dateTime) {
        if( dateTime instanceof DateTimeJodaTime joda ){
            return joda.dateTime;
        }
        throw new IllegalArgumentException();
    }

    public final org.joda.time.DateTime dateTime;

    public DateTimeJodaTime(org.joda.time.DateTime dateTime) {
        this.dateTime = dateTime;
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
        return dateTime.isBefore(dateTimeIn(other));
    }

    @Override
    public int compareTo(DateTime other) {
        return dateTime.compareTo(dateTimeIn(other));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DateTimeJodaTime otherDateTime) {
            return dateTime.equals(otherDateTime.dateTime);
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

    public org.joda.time.DateTime dateTimeIn(Object other) {
        if (other instanceof DateTimeJodaTime joda) {
            return joda.dateTime;
        }
        throw new IllegalArgumentException();
    }

}
