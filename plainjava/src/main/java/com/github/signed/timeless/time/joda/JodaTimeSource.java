package com.github.signed.timeless.time.joda;

import com.github.signed.timeless.time.DateTime;
import com.github.signed.timeless.time.DateTimeZone;
import com.github.signed.timeless.time.Interval;
import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.TimeSource;
import com.github.signed.timeless.time.Month;

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
        return new IntervalJodaTime(new org.joda.time.Interval(start.toJoda(), end.toJoda()));
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



    public LocalDate localDateOf(org.joda.time.LocalDate localDate) {
        return new LocalDateJodaTime(localDate);
    }
}
