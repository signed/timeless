package com.github.signed.timeless.time;

import com.github.signed.timeless.time.joda.JodaTimeSource;

public interface TimeSource {
    TimeSource instance = new JodaTimeSource();

    LocalDate localDateNow();

    LocalDate localDateOf(int year, Month month, int day);
}
