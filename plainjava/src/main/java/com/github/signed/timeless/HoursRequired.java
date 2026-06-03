package com.github.signed.timeless;

import com.github.signed.timeless.time.LocalDate;
import com.github.signed.timeless.workhours.WorkHoursPerDay;

public interface HoursRequired {

    WorkHoursPerDay hoursToWorkAt(LocalDate day);
}
