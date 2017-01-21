package com.github.signed.timeless.holidays;

import org.joda.time.LocalDate;

import com.github.signed.timeless.holidays.Holiday;

public class HolidayMother {

    public static Holiday anyHoliday(){
        return new Holiday(new LocalDate(2015, 1, 1));
    }
}
