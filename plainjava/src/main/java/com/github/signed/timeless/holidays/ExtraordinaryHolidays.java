package com.github.signed.timeless.holidays;

import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.Month;

import java.util.ArrayList;
import java.util.List;

class ExtraordinaryHolidays implements HolidayAlmanac {

    @Override
    public List<Holiday> holidaysFor(int year) {
        List<Holiday> holidays = new ArrayList<>();
        holidays.add(new Holiday(LocalDate.of(2017, Month.October, 31)));
        return holidays;
    }
}
