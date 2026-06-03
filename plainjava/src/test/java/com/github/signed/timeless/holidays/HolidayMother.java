package com.github.signed.timeless.holidays;

import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.Month;

import static com.github.signed.timeless.time.Month.January;
import static com.github.signed.timeless.time.Month.October;

public class HolidayMother {

    public static Holiday anyHoliday() {
        return new Holiday(LocalDate.of(2015, January, 1));
    }

    public static Holiday extraordinaryHoliday() {
        return new Holiday(LocalDate.of(2017, October, 31));
    }
}
