package com.github.signed.timeless.time;

import com.github.signed.timeless.time.joda.JodaTimeSource;

public interface TimeSource {
    TimeSource instance = new JodaTimeSource();

    LocalDate localDateNow();

    LocalDate localDateOf(int year, Month month, int day);

    Interval intervalOf(DateTime start, DateTime end);

    Interval intervalInfinity();

    DateTime dateTimeOf(int year, Month month, int dayOfMonth, int hour, int minutes, DateTimeZone dateTimeZone);

    Duration durationZero();

    Duration durationMinutes(int minutes);

    Duration durationHours(int hours);

    Duration durationDays(int days);
}
