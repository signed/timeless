package com.github.signed.timeless.time;

public interface DateTime extends Comparable<DateTime> {
    static DateTime of(int year, Month month, int dayOfMonth, int hour, int minutes, DateTimeZone dateTimeZone) {
        return TimeSource.instance.dateTimeOf(year, month, dayOfMonth, hour, minutes, dateTimeZone);
    }

    org.joda.time.DateTime toJoda();

    DateTime toDateTime(DateTimeZone timeZone);

    DateTime plusDays(int days);

    LocalDate toLocalDate();

    DateTime withZone(DateTimeZone utc);

    boolean isBefore(DateTime other);

    String asWorkLogString();
}
