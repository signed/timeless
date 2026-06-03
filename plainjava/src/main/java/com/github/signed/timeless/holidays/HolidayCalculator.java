package com.github.signed.timeless.holidays;

import com.github.signed.timeless.specialdays.EasterSundayCalculator;
import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.time.Month;

import java.util.ArrayList;
import java.util.List;

import static org.joda.time.DateTimeConstants.*;

class HolidayCalculator implements HolidayAlmanac {

    private final EasterSundayCalculator calculator = new EasterSundayCalculator();

    @Override
    public List<Holiday> holidaysFor(int year) {
        List<Holiday> holidays = new ArrayList<>();
        holidays.add(new Holiday(LocalDate.of(year, Month.January, 1)));
        holidays.add(new Holiday(LocalDate.of(year, Month.January, 6)));
        LocalDate easterSunday = calculator.easterSundayFor(year);
        holidays.add(new Holiday(easterSunday.minusDays(2)));
        holidays.add(new Holiday(easterSunday.plusDays(1)));
        holidays.add(new Holiday(LocalDate.of(year, Month.May, 1)));
        holidays.add(new Holiday(easterSunday.plusDays(39)));
        holidays.add(new Holiday(easterSunday.plusDays(50)));
        holidays.add(new Holiday(easterSunday.plusDays(60)));
        holidays.add(new Holiday(LocalDate.of(year, Month.October, 3)));
        holidays.add(new Holiday(LocalDate.of(year, Month.November, 1)));
        holidays.add(new Holiday(LocalDate.of(year, Month.December, 25)));
        holidays.add(new Holiday(LocalDate.of(year, Month.December, 26)));
        return holidays;
    }
}
