package com.github.signed.timeless.contract;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

public class WeeklyWorkHours implements WorkHoursPerDayAdjuster {

    public static WeeklyWorkHours fortyHourWeek() {
        return fiveDayWorkWeekOf(40);
    }

    public static WeeklyWorkHours fiveDayWorkWeekOf(int hours) {
        return new WeeklyWorkHours(hours);
    }

    private final Duration workday;

    private WeeklyWorkHours(final int hours) {
        workday = Duration.standardHours(hours).dividedBy(5);
    }

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        workHoursPerDayBuilder.hoursToWork(hoursToWorkAt(date));
    }

    private Duration hoursToWorkAt(LocalDate day) {
        if (day.dayOfWeek().get() == SATURDAY || day.dayOfWeek().get() == SUNDAY) {
            return Duration.ZERO;
        }
        return workday;
    }

}
