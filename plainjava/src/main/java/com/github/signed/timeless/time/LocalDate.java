package com.github.signed.timeless.time;

/**
 * Mirrors import com.github.signed.timeless.time.LocalDate;
 */
public interface LocalDate extends Comparable<LocalDate> {

    static LocalDate now() {
        return TimeSource.instance.localDateNow();
    }

    static LocalDate of(int year, Month month, int day) {
        return TimeSource.instance.localDateOf(year, month, day);
    }

    boolean is(Month month);

    boolean isDayOfMonth(int dayOfMonth);

    LocalDate plusDays(int days);

    LocalDate minusDays(int days);

    int getYear();

    DateTime toDateTimeAtStartOfDay(DateTimeZone zone);

    DayOfWeek dayOfWeek();

    boolean isBefore(LocalDate localDate);

    String asString();

    int weekOfWeekyear();

    int compareTo(LocalDate day);

    Month monthOfYear();

    int dayOfMonth();

    LocalDate plusMonths(int numberOfMonths);

    String asISOString();
}
