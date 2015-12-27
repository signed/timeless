package com.github.signed.timeless.contract;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;

public class WorkHours implements WorkHoursPerDayAdjuster{

    @Override
    public void adjustHoursToWorkFor(LocalDate day, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        workHoursPerDayBuilder.hoursToWork(hoursToWorkAt(day));
    }

    private Duration hoursToWorkAt(LocalDate day) {
        if (day.dayOfWeek().get() == SATURDAY || day.dayOfWeek().get() == SUNDAY) {
            return Duration.ZERO;
        }
        return workday();
    }

    private Duration workday() {
        return Duration.standardHours(8);
    }
}