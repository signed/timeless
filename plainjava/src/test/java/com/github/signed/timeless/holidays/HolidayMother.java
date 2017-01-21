package com.github.signed.timeless.holidays;

import org.joda.time.LocalDate;

public class HolidayMother {

    public static Holiday anyHoliday(){
        return new Holiday(new LocalDate(2015, 1, 1));
    }

    public static Holiday extraordinaryHoliday() {
        return new Holiday(new LocalDate(2017, 10, 31));
    }
}
