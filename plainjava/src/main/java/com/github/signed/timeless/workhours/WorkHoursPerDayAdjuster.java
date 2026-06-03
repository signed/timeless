package com.github.signed.timeless.workhours;

import com.github.signed.timeless.time.LocalDate;

public interface WorkHoursPerDayAdjuster {

    void adjustHoursToWorkFor(LocalDate date, WorkHoursPerDayBuilder workHoursPerDayBuilder);
}
