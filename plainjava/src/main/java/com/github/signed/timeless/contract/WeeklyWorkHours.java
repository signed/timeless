package com.github.signed.timeless.contract;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

public class WeeklyWorkHours implements WorkHoursPerDayAdjuster {

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        workHoursPerDayBuilder.hoursToWork(hoursToWorkAt(date));
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
