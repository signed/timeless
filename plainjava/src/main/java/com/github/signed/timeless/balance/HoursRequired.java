package com.github.signed.timeless.balance;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

public interface HoursRequired {

    Duration hoursToWorkAt(LocalDate day);
}
