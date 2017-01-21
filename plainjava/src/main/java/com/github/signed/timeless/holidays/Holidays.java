package com.github.signed.timeless.holidays;

import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;

public class Holidays implements WorkHoursPerDayAdjuster {

    private final Iterable<HolidayAlmanac> holidayAlmanacs = Arrays.asList(new HolidayCalculator(), new ExtraordinaryHolidays());

    @Override
    public void adjustHoursToWorkFor(LocalDate day, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        for (HolidayAlmanac almanac : holidayAlmanacs) {
            List<Holiday> holidays = almanac.holidaysFor(day.getYear());
            for (Holiday holiday : holidays) {
                if (holiday.date.equals(day)) {
                    workHoursPerDayBuilder.holiday(holiday);
                    return;
                }
            }
        }
    }
}
