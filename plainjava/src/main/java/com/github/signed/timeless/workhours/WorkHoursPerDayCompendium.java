package com.github.signed.timeless.workhours;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import com.github.signed.timeless.HoursRequired;
import com.github.signed.timeless.contract.WorkHours;

public class WorkHoursPerDayCompendium implements HoursRequired {

    private final Set<WorkHoursPerDayAdjuster> adjusters = new HashSet<WorkHoursPerDayAdjuster>();

    public WorkHoursPerDayCompendium() {
        adjusters.add(new WorkHours());
        adjusters.add(new PersonalTimeOff());
    }

    @Override
    public WorkHoursPerDay hoursToWorkAt(LocalDate day) {
        WorkHoursPerDayBuilder builder = new WorkHoursPerDayBuilder();
        for (WorkHoursPerDayAdjuster adjuster : adjusters) {
            adjuster.adjustHoursToWorkFor(day, builder);
        }
        return builder.build();
    }
}
