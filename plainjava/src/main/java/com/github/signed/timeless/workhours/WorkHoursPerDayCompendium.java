package com.github.signed.timeless.workhours;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import com.github.signed.timeless.HoursRequired;

public class WorkHoursPerDayCompendium implements HoursRequired {

    private final Set<WorkHoursPerDayAdjuster> adjusters;

    public WorkHoursPerDayCompendium(Set<WorkHoursPerDayAdjuster> adjusters) {
        this.adjusters = new HashSet<>(adjusters);
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
