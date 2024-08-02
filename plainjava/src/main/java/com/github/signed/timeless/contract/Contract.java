package com.github.signed.timeless.contract;

import com.github.signed.timeless.holidays.Holidays;
import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.LocalDate;

public class Contract implements WorkHoursPerDayAdjuster {

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        new WorkHours().adjustHoursToWorkFor(date, workHoursPerDayBuilder);
        new EmployerCourtesy().adjustHoursToWorkFor(date, workHoursPerDayBuilder);
        // actual holidays depend on your place of work that is specified in the contract
        new Holidays().adjustHoursToWorkFor(date, workHoursPerDayBuilder);
    }
}
