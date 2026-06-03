package com.github.signed.timeless.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Mirrors import com.github.signed.timeless.time.LocalDate;
 */
public interface LocalDate extends Comparable<LocalDate> {

    static LocalDate now() {
        return of(new org.joda.time.LocalDate());
    }

    static LocalDate of(int year, Month month, int day) {
        var jodaMonth = ToJodaTime.toDateTimeConstant(month);
        return of(new org.joda.time.LocalDate(year, jodaMonth, day));
    }

    //todo get rid of
    static LocalDate of(org.joda.time.LocalDate localDate) {
        return new LocalDateJodaTime(localDate);
    }

    boolean is(Month month);

    boolean isDayOfMonth(int dayOfMonth);

    LocalDate plusDays(int days);

    LocalDate minusDays(int days);

    int getYear();

    //todo drive out joda time from the interface
    DateTime toDateTimeAtStartOfDay(DateTimeZone zone);

    DayOfWeek dayOfWeek();

    boolean isBefore(LocalDate localDate);

    org.joda.time.LocalDate joda();

    String asString();

    int weekOfWeekyear();

    int compareTo(LocalDate day);

    Month monthOfYear();

    int dayOfMonth();

    LocalDate plusMonths(int numberOfMonths);
}
