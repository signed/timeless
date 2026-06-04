package com.github.signed.timeless.time;

import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;

public interface DateTime extends Comparable<DateTime> {
    static DateTime of(int year, Month month, int dayOfMonth, int hour, int minutes, DateTimeZone inputTimeZone) {
        var jodaMonth = ToJodaTime.toDateTimeConstant(month);
        return new DateTimeJodaTime(new org.joda.time.DateTime(year, jodaMonth, dayOfMonth, hour, minutes, inputTimeZone));
    }

    org.joda.time.DateTime toJoda();

    DateTime toDateTime(DateTimeZone timeZone);

    String asString(DateTimeFormatter workLogFormatter);

    DateTime plusDays(int days);

    LocalDate toLocalDate();

    DateTime withZone(DateTimeZone utc);

    boolean isBefore(DateTime other);
}
