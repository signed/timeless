package com.github.signed.timeless.holidays;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

class ExtraordinaryHolidays implements HolidayAlmanac {

    @Override
    public List<Holiday> holidaysFor(int year) {
        List<Holiday> holidays = new ArrayList<>();
        holidays.add(new Holiday(new LocalDate(2017, 10, 31)));
        return holidays;
    }
}
