package com.github.signed.timeless.workhours;

import java.util.List;

import org.joda.time.LocalDate;

public class Holidays implements WorkHoursPerDayAdjuster{

    private final HolidayCalculator calculator = new HolidayCalculator();
    @Override
    public void adjustHoursToWorkFor(LocalDate day, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        List<Holiday> holidays = calculator.holidaysFor(day.getYear());
        for (Holiday holiday : holidays) {
            if( holiday.date.equals(day)){
                workHoursPerDayBuilder.holiday(holiday);
                return;
            }
        }
    }
}
