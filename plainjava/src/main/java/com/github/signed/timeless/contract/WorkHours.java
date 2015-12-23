package com.github.signed.timeless.contract;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.HoursRequired;
import com.github.signed.timeless.workhours.WorkHoursPerDay;

public class WorkHours implements HoursRequired {

    @Override
    public WorkHoursPerDay hoursToWorkAt(LocalDate day) {
        if (day.dayOfWeek().get() == DateTimeConstants.SATURDAY || day.dayOfWeek().get() == DateTimeConstants.SUNDAY) {
            return WorkHoursPerDay.unreducedWorkHours(Duration.ZERO);
        }
        if (isChristmas(day) || isNewYearsEve(day)) {
            return WorkHoursPerDay.unreducedWorkHours(halfAWorkday());
        }
        return WorkHoursPerDay.unreducedWorkHours(workday());
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