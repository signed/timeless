package com.github.signed.timeless.storage;

import org.joda.time.LocalDate;

public class DateTimeMother {

    public static LocalDate AnySaturday() {
        return new LocalDate(2015, 12, 26);
    }

    public static LocalDate AnySunday() {
        return AnySaturday().plusDays(1);
    }

    public static LocalDate AnyWeekDayBetweenMondayAndFriday() {
        return AnySunday().plusDays(2);
    }
}
