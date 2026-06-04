package com.github.signed.timeless.time;

public interface TimeSource {
    LocalDate localDateNow();

    LocalDate of(int year, Month month, int day);

    //todo get rid of
    LocalDate of(org.joda.time.LocalDate localDate);
}
