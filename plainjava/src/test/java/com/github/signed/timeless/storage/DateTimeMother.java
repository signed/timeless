package com.github.signed.timeless.storage;

import org.joda.time.LocalDate;

public class DateTimeMother {

    public static LocalDate AnyWorkday() {
        return AnySunday().plusDays(2);
    }

    public static LocalDate AnyMondayAtTheStartOfAFiveDayWorkWeek(){
        return new LocalDate(2015, 11, 16);
    }

    public static LocalDate AnySunday() {
        return AnySaturday().plusDays(1);
    }

    public static LocalDate AnySaturday() {
        return new LocalDate(2015, 12, 26);
    }

    public static LocalDate ChristmasOnAWorkDay() {
        return new LocalDate(1968, 12, 24);
    }

    public static LocalDate ChristmasOnAWeekend() {
        return new LocalDate(1967, 12, 24);
    }


    public static LocalDate NewYearsEveOnAWorkDay() {
        return new LocalDate(1968, 12, 31);
    }

    public static LocalDate NewYearsEveOnAWeekend() {
        return new LocalDate(1967, 12, 31);
    }

}
