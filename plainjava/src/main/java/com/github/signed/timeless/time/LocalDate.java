package com.github.signed.timeless.time;

import com.github.signed.timeless.time.joda.JodaTimeFactory;

/**
 * Mirrors import com.github.signed.timeless.time.LocalDate;
 */
public interface LocalDate extends Comparable<LocalDate> {
     LocalDateFactory factory = new JodaTimeFactory();

    static LocalDate now() {
        return factory.now();
    }

    static LocalDate of(int year, Month month, int day) {
        return factory.of(year, month, day);
    }

    static LocalDate of(org.joda.time.LocalDate localDate) {
        return factory.of(localDate);
    }

    boolean is(Month month);

    boolean isDayOfMonth(int dayOfMonth);

    LocalDate plusDays(int days);

    LocalDate minusDays(int days);

    int getYear();

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
