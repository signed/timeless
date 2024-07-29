package com.github.signed.timeless.holidays;

import com.github.signed.timeless.workhours.EasterSundayCalculator;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import static org.joda.time.DateTimeConstants.*;

class HolidayCalculator implements HolidayAlmanac {

    private final EasterSundayCalculator calculator = new EasterSundayCalculator();

    @Override
    public List<Holiday> holidaysFor(int year) {
        List<Holiday> holidays = new ArrayList<>();
        holidays.add(new Holiday(new LocalDate(year, JANUARY, 1)));
        holidays.add(new Holiday(new LocalDate(year, JANUARY, 6)));
        LocalDate easterSunday = calculator.easterSundayFor(year);
        holidays.add(new Holiday(easterSunday.minusDays(2)));
        holidays.add(new Holiday(easterSunday.plusDays(1)));
        holidays.add(new Holiday(new LocalDate(year, MAY, 1)));
        holidays.add(new Holiday(easterSunday.plusDays(39)));
        holidays.add(new Holiday(easterSunday.plusDays(50)));
        holidays.add(new Holiday(easterSunday.plusDays(60)));
        holidays.add(new Holiday(new LocalDate(year, OCTOBER, 3)));
        holidays.add(new Holiday(new LocalDate(year, NOVEMBER, 1)));
        holidays.add(new Holiday(new LocalDate(year, DECEMBER, 25)));
        holidays.add(new Holiday(new LocalDate(year, DECEMBER, 26)));
        return holidays;
    }
}
