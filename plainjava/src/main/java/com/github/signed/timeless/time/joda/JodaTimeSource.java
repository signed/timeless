package com.github.signed.timeless.time.joda;

import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.TimeSource;
import com.github.signed.timeless.time.Month;

public class JodaTimeSource implements TimeSource {
    @Override
    public LocalDate localDateNow() {
        return of(new org.joda.time.LocalDate());
    }

    @Override
    public LocalDate of(int year, Month month, int day) {
        var jodaMonth = ToJodaTime.toDateTimeConstant(month);
        return of(new org.joda.time.LocalDate(year, jodaMonth, day));
    }

    @Override
    public LocalDate of(org.joda.time.LocalDate localDate) {
        return new LocalDateJodaTime(localDate);
    }
}
