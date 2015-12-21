package com.github.signed.timeless.contract;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.balance.HoursRequired;

public class WorkHours implements HoursRequired{

    @Override
    public Duration hoursToWorkAt(LocalDate day) {
        if( day.dayOfWeek().get() == DateTimeConstants.SATURDAY || day.dayOfWeek().get() == DateTimeConstants.SUNDAY){
            return Duration.ZERO;
        }
        return Duration.standardHours(8);
    }
}