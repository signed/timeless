package com.github.signed.timeless;

import org.joda.time.LocalDate;

public interface HoursRequired {

    WorkHoursPerDay hoursToWorkAt(LocalDate day);
}
