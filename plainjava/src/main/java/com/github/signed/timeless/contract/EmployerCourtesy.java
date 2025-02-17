package com.github.signed.timeless.contract;

import com.github.signed.timeless.workhours.WorkHoursPerDayAdjuster;
import com.github.signed.timeless.workhours.WorkHoursPerDayBuilder;
import org.joda.time.LocalDate;

import static com.github.signed.timeless.specialdays.SpecialDays.christmas;
import static com.github.signed.timeless.specialdays.SpecialDays.newYearsEve;

public class EmployerCourtesy implements WorkHoursPerDayAdjuster {

    @Override
    public void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder) {
        var christmas = christmas();
        var newYearsEve = newYearsEve();
        if (christmas.test(date) || newYearsEve.test(date)) {
            workHoursPerDayBuilder.reduceByHalfAWorkDay();
        }
    }
}
