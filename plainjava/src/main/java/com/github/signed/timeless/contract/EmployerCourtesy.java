package com.github.signed.timeless.contract;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;

public class EmployerCourtesy implements WorkHoursPerDayAdjuster {
    @Override
    public void adjustHoursToWorkFor(LocalDate day, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        if (isChristmas(day) || isNewYearsEve(day)) {
            workHoursPerDayBuilder.reduceByHalfAWorkDay();
        }
    }

    private boolean isChristmas(LocalDate day) {
        return day.monthOfYear().get() == DateTimeConstants.DECEMBER && day.dayOfMonth().get() == 24;
    }

    private boolean isNewYearsEve(LocalDate day) {
        return day.monthOfYear().get() == DateTimeConstants.DECEMBER && day.dayOfMonth().get() == 31;
    }

}
