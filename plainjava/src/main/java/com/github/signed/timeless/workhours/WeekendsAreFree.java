package com.github.signed.timeless.workhours;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

import org.joda.time.LocalDate;

public class WeekendsAreFree implements WorkHoursPerDayAdjuster {
    @Override
    public void adjustHoursToWorkFor(LocalDate day, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        if( day.dayOfWeek().get() == SATURDAY || day.dayOfWeek().get() == SUNDAY){
            workHoursPerDayBuilder.reduceByCompleteWorkDay();
        }
    }
}
