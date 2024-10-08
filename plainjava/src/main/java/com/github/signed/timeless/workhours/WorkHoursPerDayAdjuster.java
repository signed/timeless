package com.github.signed.timeless.workhours;

import org.joda.time.LocalDate;

public interface WorkHoursPerDayAdjuster {

    void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder);
}
