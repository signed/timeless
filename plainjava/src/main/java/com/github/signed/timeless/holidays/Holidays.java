package com.github.signed.timeless.holidays;

import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;

public class Holidays implements WorkHoursPerDayAdjuster {

    private final Iterable<HolidayAlmanac> holidayAlmanacs = Arrays.asList(new HolidayCalculator(), new ExtraordinaryHolidays());

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        for (HolidayAlmanac almanac : holidayAlmanacs) {
            List<Holiday> holidays = almanac.holidaysFor(date.getYear());
            for (Holiday holiday : holidays) {
                if (holiday.date.equals(date)) {
                    workHoursPerDayBuilder.holiday(holiday);
                    return;
                }
            }
        }
    }
}
