package com.github.signed.timeless.contract;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.HoursRequired;
import com.github.signed.timeless.WorkHoursPerDay;

public class WorkHours implements HoursRequired{

    @Override
    public WorkHoursPerDay hoursToWorkAt(LocalDate day) {
        if( day.dayOfWeek().get() == DateTimeConstants.SATURDAY || day.dayOfWeek().get() == DateTimeConstants.SUNDAY){
            return WorkHoursPerDay.unreducedWorkHours(Duration.ZERO);
        }
        return WorkHoursPerDay.unreducedWorkHours(Duration.standardHours(8));
    }
}