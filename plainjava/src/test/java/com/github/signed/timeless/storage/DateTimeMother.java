package com.github.signed.timeless.storage;

import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.Month;

public class DateTimeMother {

    public static LocalDate AnyWorkday() {
        return AnySunday().plusDays(2);
    }

    public static LocalDate AnyLocalDate(){
        return LocalDate.of(2017, Month.March, 4);
    }

    public static LocalDate AnyMondayAtTheStartOfAFiveDayWorkWeek() {
        return LocalDate.of(2015, Month.November, 16);
    }

    public static LocalDate AnySunday() {
        return AnySaturday().plusDays(1);
    }

    public static LocalDate AnySaturday() {
        return LocalDate.of(2015, Month.December, 26);
    }

    public static LocalDate AnyChristmasEve() {
        return AnyChristmasEveOnAWeekend();
    }

    public static LocalDate AnyChristmasEveOnAWorkDay() {
        return LocalDate.of(1968, Month.December, 24);
    }

    private static LocalDate AnyChristmasEveOnAWeekend() {
        return LocalDate.of(1967, Month.December, 24);
    }

    public static LocalDate AnyNewYearsEve() {
        return AnyNewYearsEveOnAWeekend();
    }

    private static LocalDate AnyNewYearsEveOnAWorkDay() {
        return LocalDate.of(1968, Month.December, 31);
    }

    private static LocalDate AnyNewYearsEveOnAWeekend() {
        return LocalDate.of(1967, Month.December, 31);
    }

    public static LocalDate AnyDayNotChristmasEveOrNewYearsEve() {
        return AnyNewYearsEveOnAWorkDay().plusMonths(1);
    }

    public static LocalDate AnyWorkdayFridayWhereNextMondayIsAWorkday() {
        return LocalDate.of(2016, Month.February, 12);
    }
}
