package com.github.signed.timeless.workhours;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.github.signed.timeless.contract.EmployerCourtesy;
import com.github.signed.timeless.holidays.Holidays;

class WorkDays {

    private final List<WorkHoursPerDayAdjuster> adjusters = new ArrayList<>();

    {
        adjusters.add(new Holidays());
        adjusters.add(new EmployerCourtesy());
        adjusters.add(new WeekendsAreFree());
    }

    int workdays(LocalDate startInclusive, LocalDate endInclusive) {
        Duration total = Duration.ZERO;

        for (LocalDate day = startInclusive; !day.isAfter(endInclusive); day = day.plusDays(1)) {
            WorkHoursPerDayBuilder workHoursPerDayBuilder = new WorkHoursPerDayBuilder().hoursToWork(Duration.standardDays(1));
            for (WorkHoursPerDayAdjuster adjuster : adjusters) {
                adjuster.adjustHoursToWorkFor(day, workHoursPerDayBuilder);
            }
            WorkHoursPerDay build = workHoursPerDayBuilder.build();
            total = total.plus(build.duration());
        }


        return (int) total.getStandardDays();
    }
}
