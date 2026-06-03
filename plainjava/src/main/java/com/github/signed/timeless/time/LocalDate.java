package com.github.signed.timeless.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Mirrors import com.github.signed.timeless.time.LocalDate;
 */
public interface LocalDate {

    boolean is(Month month);

    boolean isDayOfMonth(int dayOfMonth);

    LocalDate plusDays(int days);

    //todo drive out joda time from the interface
    DateTime toDateTimeAtStartOfDay(DateTimeZone zone);
}
