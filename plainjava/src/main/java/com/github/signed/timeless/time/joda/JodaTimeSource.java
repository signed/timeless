package com.github.signed.timeless.time.joda;

import com.github.signed.timeless.time.DateTime;
import com.github.signed.timeless.time.DateTimeZone;
import com.github.signed.timeless.time.Duration;
import com.github.signed.timeless.time.Interval;
import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.Month;
import com.github.signed.timeless.time.TimeSource;

public class JodaTimeSource implements TimeSource {
    @Override
    public LocalDate localDateNow() {
        return localDateOf(new org.joda.time.LocalDate());
    }

    @Override
    public LocalDate localDateOf(int year, Month month, int day) {
        var jodaMonth = ToJodaTime.toDateTimeConstant(month);
        return localDateOf(new org.joda.time.LocalDate(year, jodaMonth, day));
    }

    @Override
    public Interval intervalOf(DateTime start, DateTime end) {
        var jodaStart = DateTimeJodaTime.jodaDateTimeIn(start);
        var jodaEnd= DateTimeJodaTime.jodaDateTimeIn(end);

        return new IntervalJodaTime(new org.joda.time.Interval(jodaStart, jodaEnd));
    }

    @Override
    public Interval intervalInfinity() {
        return new IntervalJodaTime(new org.joda.time.Interval(Long.MIN_VALUE, Long.MAX_VALUE));
    }

    @Override
    public DateTime dateTimeOf(int year, Month month, int dayOfMonth, int hour, int minutes, DateTimeZone dateTimeZone) {
        var jodaMonth = ToJodaTime.toDateTimeConstant(month);
        var jodaDateTimeZone = ToJodaTime.toJodaTime(dateTimeZone);
        return new DateTimeJodaTime(new org.joda.time.DateTime(year, jodaMonth, dayOfMonth, hour, minutes, jodaDateTimeZone));
    }

    @Override
    public Duration durationZero() {
        return new DurationJodaTime(org.joda.time.Duration.ZERO);
    }

    @Override
    public Duration durationMinutes(int minutes) {
        return new DurationJodaTime(org.joda.time.Duration.standardMinutes(minutes));
    }

    @Override
    public Duration durationHours(int hours) {
        return new DurationJodaTime(org.joda.time.Duration.standardHours(hours));
    }

    @Override
    public Duration durationDays(int days) {
        return new DurationJodaTime(org.joda.time.Duration.standardDays(days));
    }

    public LocalDate localDateOf(org.joda.time.LocalDate localDate) {
        return new LocalDateJodaTime(localDate);
    }
}
