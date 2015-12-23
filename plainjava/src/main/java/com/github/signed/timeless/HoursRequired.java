package com.github.signed.timeless;

import org.joda.time.LocalDate;

import com.github.signed.timeless.workhours.WorkHoursPerDay;

public interface HoursRequired {

    WorkHoursPerDay hoursToWorkAt(LocalDate day);
}
