package com.github.signed.timeless.time;

import org.joda.time.format.DateTimeFormatter;

public interface DateTime extends Comparable<DateTime> {
    static DateTime of(int year, Month month, int dayOfMonth, int hour, int minutes, DateTimeZone dateTimeZone) {
        var jodaMonth = ToJodaTime.toDateTimeConstant(month);
        var jodaDateTimeZone = ToJodaTime.toJodaTime(dateTimeZone);
        return new DateTimeJodaTime(new org.joda.time.DateTime(year, jodaMonth, dayOfMonth, hour, minutes, jodaDateTimeZone));
    }

    org.joda.time.DateTime toJoda();

    DateTime toDateTime(DateTimeZone timeZone);

    String asString(DateTimeFormatter workLogFormatter);

    DateTime plusDays(int days);

    LocalDate toLocalDate();

    DateTime withZone(DateTimeZone utc);

    boolean isBefore(DateTime other);
}
