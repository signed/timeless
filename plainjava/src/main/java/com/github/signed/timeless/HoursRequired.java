package com.github.signed.timeless;

import com.github.signed.timeless.workhours.WorkHoursPerDay;
import org.joda.time.LocalDate;

public interface HoursRequired {

    WorkHoursPerDay hoursToWorkAt(LocalDate day);
}
