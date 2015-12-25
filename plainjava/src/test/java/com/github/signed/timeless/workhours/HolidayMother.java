package com.github.signed.timeless.workhours;

import org.joda.time.LocalDate;

public class HolidayMother {

    public static Holiday anyHoliday(){
        return new Holiday(new LocalDate(2015, 1, 1));
    }
}
