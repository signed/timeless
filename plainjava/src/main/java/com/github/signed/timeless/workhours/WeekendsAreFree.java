package com.github.signed.timeless.workhours;

import org.joda.time.LocalDate;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

public class WeekendsAreFree implements WorkHoursPerDayAdjuster {
    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        if (date.dayOfWeek().get() == SATURDAY || date.dayOfWeek().get() == SUNDAY) {
            workHoursPerDayBuilder.reduceByCompleteWorkDay();
        }
    }
}
