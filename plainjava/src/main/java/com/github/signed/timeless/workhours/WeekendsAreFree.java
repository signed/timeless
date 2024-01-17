package com.github.signed.timeless.workhours;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

import org.joda.time.LocalDate;

public class WeekendsAreFree implements WorkHoursPerDayAdjuster {
    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        if( date.dayOfWeek().get() == SATURDAY || date.dayOfWeek().get() == SUNDAY){
            workHoursPerDayBuilder.reduceByCompleteWorkDay();
        }
    }
}
