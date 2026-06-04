package com.github.signed.timeless.time.joda;

import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.LocalDateFactory;
import com.github.signed.timeless.time.Month;

public class JodaTimeFactory implements LocalDateFactory {
    @Override
    public LocalDate now() {
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
