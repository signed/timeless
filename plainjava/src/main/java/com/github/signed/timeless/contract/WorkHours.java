package com.github.signed.timeless.contract;

import org.joda.time.DateTimeConstants;
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
        if (day.dayOfWeek().get() == DateTimeConstants.SATURDAY || day.dayOfWeek().get() == DateTimeConstants.SUNDAY) {
            return Duration.ZERO;
        }
        if (isChristmas(day) || isNewYearsEve(day)) {
            return halfAWorkday();
        }
        return workday();
    }

    private boolean isChristmas(LocalDate day) {
        return day.monthOfYear().get() == DateTimeConstants.DECEMBER && day.dayOfMonth().get() == 24;
    }

    private boolean isNewYearsEve(LocalDate day) {
        return day.monthOfYear().get() == DateTimeConstants.DECEMBER && day.dayOfMonth().get() == 31;
    }

    private Duration halfAWorkday() {
        return workday().dividedBy(2);
    }

    private Duration workday() {
        return Duration.standardHours(8);
    }
}